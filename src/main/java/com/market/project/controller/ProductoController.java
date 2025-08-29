package com.market.project.controller;

import com.market.project.common.constants.Messages;
import com.market.project.model.ProductoModel;
import com.market.project.service.producto.IProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @GetMapping("id/{id}")
    public ProductoModel GetById(@PathVariable Long id) {
        return productoService.GetById(id);
    }
    
    @PostMapping("add")
    public String InsertProducto(@RequestBody ProductoModel producto) {
        productoService.insertProducto(producto);
        return Messages.InsertedProduct;
    }
    
    @PutMapping("update")
    public String UpdateProducto(@RequestBody ProductoModel producto) {
        productoService.updateProducto(producto);
        return Messages.UpdatedProduct;
    }
}
