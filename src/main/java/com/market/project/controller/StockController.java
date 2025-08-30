package com.market.project.controller;

import com.market.project.common.constants.Messages;
import com.market.project.model.StockAssignModel;
import com.market.project.model.StockLocalModel;
import com.market.project.model.StockPerLocalModel;
import com.market.project.service.stock.IStockService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private IStockService stockService;
    
    @GetMapping("all")
    public List<StockLocalModel> getAll() {
        return stockService.getAll();
    }
    
    @GetMapping("byLocalId/{id}")
    public StockPerLocalModel byLocalId(Long id) {
        return stockService.byLocalId(id);
    }
    
    @PostMapping("assign")
    public StockLocalModel assignProduct(@RequestBody StockAssignModel stock) {
        return stockService.assignProduct(stock);
    }
    
    @PutMapping("setsell")
    public ResponseEntity<String> setSell(StockAssignModel stock) {
        stockService.setSell(stock);
        return ResponseEntity.ok(Messages.UpdatedSell);
    }
    
    @PutMapping("setstock")
    public ResponseEntity<String> setStock(StockAssignModel stock) {
        stockService.setStock(stock);
        return ResponseEntity.ok(Messages.UpdatedStock);
    }
}

