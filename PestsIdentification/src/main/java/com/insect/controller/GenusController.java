package com.insect.controller;

import com.insect.pojo.Genus;
import com.insect.service.GenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GenusController {
    @Autowired
    @Qualifier("GenusServiceImpl")
    private GenusService genusService;

    @RequestMapping("/genusList")
    public String genusList(Model model,String id){
        List<Genus> list = genusService.searchGenusByFamilyId(id);
        model.addAttribute("list",list);
        return "genusList";
    }
    @RequestMapping("/allGenus")
    public String allGenus(Model model){
        List<Genus> list = genusService.searchAllGenus();
        model.addAttribute("list",list);
        return "genusList";
    }
}
