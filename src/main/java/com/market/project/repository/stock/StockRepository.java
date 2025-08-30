package com.market.project.repository.stock;

import com.market.project.common.constants.SpNames;
import com.market.project.common.utils.StringUtils;
import com.market.project.model.StockAssignModel;
import com.market.project.model.StockLocalModel;
import com.market.project.model.StockPerLocalModel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StockRepository implements IStockRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<StockLocalModel> getAll() {
        return jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall(StringUtils.GetSpFormat(SpNames.Sp_ManageStockLocal, 7));
            cs.setString(1, "LIST");
            cs.setNull(2, Types.BIGINT);
            cs.setLong(3, 0);
            cs.setLong(4, 0);
            cs.setInt(5, 0);
            cs.setInt(6, 0);
            cs.registerOutParameter(7, Types.REF_CURSOR);

            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(7);

            List<StockLocalModel> stocks = new ArrayList();
            if (rs.next()) {
                StockLocalModel sl = new StockLocalModel();
                sl.setIdStock(rs.getLong("ID_STOCK"));
                sl.setIdProducto(rs.getLong("ID_PRODUCTO"));
                sl.setIdLocal(rs.getLong("ID_LOCAL"));
                sl.setStock(rs.getInt("STOCK"));
                sl.setVentas(rs.getInt("VENTAS"));
                stocks.add(sl);
            }
            rs.close();
            cs.close();
            return stocks;
        });
    }

    @Override
    public StockLocalModel assignProduct(StockAssignModel stock) {
        return jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall(StringUtils.GetSpFormat(SpNames.Sp_ManageStockLocal, 7));
            cs.setString(1, "ASSIGN");
            cs.setNull(2, Types.BIGINT);
            cs.setLong(3, stock.getIdProducto());
            cs.setLong(4, stock.getIdLocal());
            cs.setInt(5, stock.getStock());
            cs.setInt(6, 0);
            cs.registerOutParameter(7, Types.REF_CURSOR);

            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(7);

            StockLocalModel sl = new StockLocalModel();
            if (rs.next()) {
                sl.setIdStock(rs.getLong("ID_STOCK"));
                sl.setIdProducto(rs.getLong("ID_PRODUCTO"));
                sl.setIdLocal(rs.getLong("ID_LOCAL"));
                sl.setStock(rs.getInt("STOCK"));
                sl.setVentas(rs.getInt("VENTAS"));
            }
            rs.close();
            cs.close();
            return sl;
        });
    }
    
    @Override
    public StockPerLocalModel byLocalId(Long id) {
        return jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall(StringUtils.GetSpFormat(SpNames.Sp_ManageStockLocal, 7));
            cs.setString(1, "GETBYIDLOCAL");
            cs.setNull(2, Types.BIGINT);
            cs.setLong(3, 0);
            cs.setLong(4, id);
            cs.setInt(5, 0);
            cs.setInt(6, 0);
            cs.registerOutParameter(7, Types.REF_CURSOR);

            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(7);

            StockPerLocalModel spl = new StockPerLocalModel();
            if (rs.next()) {
                spl.setIdProducto(rs.getLong("ID_PRODUCTO"));
                spl.setNombre(rs.getString("NOMBRE"));
                spl.setStock(rs.getInt("STOCK"));
            }
            rs.close();
            cs.close();
            return spl;
        });
    }
    
    @Override
    public void setSell(StockAssignModel stock) {
        jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall(StringUtils.GetSpFormat(SpNames.Sp_ManageStockLocal, 7));
            cs.setString(1, "SETSELL");
            cs.setNull(2, Types.BIGINT);
            cs.setLong(3, stock.getIdProducto());
            cs.setLong(4, stock.getIdLocal());
            cs.setInt(5, 0);
            cs.setInt(6, stock.getVenta());
            cs.registerOutParameter(7, Types.REF_CURSOR);

            cs.execute();
            return null;
        });
    }
    
    @Override
    public void setStock(StockAssignModel stock) {
        jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall(StringUtils.GetSpFormat(SpNames.Sp_ManageStockLocal, 7));
            cs.setString(1, "SETSELL");
            cs.setNull(2, Types.BIGINT);
            cs.setLong(3, stock.getIdProducto());
            cs.setLong(4, stock.getIdLocal());
            cs.setInt(5, stock.getStock());
            cs.setInt(6, 0);
            cs.registerOutParameter(7, Types.REF_CURSOR);

            cs.execute();
            return null;
        });
    }
}
