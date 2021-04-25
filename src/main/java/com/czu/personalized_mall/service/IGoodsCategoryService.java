package com.czu.personalized_mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czu.personalized_mall.entity.GoodsCategory;
import com.czu.personalized_mall.vo.GoodsCategoryVO;

import java.util.List;

public interface IGoodsCategoryService extends IService<GoodsCategory> {
    public List<GoodsCategoryVO> getAllGoodsCategoryVO();
}
