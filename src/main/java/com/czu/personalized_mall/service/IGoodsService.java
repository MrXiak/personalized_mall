package com.czu.personalized_mall.service;

import com.czu.personalized_mall.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
//import com.czu.personalized_mall.vo.TableDataVO;
//import com.czu.personalized_mall.vo.TableProductVO;

import java.util.List;

public interface IGoodsService extends IService<Goods> {
    public List<Goods> findByCategoryId(String type,Integer categoryId);

    /**
     * 后台管理系统返回商品数据
     */
//    public TableDataVO<TableProductVO> findAllTableData(Integer page,Integer limit);
}
