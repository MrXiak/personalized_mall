package com.czu.personalized_mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czu.personalized_mall.entity.Goods;
import com.czu.personalized_mall.entity.GoodsCategory;
import com.czu.personalized_mall.mapper.GoodsCategoryMapper;
import com.czu.personalized_mall.mapper.GoodsMapper;
import com.czu.personalized_mall.service.IGoodsCategoryService;
import com.czu.personalized_mall.vo.GoodsCategoryVO;
import com.czu.personalized_mall.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IGoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements IGoodsCategoryService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsCategoryVO> getAllGoodsCategoryVO() {
        //一级分类
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("type", 1);
        List<GoodsCategory> levelOne = goodsCategoryMapper.selectList(wrapper);
//        List<GoodsCategoryVO> levelOneVO=new ArrayList<>();
//        for (GoodsCategory goodsCategory : levelOne) {
//            GoodsCategoryVO goodsCategoryVO=new GoodsCategoryVO();
//            BeanUtils.copyProperties(goodsCategory,goodsCategoryVO);
//            levelOneVO.add(goodsCategoryVO);
//        }
        List<GoodsCategoryVO> levelOneVO = levelOne.stream().map(e -> new GoodsCategoryVO(e.getId(), e.getName())).collect(Collectors.toList());
        //图片赋值
        //商品信息赋值
        for (int i = 0; i < levelOneVO.size(); i++) {
            levelOneVO.get(i).setBannerImg("/images/banner" + i + ".png");
            levelOneVO.get(i).setTopImg("/images/top" + i + ".png");
            wrapper = new QueryWrapper();
            wrapper.eq("goods_first", levelOneVO.get(i).getId());
            List<Goods> goodsList = goodsMapper.selectList(wrapper);
            List<GoodsVO> goodsVOList = goodsList.stream()
                    .map(e -> new GoodsVO(
                            e.getId(),
                            e.getName(),
                            e.getPrice(),
                            e.getFileName()
                    )).collect(Collectors.toList());
            levelOneVO.get(i).setGoodsVOList(goodsVOList);
        }

        for (GoodsCategoryVO levelOnegoodsCategoryVO : levelOneVO) {
            wrapper = new QueryWrapper();
            wrapper.eq("type", 2);
            wrapper.eq("parent_id", levelOnegoodsCategoryVO.getId());
            List<GoodsCategory> levelTwo = goodsCategoryMapper.selectList(wrapper);
            List<GoodsCategoryVO> levelTwoVO = levelTwo.stream().map(e -> new GoodsCategoryVO(e.getId(), e.getName())).collect(Collectors.toList());
            levelOnegoodsCategoryVO.setChildren(levelTwoVO);
            for (GoodsCategoryVO levelTwoGoodscategoryVO : levelTwoVO) {
                wrapper = new QueryWrapper();
                wrapper.eq("type", 3);
                wrapper.eq("parent_id", levelTwoGoodscategoryVO.getId());
                List<GoodsCategory> levelThree = goodsCategoryMapper.selectList(wrapper);
                List<GoodsCategoryVO> levelThreeVO = levelThree.stream().map(e -> new GoodsCategoryVO(e.getId(), e.getName())).collect(Collectors.toList());
                levelTwoGoodscategoryVO.setChildren(levelThreeVO);
            }
        }
        return levelOneVO;
    }
}
