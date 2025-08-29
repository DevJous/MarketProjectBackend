package com.market.project.service.producto;

import com.market.project.model.ProductoModel;
import java.util.List;

public interface IProductoService {
    List<ProductoModel> GetAll();
    ProductoModel GetById(Long id);
    void updateProducto(ProductoModel producto);
    void insertProducto(ProductoModel producto);
}
