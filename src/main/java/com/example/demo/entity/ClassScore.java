package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ClassScore {
    private String coursename;
    private Double minscore;
    private Double maxscore;
    private Double averagescore;

}
