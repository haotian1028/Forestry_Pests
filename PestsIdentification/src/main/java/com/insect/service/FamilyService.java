package com.insect.service;

import com.insect.pojo.Family;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FamilyService {
    List<Family> searchAllFamily();
    Family queryFamilyById(String id);
    Family queryFamilyByName(String name);
    List<Family> searchFamilyByOrderId(String orderId);
    void addFamily(String FamilyId,String FamilyName,String OrderId);
    void deleteFamilyById(String id);
    void updateFamily(String FamilyId,String FamilyName,String OrderId);



}
