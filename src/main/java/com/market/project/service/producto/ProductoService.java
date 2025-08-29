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
}
