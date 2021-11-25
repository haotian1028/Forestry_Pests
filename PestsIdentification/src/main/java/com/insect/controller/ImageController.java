package com.insect.controller;

import com.insect.mapper.EcologicalImagesMapper;
import com.insect.pojo.EcologicalImages;
import com.insect.pojo.Insect;
import com.insect.pojo.SpecimenImages;
import com.insect.service.EcologicalImagesService;
import com.insect.service.InsectService;
import com.insect.service.SpecimenImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    @Qualifier("SpecimenImagesServiceImpl")
    private SpecimenImagesService specimenImagesService;
    @Autowired
    @Qualifier("InsectServiceImpl")
    private InsectService insectService;
    @Autowired
    @Qualifier("EcologicalImagesServiceImpl")
    private EcologicalImagesService ecologicalImagesService;


    @RequestMapping("toInsertSpecimenImage")
    public String toInsertImage(String id, Model model) {
        model.addAttribute("id", id);
        return "insertSpecimenImage";
    }

    @RequestMapping("toInsertEcoImage")
    public String toInsertEcoImage(String id, Model model) {
        model.addAttribute("id", id);
        return "insertEcoImage";
    }

    @RequestMapping("/toDetail")
    public String toDetailJsp(String id, Model model) {
        Insect insect = insectService.queryInsectById(id);
        List<SpecimenImages> image = specimenImagesService.selectAllImageById(id);
        model.addAttribute("InsectInfo", insect);
        model.addAttribute("image", image);
        return "detail";
    }

    @RequestMapping("/insertImage")
    public String insertSpecimenImage(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, String id, int method, Model model) throws IOException {
        //获取文件名 : file.getOriginalFilename();
        String uploadFileName = file.getOriginalFilename();
        //如果文件名为空，直接回到首页！
        if ("".equals(uploadFileName)) {
            return "redirect:/index.jsp";
        }
        System.out.println("上传文件名 : " + uploadFileName);
        //上传路径保存设置
        String path = "C:\\Users\\张浩天\\Desktop\\PestsIdentification\\web\\WEB-INF\\image";
        String path2 ="C:\\Users\\张浩天\\Desktop\\PestsIdentification\\out\\artifacts\\PestsIdentification_war_exploded\\WEB-INF\\image";
        //如果路径不存在，创建一个
        File realPath = new File(path);
        File realPath2 = new File(path2);
        String path1 = "../image/" + uploadFileName;
        if (method == 1)
            specimenImagesService.InsertImage(id, path1);
        else if (method == 2)
            ecologicalImagesService.InsertImage(id, path1);
        InputStream is = file.getInputStream(); //文件输入流
        OutputStream os = new FileOutputStream(new File(realPath, uploadFileName)); //文件输出流
        OutputStream os1 = new FileOutputStream(new File(realPath2, uploadFileName)); //文件输出流
        //读取写出
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
            os1.write(buffer, 0, len);
            os.flush();
            os1.flush();
        }
        os.close();
        is.close();
        model.addAttribute("id", id);
        if (method == 1)
            return "redirect:/image/toDetail";
        return "redirect:/image/ecoImage";
    }

    @RequestMapping("/predict")
    public String predict(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request, Model model) throws Exception {
        //获取文件名 : file.getOriginalFilename();
        String uploadFileName = file.getOriginalFilename();
        //如果文件名为空，直接回到首页！
        if ("".equals(uploadFileName)) {
            return "redirect:/index.jsp";
        }
        //上传路径保存设置
        String path1 = "C:\\Users\\张浩天\\Desktop\\PestsIdentification\\out\\artifacts\\PestsIdentification_war_exploded\\WEB-INF\\saved";
        String path2 = "C:\\Users\\张浩天\\Desktop\\PestsIdentification\\web\\WEB-INF\\saved";
        //如果路径不存在，创建一个
        File realPath1 = new File(path1);
        File realPath2 = new File(path2);
        InputStream is = file.getInputStream(); //文件输入流
        OutputStream os1 = new FileOutputStream(new File(realPath1, "IMG.jpg")); //文件输出流
        OutputStream os2 = new FileOutputStream(new File(realPath2, "IMG.jpg")); //文件输出流
        //读取写出
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = is.read(buffer)) != -1) {
            os1.write(buffer, 0, len);
            os2.write(buffer, 0, len);
            os1.flush();
            os2.flush();
        }
        os1.close();
        os2.close();
        is.close();
        FileWriter writer = new FileWriter("C:\\Users\\张浩天\\Desktop\\PestsIdentification\\web\\WEB-INF\\saved\\file.txt");
        writer.write("IMG.jpg");
        writer.flush();
        writer.close();
        /*=======================执行Python============================*/
        Robot  r   =   new Robot();
        r.delay(3500);
        List<String> result=new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("C:\\Users\\张浩天\\Desktop\\PestsIdentification\\web\\WEB-INF\\saved\\file.txt")));
        String s=null;
        while ((s=bufferedReader.readLine())!=null)
            result.add(s);
        bufferedReader.close();
        model.addAttribute("info",result);
        for (String s1 : result) {
            System.out.println(s1);
        }
        return "image";
    }

    @RequestMapping("/ecoImage")
    public String allImage(String id, Model model) {
        List<EcologicalImages> list = ecologicalImagesService.selectAllImageById(id);
        Insect insect = insectService.queryInsectById(id);
        model.addAttribute("name", insect.getInsectName());
        model.addAttribute("list", list);
        model.addAttribute("id", id);
        return "ecoImage";
    }

    @RequestMapping("/deleteEco")
    public String deleteEco(String path, String id, Model model) {
        System.out.println(path);
        ecologicalImagesService.deleteImageByPath(path);
        model.addAttribute("id", id);
        return "redirect:/image/ecoImage";
    }

    @RequestMapping("/toPredict")
    public String toPredict() {
        return "predict";
    }


}
