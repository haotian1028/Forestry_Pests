package com.insect.controller;

import com.insect.pojo.Family;
import com.insect.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FamilyController {
    @Autowired
    @Qualifier("FamilyServiceImpl")
    private FamilyService familyService;

    @RequestMapping("/familyList")
    public String familyList(Model model,String id){
        List<Family> list = familyService.searchFamilyByOrderId(id);
        model.addAttribute("list",list);
        return "familyList";
    }
    @RequestMapping("/allFamily")
    public String allFamily(Model model){
        List<Family> list = familyService.searchAllFamily();
        model.addAttribute("list",list);
        return "familyList";
    }

}
