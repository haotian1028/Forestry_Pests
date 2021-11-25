package com.insect.service;

import com.insect.mapper.EcologicalImagesMapper;
import com.insect.pojo.EcologicalImages;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EcologicalImagesService {
    void InsertImage(@Param("InsectId") String InsectId, @Param("ImagePath") String ImagePath);
    List<EcologicalImages> selectAllImageById(@Param("InsectId") String InsectId);
    void deleteImageByPath(String path);
}
