
package com.hq.myretail.helper;

import org.springframework.stereotype.Component;

import com.hq.myretail.entity.Product;
import com.hq.myretail.vo.ProductVO;

@Component
public class ProductHelper {

    public ProductVO convertToProductVOObject(Product product, String title) {
        final ProductVO productVO = new ProductVO();
        productVO.setProductId(product.getProductId());
        productVO.setName(title);
        productVO.setCurrentPrice(product.getCurrentPrice());

        return productVO;
    }
}
