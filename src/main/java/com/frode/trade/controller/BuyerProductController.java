package com.frode.trade.controller;

import com.frode.trade.dataobject.ProductCategory;
import com.frode.trade.dataobject.ProductInfo;
import com.frode.trade.service.CategoryService;
import com.frode.trade.service.ProductService;
import com.frode.trade.utils.ResultVoUtil;
import com.frode.trade.vo.ProductInfoVo;
import com.frode.trade.vo.ProductVo;
import com.frode.trade.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public ResultVo list() {
        //1查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2查询类目（一次性查询）
//        List<Integer> categoryList = new ArrayList<>();
        //传统方法
//        for(ProductInfo productInfo:productInfoList){
//            categoryList.add(productInfo.getCategoryType())
//        }
        //精简lambda
        List<Integer> categoryList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryList);
        //3数据拼装
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
        return  ResultVoUtil.success(productVoList);
    }
}
