package com.frode.trade.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class ProductInfo {
    @Id
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    /*库存*/
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    /*0正常，1下架*/
    private Integer productStatus;
    private Integer categoryType;

}
