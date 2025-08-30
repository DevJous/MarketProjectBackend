package com.market.project.repository.stock;

import com.market.project.model.StockAssignModel;
import com.market.project.model.StockLocalModel;
import com.market.project.model.StockPerLocalModel;
import java.util.List;

public interface IStockRepository {
    List<StockLocalModel> getAll();
    StockLocalModel assignProduct(StockAssignModel stock);
    StockPerLocalModel byLocalId(Long id);
    void setSell(StockAssignModel stock);
    void setStock(StockAssignModel stock);
}
