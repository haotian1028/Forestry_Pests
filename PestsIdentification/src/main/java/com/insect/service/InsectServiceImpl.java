package com.insect.service;

import com.insect.mapper.InsectMapper;
import com.insect.pojo.Insect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public class InsectServiceImpl implements InsectService{
    private InsectMapper insectMapper;

    public void setInsectMapper(InsectMapper insectMapper) {
        this.insectMapper = insectMapper;
    }

    @Override
    public List<Insect> searchAllInsect(int n) {
        return insectMapper.searchAllInsect(n);
    }

    @Override
    public void addInsect(String InsectId, String InsectName, String ScientificName, String GenusId, String Crop, String Area) {
        insectMapper.addInsect(InsectId, InsectName, ScientificName, GenusId, Crop, Area);
    }


    @Override
    public Insect queryInsectById(String id) {
        return insectMapper.queryInsectById(id);
    }

    @Override
    public void deleteInsectById(String InsectId) {
        insectMapper.deleteInsectById(InsectId);
    }

    @Override
    public Insect queryInsectByName(String InsectName) {
        return insectMapper.queryInsectByName(InsectName);
    }

    @Override
    public Insect queryInsectByScientificName(String ScientificName) {
        return insectMapper.queryInsectByScientificName(ScientificName);
    }

    @Override
    public void updateInsect(String InsectId, String InsectName, String ScientificName, String GenusId, String Crop, String Area) {
        insectMapper.updateInsect(InsectId, InsectName, ScientificName, GenusId, Crop, Area);
    }

    @Override
    public List<Insect> searchInsectByGenusId(String GenusId) {
        return insectMapper.searchInsectByGenusId(GenusId);
    }

    @Override
    public List<Insect> searchInsectLike(String name) {
        return insectMapper.searchInsectLike(name);
    }

    @Override
    public List<Insect> searchAllPest() {
        return insectMapper.searchAllPest();
    }

}
