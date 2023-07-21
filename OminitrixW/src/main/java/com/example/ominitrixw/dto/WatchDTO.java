package com.example.ominitrixw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WatchDTO {
    private String watchID;
    private String watchName;
    private double price;
    private boolean waterResistance;
    private String description;
    private double thickness;
    private boolean watchGender;
    private int voteLike;
    private int limitQuantity;
    private boolean status;
    private String colorID;
    private String typeID;
    private String brandID;
    private List<String> images;


}
