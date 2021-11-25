package com.insect.service;

import com.insect.mapper.GenusMapper;
import com.insect.pojo.Genus;

import java.util.List;

public class GenusServiceImpl implements GenusService {
    private GenusMapper genusMapper;

    public void setGenusMapper(GenusMapper genusMapper) {
        this.genusMapper = genusMapper;
    }

    @Override
    public List<Genus> searchAllGenus() {
        return genusMapper.searchAllGenus();
    }

    @Override
    public Genus searchGenusById(String id) {
        return genusMapper.searchGenusById(id);
    }

    @Override
    public Genus searchGenusByName(String name) {
        return genusMapper.searchGenusByName(name);
    }

    @Override
    public List<Genus> searchGenusByFamilyId(String id) {
        return genusMapper.searchGenusByFamilyId(id);
    }

    @Override
    public void addGenus(String GenusId, String GenusName, String FamilyId) {
        genusMapper.addGenus(GenusId, GenusName, FamilyId);
    }

    @Override
    public void deleteGenus(String GenusId) {
        genusMapper.deleteGenus(GenusId);
    }

    @Override
    public void updateGenus(String GenusId, String GenusName, String FamilyId) {
        genusMapper.updateGenus(GenusId, GenusName, FamilyId);
    }
}
