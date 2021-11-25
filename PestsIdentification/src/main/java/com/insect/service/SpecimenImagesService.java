package com.insect.service;

import com.insect.pojo.SpecimenImages;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpecimenImagesService {
    void InsertImage(String InsectId,String ImagePath);
    List<SpecimenImages> selectAllImageById(String InsectId);

}
