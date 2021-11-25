package com.insect.mapper;

import com.insect.pojo.Genus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GenusMapper {
    List<Genus> searchAllGenus();
    Genus searchGenusById(@Param("id") String id);
    Genus searchGenusByName(@Param("name") String name);
    List<Genus> searchGenusByFamilyId(@Param("id")String id);
    void addGenus(@Param("GenusId") String GenusId,@Param("GenusName") String GenusName,@Param("FamilyId") String FamilyId);
    void deleteGenus(@Param("GenusId") String GenusId);
    void updateGenus(@Param("GenusId") String GenusId,@Param("GenusName") String GenusName,@Param("FamilyId") String FamilyId);
}
