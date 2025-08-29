package com.market.project.controller;

import com.market.project.model.ProductoModel;
import com.market.project.service.producto.IProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    
    @Autowired
    private IProductoService productoService;
    
    @GetMapping("all")
    public List<ProductoModel> GetAll() {
        return productoService.GetAll();
    }
}
