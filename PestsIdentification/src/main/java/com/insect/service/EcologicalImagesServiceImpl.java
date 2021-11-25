package com.insect.service;

import com.insect.mapper.EcologicalImagesMapper;
import com.insect.pojo.EcologicalImages;

import java.util.List;

public class EcologicalImagesServiceImpl implements EcologicalImagesService {
    private EcologicalImagesMapper ecologicalImagesMapper;

    public void setEcologicalImagesMapper(EcologicalImagesMapper ecologicalImagesMapper) {
        this.ecologicalImagesMapper = ecologicalImagesMapper;
    }

    @Override
    public void InsertImage(String InsectId, String ImagePath) {
        ecologicalImagesMapper.InsertImage(InsectId, ImagePath);
    }

    @Override
    public List<EcologicalImages> selectAllImageById(String InsectId) {
        return ecologicalImagesMapper.selectAllImageById(InsectId);
    }

    @Override
    public void deleteImageByPath(String path) {
        ecologicalImagesMapper.deleteImageByPath(path);
    }
}
