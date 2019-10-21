package com.frode.trade.service;

import com.frode.trade.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList);
    ProductCategory save(ProductCategory productCategory);


}
