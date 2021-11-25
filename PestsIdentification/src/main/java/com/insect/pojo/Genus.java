package com.insect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genus {
    private String GenusId;
    private String GenusName;
    private Family Family;
}
