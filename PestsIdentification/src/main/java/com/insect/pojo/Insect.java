package com.insect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insect {
    private String InsectId;
    private String InsectName;
    private String ScientificName;
    private Genus Genus;
    private String Crop;//分布的作物
    private String Area;//分布区域
    private Boolean IsPest;

}
