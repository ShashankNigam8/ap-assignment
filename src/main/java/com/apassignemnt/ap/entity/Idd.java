package com.apassignemnt.ap.entity;

import lombok.Data;

import java.util.List;

@Data
public class Idd {
    private String root;
    private List<String> suffixes;
}
