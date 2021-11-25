package com.insect.mapper;

import com.insect.pojo.Insect;
import org.apache.ibatis.annotations.Param;
import org.python.antlr.ast.Str;

import java.util.List;

public interface InsectMapper {
    //查询全部昆虫信息
    List<Insect> searchAllInsect(@Param("n") int n);
    Insect queryInsectById(@Param("id") String id);
    void addInsect(@Param("InsectId") String InsectId,@Param("InsectName")String InsectName,@Param("ScientificName")String ScientificName,@Param("GenusId")String GenusId,@Param("Crop")String Crop,@Param("Area")String Area);
    void deleteInsectById(@Param("InsectId") String InsectId);
    Insect queryInsectByName(@Param("InsectName")String InsectName);
    Insect queryInsectByScientificName(@Param("ScientificName")String ScientificName);
    void updateInsect(@Param("InsectId") String InsectId,@Param("InsectName")String InsectName,@Param("ScientificName")String ScientificName,@Param("GenusId")String GenusId,@Param("Crop")String Crop,@Param("Area")String Area);
    List<Insect> searchInsectByGenusId(@Param("GenusId") String GenusId);
    List<Insect> searchInsectLike( @Param("name")String name);
    List<Insect> searchAllPest();
}
