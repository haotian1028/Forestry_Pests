package com.insect.service;

import com.insect.pojo.Insect;
import org.apache.ibatis.annotations.Param;
import org.python.antlr.ast.Str;

import java.util.List;

public interface InsectService {
    //查询全部昆虫信息
    List<Insect> searchAllInsect(int n);
    void addInsect(String InsectId,String InsectName,String ScientificName,String GenusId,String Crop,String Area);
    Insect queryInsectById(String id);
    void deleteInsectById(String InsectId);
    Insect queryInsectByName(String InsectName);
    Insect queryInsectByScientificName(String ScientificName);
    void updateInsect(String InsectId,String InsectName,String ScientificName,String GenusId,String Crop,String Area);
    List<Insect> searchInsectByGenusId(String GenusId);
    List<Insect> searchInsectLike(String name);
    List<Insect> searchAllPest();




}
