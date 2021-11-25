package com.insect.service;

import com.insect.pojo.Genus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GenusService {
    List<Genus> searchAllGenus();
    Genus searchGenusById(String id);
    Genus searchGenusByName(String name);
    List<Genus> searchGenusByFamilyId(String id);
    void addGenus(String GenusId,String GenusName,String FamilyId);
    void deleteGenus(String GenusId);
    void updateGenus(String GenusId,String GenusName,String FamilyId);

}
