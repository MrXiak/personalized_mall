package com.czu.personalized_mall.vo;

import lombok.Data;

import java.util.List;

@Data
public class GoodsCategoryVO {
    private Integer id;
    private String name;
    private List<GoodsCategoryVO> children;
    private String bannerImg;
    private String topImg;
    private List<GoodsVO> goodsVOList;

    public GoodsCategoryVO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
