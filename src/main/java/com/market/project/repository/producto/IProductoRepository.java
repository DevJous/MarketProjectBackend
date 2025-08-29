package com.market.project.repository.producto;

import com.market.project.model.ProductoModel;
import java.util.List;


public interface IProductoRepository {
    List<ProductoModel> GetAll();
    ProductoModel GetById(Long id);
    void updateProducto(ProductoModel producto);
    void insertProducto(ProductoModel producto);
}
