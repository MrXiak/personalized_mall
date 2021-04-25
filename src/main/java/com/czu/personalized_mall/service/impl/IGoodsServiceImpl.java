package com.czu.personalized_mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czu.personalized_mall.entity.Goods;
import com.czu.personalized_mall.entity.GoodsCategory;
import com.czu.personalized_mall.mapper.GoodsCategoryMapper;
import com.czu.personalized_mall.mapper.GoodsMapper;
import com.czu.personalized_mall.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czu.personalized_mall.vo.TableDataVO;
import com.czu.personalized_mall.vo.TableGoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IGoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public List<Goods> findByCategoryId(String type,Integer id) {
        Map<String,Object> map = new HashMap<>();
        map.put("goods"+"_"+type,id);
        return goodsMapper.selectByMap(map);
    }

//    public TableDataVO<TableGoodsVO> findAllTableData(Integer page, Integer limit) {
//        IPage<Goods> goodsIPage = new Page<>(page,limit);
//        IPage<Goods> result = goodsMapper.selectPage(goodsIPage,null);
//        List<Goods> goodsList = result.getRecords();
//        List<TableGoodsVO> tableProductVOList = new ArrayList<>();
//        QueryWrapper wrapper;
//        GoodsCategory productCategory;
//        for (Goods goods : goodsList) {
//            TableGoodsVO tableProductVO = new TableGoodsVO();
//            BeanUtils.copyProperties(goods,tableProductVO);
//            wrapper = new QueryWrapper();
//            wrapper.eq("id",goods.getGoodsFirst());
//            productCategory = goodsCategoryMapper.selectOne(wrapper);
//            if(productCategory != null){
//                tableProductVO.setLevelOne(productCategory.getName());
//            }
//
//
//            wrapper = new QueryWrapper();
//            wrapper.eq("id",goods.getGoodsSecond());
//            productCategory = goodsCategoryMapper.selectOne(wrapper);
//            if(productCategory != null){
//                tableProductVO.setLevelTwo(productCategory.getName());
//            }
//
//
//            wrapper = new QueryWrapper();
//            wrapper.eq("id",goods.getGoodsThrid());
//            productCategory = goodsCategoryMapper.selectOne(wrapper);
//            if(productCategory != null){
//                tableProductVO.setLevelThree(productCategory.getName());
//            }
//            tableProductVOList.add(tableProductVO);
//        }
//        TableDataVO tableDataVO = new TableDataVO();
//        tableDataVO.setCode(0);
//        tableDataVO.setMsg("");
//        tableDataVO.setData(tableProductVOList);
//        tableDataVO.setCount(result.getTotal());
//        return tableDataVO;
//    }
}
