package com.insect.mapper;

import com.insect.pojo.SpecimenImages;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpecimenImagesMapper {
    void InsertImage(@Param("InsectId") String InsectId, @Param("ImagePath") String ImagePath);
    List<SpecimenImages> selectAllImageById(@Param("InsectId") String InsectId);
}
