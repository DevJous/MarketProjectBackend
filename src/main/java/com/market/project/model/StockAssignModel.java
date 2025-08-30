package com.market.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockAssignModel {
    private Long idProducto;
    private Long idLocal; 
    private Integer stock;
    private Integer venta;
}
