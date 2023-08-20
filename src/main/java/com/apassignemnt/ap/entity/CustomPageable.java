package com.apassignemnt.ap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomPageable {

    private int page;
    private int size;
    private String sortOrder;

    public int getOffset() {
        return page * size;
    }

}
