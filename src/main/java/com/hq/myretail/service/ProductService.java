
package com.hq.myretail.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hq.myretail.entity.Product;
import com.hq.myretail.exception.ProductNotFoundException;
import com.hq.myretail.helper.ProductHelper;
import com.hq.myretail.remotehttp.InteractHttpClient;
import com.hq.myretail.repository.ProductRepository;
import com.hq.myretail.vo.ProductVO;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    InteractHttpClient interactHttpClient;

    @Autowired
    ProductHelper productHelper;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public ProductVO getProductDetailsById(String productId) {
        logger.info("Inside ProductService().getProductDetailsById : {} ", productId);
        Product product = null;
        ProductVO productVO = null;

        product = productRepository.findProductByProductId(productId);

        if (null == product) {
            logger.info("Product Not Found Exception while fetching product data");
            throw new ProductNotFoundException();
        }

        String productTitle;
        try {
            productTitle = getProductTitle(productId);
        } catch (final IOException e) {
            logger.info("Product title not available : {}", e);
            throw new ProductNotFoundException();
        }

        productVO = productHelper.convertToProductVOObject(product, productTitle);

        return productVO;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private String getProductTitle(String productId) throws JsonParseException, JsonMappingException, IOException {
        final Map<String, Map> productInfo = interactHttpClient.getProductInfoFromRemote(productId);

        if (!productInfo.isEmpty()) {
            final Map<String, Map> productMap = productInfo.get("product");
            final Map<String, Map> itemMap = productMap.get("item");
            final Map<String, String> productDescriptionMap = itemMap.get("product_description");

            return productDescriptionMap.get("title");
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * @param productVO
     */
    public void updateProductDetails(ProductVO productVO) {
        final Product product = new Product();
        product.setProductId(productVO.getProductId());
        product.setCurrentPrice(productVO.getCurrentPrice());
        productRepository.save(product);

    }

}
