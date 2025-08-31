package com.market.project.controller;

import com.market.project.model.CategoriaModel;
import com.market.project.service.categoria.ICategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/categories")
public class CategoriaController {
    
    @Autowired
    private ICategoriaService categoriaService;
    
    @GetMapping("all")
    public ResponseEntity<List<CategoriaModel>> getAll() {
        return ResponseEntity.ok(categoriaService.getAll());
    }
}
