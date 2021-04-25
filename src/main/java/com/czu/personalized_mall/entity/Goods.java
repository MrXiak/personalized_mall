package com.czu.personalized_mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public
class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 价格
     */
    private Float  price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 分类1
     */
    private Integer goodsFirst;

    /**
     * 分类2
     */
    private Integer goodsSecond;

    /**
     * 分类3
     */
    private Integer goodsThrid;

    /**
     * 文件名称
     */
    private String fileName;
}
