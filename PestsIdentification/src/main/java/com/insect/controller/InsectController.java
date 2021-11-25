package com.insect.controller;

import com.insect.pojo.Insect;
import com.insect.pojo.SpecimenImages;
import com.insect.service.InsectService;
import com.insect.service.SpecimenImagesService;
import jnr.ffi.annotations.In;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.python.antlr.ast.Str;
import org.python.icu.util.Output;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class InsectController {
    @Autowired
    @Qualifier("InsectServiceImpl")
    private InsectService insectService;

    @Autowired
    @Qualifier("SpecimenImagesServiceImpl")
    private SpecimenImagesService specimenImagesService;

    @RequestMapping("/allInsect")
    public String list(Model model,int n) throws IOException {
        List<Insect> list = insectService.searchAllInsect(n);
        model.addAttribute("n",n);
        model.addAttribute("list", list);
        return "allInsect";
    }
    @RequestMapping("/insectByGenus")
    public String insectByGenus(Model model,String id,String name){
        List<Insect> list = insectService.searchInsectByGenusId(id);
        model.addAttribute("list",list);
        model.addAttribute("genusId",id);
        model.addAttribute("genusName",name);
        return "insectByGenus";
    }
    @PostMapping("/searchInsect")
    public String searchInsect(Model model,String name){
        System.out.println("name====>"+name);
        List<Insect> list = insectService.searchInsectLike(name);

        model.addAttribute("list",list);
        return "searchInsect";
    }
    @RequestMapping("/allPest")
    public String allPest(Model model){
        List<Insect> insects = insectService.searchAllPest();
        model.addAttribute("list",insects);
        return "allPest";
    }

}
