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
        //??????????????? : file.getOriginalFilename();
        String uploadFileName = file.getOriginalFilename();
        //?????????????????????????????????????????????
        if ("".equals(uploadFileName)) {
            return "redirect:/index.jsp";
        }
        System.out.println("??????????????? : " + uploadFileName);
        //????????????????????????
        String path = "C:\\Users\\?????????\\Desktop\\PestsIdentification\\web\\WEB-INF\\image";
        String path2 ="C:\\Users\\?????????\\Desktop\\PestsIdentification\\out\\artifacts\\PestsIdentification_war_exploded\\WEB-INF\\image";
        //????????????????????????????????????
        File realPath = new File(path);
        File realPath2 = new File(path2);
        String path1 = "../image/" + uploadFileName;
        if (method == 1)
            specimenImagesService.InsertImage(id, path1);
        else if (method == 2)
            ecologicalImagesService.InsertImage(id, path1);
        InputStream is = file.getInputStream(); //???????????????
        OutputStream os = new FileOutputStream(new File(realPath, uploadFileName)); //???????????????
        OutputStream os1 = new FileOutputStream(new File(realPath2, uploadFileName)); //???????????????
        //????????????
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
        //??????????????? : file.getOriginalFilename();
        String uploadFileName = file.getOriginalFilename();
        //?????????????????????????????????????????????
        if ("".equals(uploadFileName)) {
            return "redirect:/index.jsp";
        }
        //????????????????????????
        String path1 = "C:\\Users\\?????????\\Desktop\\PestsIdentification\\out\\artifacts\\PestsIdentification_war_exploded\\WEB-INF\\saved";
        String path2 = "C:\\Users\\?????????\\Desktop\\PestsIdentification\\web\\WEB-INF\\saved";
        //????????????????????????????????????
        File realPath1 = new File(path1);
        File realPath2 = new File(path2);
        InputStream is = file.getInputStream(); //???????????????
        OutputStream os1 = new FileOutputStream(new File(realPath1, "IMG.jpg")); //???????????????
        OutputStream os2 = new FileOutputStream(new File(realPath2, "IMG.jpg")); //???????????????
        //????????????
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
        FileWriter writer = new FileWriter("C:\\Users\\?????????\\Desktop\\PestsIdentification\\web\\WEB-INF\\saved\\file.txt");
        writer.write("IMG.jpg");
        writer.flush();
        writer.close();
        /*=======================??????Python============================*/
        Robot  r   =   new Robot();
        r.delay(3500);
        List<String> result=new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("C:\\Users\\?????????\\Desktop\\PestsIdentification\\web\\WEB-INF\\saved\\file.txt")));
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
