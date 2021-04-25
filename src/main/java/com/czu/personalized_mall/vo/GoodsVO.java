package com.czu.personalized_mall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoodsVO {
    private Integer id;
    private String name;
    private Float price;
    private String fileName;
}
