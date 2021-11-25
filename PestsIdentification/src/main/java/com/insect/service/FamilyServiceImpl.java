package com.insect.service;

import com.insect.mapper.FamilyMapper;
import com.insect.pojo.Family;

import java.util.List;

public class FamilyServiceImpl implements FamilyService {
    private FamilyMapper familyMapper;

    public void setFamilyMapper(FamilyMapper familyMapper) {
        this.familyMapper = familyMapper;
    }

    @Override
    public List<Family> searchAllFamily() {
        return familyMapper.searchAllFamily();
    }

    @Override
    public Family queryFamilyById(String id) {
        return familyMapper.queryFamilyById(id);
    }

    @Override
    public Family queryFamilyByName(String name) {
        return familyMapper.queryFamilyByName(name);
    }

    @Override
    public List<Family> searchFamilyByOrderId(String orderId) {
        return familyMapper.searchFamilyByOrderId(orderId);
    }

    @Override
    public void addFamily(String FamilyId,String FamilyName,String OrderId) {
        familyMapper.addFamily(FamilyId, FamilyName, OrderId);
    }

    @Override
    public void deleteFamilyById(String id) {
        familyMapper.deleteFamilyById(id);
    }

    @Override
    public void updateFamily(String FamilyId, String FamilyName, String OrderId) {
        familyMapper.updateFamily(FamilyId,FamilyName,OrderId);
    }
}
