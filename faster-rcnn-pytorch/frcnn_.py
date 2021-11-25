import colorsys
import copy
import math
import os
import time

import cv2
import numpy as np
import torch
import torch.backends.cudnn as cudnn
import torch.nn as nn
from PIL import Image, ImageDraw, ImageFont
from torch.nn import functional as F

from nets.frcnn import FasterRCNN
from utils.utils import DecodeBox, get_new_img_size, loc2bbox, nms


#--------------------------------------------#
#   使用自己训练好的模型预测需要修改2个参数
#   model_path和classes_path都需要修改！
#   如果出现shape不匹配
#   一定要注意训练时的NUM_CLASSES、
#   model_path和classes_path参数的修改
#--------------------------------------------#
class FRCNN(object):
    _defaults = {
        "model_path"    : 'logs/Epoch45-Total_Loss0.2734-Val_Loss0.6429.pth',
        "classes_path"  : 'model_data/new_classes.txt',
        "confidence"    : 0.5,
        "iou"           : 0.3,
        "backbone"      : "resnet50",
        "cuda"          : True,
    }

    @classmethod
    def get_defaults(cls, n):
        if n in cls._defaults:
            return cls._defaults[n]
        else:
            return "Unrecognized attribute name '" + n + "'"

    #---------------------------------------------------#
    #   初始化faster RCNN
    #---------------------------------------------------#
    def __init__(self, **kwargs):
        self.__dict__.update(self._defaults)
        self.class_names = self._get_class()
        self.generate()

        self.mean = torch.Tensor([0, 0, 0, 0]).repeat(self.num_classes+1)[None]
        self.std = torch.Tensor([0.1, 0.1, 0.2, 0.2]).repeat(self.num_classes+1)[None]
        if self.cuda:
            self.mean = self.mean.cuda()
            self.std = self.std.cuda()
            
        self.decodebox = DecodeBox(self.std, self.mean, self.num_classes)

    #---------------------------------------------------#
    #   获得所有的分类
    #---------------------------------------------------#
    def _get_class(self):
        classes_path = os.path.expanduser(self.classes_path)
        with open(classes_path) as f:
            class_names = f.readlines()
        class_names = [c.strip() for c in class_names]
        return class_names

    #---------------------------------------------------#
    #   载入模型
    #---------------------------------------------------#
    def generate(self):
        #-------------------------------#
        #   计算总的类的数量
        #-------------------------------#
        self.num_classes = len(self.class_names)

        #-------------------------------#
        #   载入模型与权值
        #-------------------------------#
        self.model = FasterRCNN(self.num_classes,"predict",backbone=self.backbone).eval()
        print('Loading weights into state dict...')
        device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
        state_dict = torch.load(self.model_path, map_location=device)
        self.model.load_state_dict(state_dict)
        
        if self.cuda:
            os.environ["CUDA_VISIBLE_DEVICES"]="0" 
            # self.model = nn.DataParallel(self.model)
            self.model = self.model.cuda()

        print('{} model, anchors, and classes loaded.'.format(self.model_path))

        # 画框设置不同的颜色
        hsv_tuples = [(x / len(self.class_names), 1., 1.)
                      for x in range(len(self.class_names))]
        self.colors = list(map(lambda x: colorsys.hsv_to_rgb(*x), hsv_tuples))
        self.colors = list(
            map(lambda x: (int(x[0] * 255), int(x[1] * 255), int(x[2] * 255)),
                self.colors))
    
    #---------------------------------------------------#
    #   检测图片
    #---------------------------------------------------#
    def detect_image(self, image):
        #-------------------------------------#
        #   转换成RGB图片，可以用于灰度图预测。
        #-------------------------------------#
        image = image.convert("RGB")
        
        image_shape = np.array(np.shape(image)[0:2])
        old_width, old_height = image_shape[1], image_shape[0]
        old_image = copy.deepcopy(image)
        
        #---------------------------------------------------------#
        #   给原图像进行resize，resize到短边为600的大小上
        #---------------------------------------------------------#
        width,height = get_new_img_size(old_width, old_height)
        image = image.resize([width,height], Image.BICUBIC)

        #-----------------------------------------------------------#
        #   图片预处理，归一化。
        #-----------------------------------------------------------#
        photo = np.transpose(np.array(image,dtype = np.float32)/255, (2, 0, 1))

        with torch.no_grad():
            images = torch.from_numpy(np.asarray([photo]))
            if self.cuda:
                images = images.cuda()

            roi_cls_locs, roi_scores, rois, _ = self.model(images)
            #-------------------------------------------------------------#
            #   利用classifier的预测结果对建议框进行解码，获得预测框
            #-------------------------------------------------------------#
            outputs = self.decodebox.forward(roi_cls_locs[0], roi_scores[0], rois, height = height, width = width, nms_iou = self.iou, score_thresh = self.confidence)
            #---------------------------------------------------------#
            #   如果没有检测出物体，返回原图
            #---------------------------------------------------------#
            if len(outputs)==0:
                return old_image
            outputs = np.array(outputs)
            bbox = outputs[:,:4]
            label = outputs[:, 4]
            conf = outputs[:, 5]

            bbox[:, 0::2] = (bbox[:, 0::2]) / width * old_width
            bbox[:, 1::2] = (bbox[:, 1::2]) / height * old_height

        font = ImageFont.truetype(font='model_data/simhei.ttf',size=np.floor(3e-2 * np.shape(image)[1] + 0.5).astype('int32'))

        thickness = max((np.shape(old_image)[0] + np.shape(old_image)[1]) // old_width * 2, 1)
                
        image = old_image
        counter={'menephron':0, 'menephronXiong':0, 'menephronYoungRuo':0, 'menephronOldRuo':0, 'anachoreta':0, 'anachoretaXiong':0, 'anachoretaEgg':0, 'anachoretaRuo':0, 'troglodyta':0, 'troglodytaXiong':0, 'troglodytaEgg':0, 'troglodytaRuo':0, 'troglodytaYong':0, 'corpulenta':0, 'corpulentaRuo':0, 'fullo':0, 'fulloRuo':0, 'japonica':0, 'cunea':0, 'cuneaRuo':0, 'cuneaegg':0, 'subcarneaRuo':0, 'subcarnea':0, 'montelus':0, 'montelusRuo':0, 'versicolora':0, 'versicoloraegg':0, 'ruoversicolora':0, 'flavescens':0, 'consocia':0, 'ruoconsocia':0, 'chinensis':0, 'chinensisEgg':0, 'germari':0, 'germariRuo':0, 'alternatus':0, 'alternatusRuo':0} #计数器
        
        name = {'menephron':'霜天蛾',
        'menephronXiong':'霜天蛾雄',
        'menephronYoungRuo':'霜天蛾幼若',
        'menephronOldRuo':'霜天蛾老若',
        'anachoreta':'杨扇舟蛾（害）',
        'anachoretaXiong':'杨扇舟蛾雄（害）',
        'anachoretaEgg':'杨扇舟蛾卵（害）',
        'anachoretaRuo':'杨扇舟蛾若（害）',
        'troglodyta':'杨小舟蛾（害）',
        'troglodytaXiong':'杨小舟蛾雄（害）',
        'troglodytaEgg':'杨小舟蛾卵（害）',
        'troglodytaRuo':'杨小舟蛾若（害）',
        'troglodytaYong':'杨小舟蛾蛹（害）',
        'corpulenta':'草履蚧（害）',
        'corpulentaRuo':'草履蚧若（害）',
        'fullo':'麻皮蝽',
        'fulloRuo':'麻皮蝽若',
        'japonica':'日本脊吉丁',
        'cunea':'美国白蛾（害）',
        'cuneaRuo':'美国白蛾若（害）',
        'cuneaegg':'美国白蛾卵（害）',
        'subcarneaRuo':'人纹污灯蛾若（害）',
        'subcarnea':'人纹污灯蛾（害）',
        'montelus':'丝带凤蝶',
        'montelusRuo':'丝带凤蝶若',
        'versicolora':'柳蓝叶甲',
        'versicoloraegg':'柳蓝叶甲卵',
        'ruoversicolora':'柳蓝叶甲若',
        'flavescens':'黄刺蛾（害）',
        'consocia':'褐边绿刺蛾',
        'ruoconsocia':'褐边绿刺蛾若',
        'chinensis':'星天牛（害）',
        'chinensisEgg':'星天牛卵（害）',
        'germari':'桑天牛（害）',
        'germariRuo':'桑天牛若（害）',
        'alternatus':'松墨天牛（害）',
        'alternatusRuo':'松墨天牛若（害）'}

        for i, c in enumerate(label):
            predicted_class = self.class_names[int(c)]
            score = conf[i]

            left, top, right, bottom = bbox[i]
            top = top - 5
            left = left - 5
            bottom = bottom + 5
            right = right + 5

            top = max(0, np.floor(top + 0.5).astype('int32'))
            left = max(0, np.floor(left + 0.5).astype('int32'))
            bottom = min(np.shape(image)[0], np.floor(bottom + 0.5).astype('int32'))
            right = min(np.shape(image)[1], np.floor(right + 0.5).astype('int32'))

            # 画框框
            label = '{} {:.2f}'.format(name[predicted_class], score)
            draw = ImageDraw.Draw(image)
            label_size = draw.textsize(label, font)
            label = label.encode('utf-8')
           # print(label, top, left, bottom, right)

                        
            counter[predicted_class]=counter[predicted_class]+1
            
            if top - label_size[1] >= 0:
                text_origin = np.array([left, top - label_size[1]])
            else:
                text_origin = np.array([left, top + 1])

            for i in range(thickness):
                draw.rectangle(
                    [left + i, top + i, right - i, bottom - i],
                    outline=self.colors[int(c)])
            draw.rectangle(
                [tuple(text_origin), tuple(text_origin + label_size)],
                fill=self.colors[int(c)])
            draw.text(text_origin, str(label,'UTF-8'), fill=(0, 0, 0), font=font)
            del draw
        d = {k:v for k, v in counter.items() if v>=1}
        for v,k in d.items():
                print('{v}的数量是{k}'.format(v = name[v], k = k))
        
        return image
