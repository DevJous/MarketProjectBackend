package com.market.project.service.stock;

import com.market.project.model.StockAssignModel;
import com.market.project.model.StockLocalModel;
import com.market.project.model.StockPerLocalModel;
import com.market.project.repository.stock.IStockRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService implements IStockService {
    
    @Autowired
    private IStockRepository stockRepository;
    
    @Override
    public List<StockLocalModel> getAll() {
        return stockRepository.getAll();
    }
    
    @Override
    public StockLocalModel assignProduct(StockAssignModel stock) {
        return stockRepository.assignProduct(stock);
    }
    
    @Override
    public StockPerLocalModel byLocalId(Long id) {
        return stockRepository.byLocalId(id);
    }
    
    @Override
    public void setSell(StockAssignModel stock) {
        stockRepository.setSell(stock);
    }
    
    @Override
    public void setStock(StockAssignModel stock) {
        stockRepository.setStock(stock);
    }
}
