package com.smart.sample.ws.soap;

import com.smart.plugin.ws.WebService;
import com.smart.sample.entity.Product;
import com.smart.sample.ws.soap.adapter.StringObjectMapAdapter;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@WebService(value = "/soap/ProductService", type = WebService.Type.SOAP)
public interface ProductService {

    List<Product> getProductList();

    Product getProduct(long productId);

    boolean createProduct(@XmlJavaTypeAdapter(StringObjectMapAdapter.class) Map<String, Object> productFieldMap);

    boolean updateProduct(long productId, @XmlJavaTypeAdapter(StringObjectMapAdapter.class) Map<String, Object> productFieldMap);

    boolean deleteProduct(long productId);
}