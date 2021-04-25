package com.czu.personalized_mall.vo;

import lombok.Data;

@Data
public class TableGoodsVO {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Integer stock;
    private String levelOne;
    private String levelTwo;
    private String levelThree;
    private String fileName;
}
