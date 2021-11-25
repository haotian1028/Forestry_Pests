package com.insect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Family {
    private String FamilyId;
    private String FamilyName;
    private Order Order;

}
