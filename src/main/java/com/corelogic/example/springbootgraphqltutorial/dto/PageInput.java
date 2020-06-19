package com.corelogic.example.springbootgraphqltutorial.dto;

import lombok.Data;

@Data
public class PageInput {
    private Integer pageNumber;
    private Integer pageSize;
}
