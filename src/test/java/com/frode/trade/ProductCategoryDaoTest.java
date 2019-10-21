package com.frode.trade;

import com.frode.trade.dao.ProductCategoryRepository;
import com.frode.trade.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {
    @Autowired
    private ProductCategoryRepository productCategoryDao;

    @Test
    public void findOne(){
        ProductCategory productCategory =  productCategoryDao.findById(1).orElse(new ProductCategory());
    }

    @Test
    public void save(){
        ProductCategory productCategory =  new ProductCategory();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(3);
        productCategoryDao.save(productCategory);
    }
    @Test
    public void update(){
        ProductCategory productCategory =  productCategoryDao.findById(2).orElse(new ProductCategory());
        productCategory.setCategoryType(10);
//        ProductCategory productCategory =  new ProductCategory();
//        productCategory.setCategoryId(2);
//        productCategory.setCategoryName("男生最爱");
//        productCategory.setCategoryType(3);
        productCategoryDao.save(productCategory);
    }
    @Test
    public void testList(){
        List<Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory> productCategoryList = productCategoryDao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,productCategoryList.size());
    }
}
