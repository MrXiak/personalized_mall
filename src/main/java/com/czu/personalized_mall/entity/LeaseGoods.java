package com.czu.personalized_mall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LeaseGoods implements Serializable {
    private static final long serialVersionUID = 1L;
    private String goodsId;
    private String goodsName;
    private String goodsFirstKind;
    private String goodsSecondKind;
    private Integer goodsPrice;
    private Integer deposit;
    private Integer status;
    private String goodsPicture;
}




