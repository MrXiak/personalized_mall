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
public class Lease implements Serializable {
    private String leaseId;
    private String goodsId;
    private String userId;
    private String leaseTime;
    private String returnTime;
}
