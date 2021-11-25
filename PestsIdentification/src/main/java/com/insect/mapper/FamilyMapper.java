package com.insect.mapper;

import com.insect.pojo.Family;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FamilyMapper {
    List<Family> searchAllFamily();
    Family queryFamilyById(@Param("FamilyId") String id);
    Family queryFamilyByName(@Param("FamilyName") String name);
    List<Family> searchFamilyByOrderId(@Param("OrderId") String orderId);
    void addFamily(@Param("familyId") String FamilyId,@Param("familyName") String FamilyName,@Param("orderId") String OrderId);
    void deleteFamilyById(@Param("FamilyId") String id);
    void updateFamily(@Param("familyId") String FamilyId,@Param("familyName") String FamilyName,@Param("orderId") String OrderId);
}
