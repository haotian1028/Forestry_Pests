package com.insect.service;

import com.insect.mapper.SpecimenImagesMapper;
import com.insect.pojo.SpecimenImages;

import java.util.List;

public class SpecimenImagesServiceImpl implements SpecimenImagesService {
    private SpecimenImagesMapper specimenImagesMapper;

    public void setSpecimenImagesMapper(SpecimenImagesMapper specimenImagesMapper) {
        this.specimenImagesMapper = specimenImagesMapper;
    }

    @Override
    public void InsertImage(String InsectId, String ImagePath) {
        specimenImagesMapper.InsertImage(InsectId, ImagePath);
    }

    @Override
    public List<SpecimenImages> selectAllImageById(String InsectId) {
        return specimenImagesMapper.selectAllImageById(InsectId);
    }
}
