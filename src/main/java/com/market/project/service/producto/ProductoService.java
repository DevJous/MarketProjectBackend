package com.market.project.service.producto;

import com.market.project.model.ProductoModel;
import com.market.project.repository.producto.IProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {
    
    @Autowired
    private IProductoRepository productoRepository;
    
    @Override
    public List<ProductoModel> GetAll() {
        return productoRepository.GetAll();
    }
    
    @Override
    public ProductoModel GetById(Long id) {
        return productoRepository.GetById(id);
    }
    
    @Override
    public void updateProducto(ProductoModel producto) {
        productoRepository.updateProducto(producto);
    }
    
    public void insertProducto(ProductoModel producto) {
        productoRepository.insertProducto(producto);
    }
}
