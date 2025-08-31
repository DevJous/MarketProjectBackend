package com.market.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockLocalModel {
    private Long idStock;
    private Long idProducto;
    private Long idLocal;
    private String Producto;
    private String Local;
    private Integer stock;
    private Integer ventas;
}
