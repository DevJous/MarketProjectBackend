package com.market.project.repository.producto;

import com.market.project.common.constants.SpNames;
import com.market.project.common.utils.StringUtils;
import java.sql.Types;
import com.market.project.model.ProductoModel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoRepository implements IProductoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ProductoModel> GetAll() {
        return jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall(StringUtils.GetSpFormat(SpNames.Sp_GetProductos, 3));
            cs.setString(1, "GetAll");
            cs.setLong(2, 0); // No se requiere enviar ID
            cs.registerOutParameter(3, Types.REF_CURSOR);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(3);
            List<ProductoModel> productos = new ArrayList<>();

            while (rs.next()) {
                ProductoModel producto = new ProductoModel();
                producto.setIdProducto(rs.getLong("ID_PRODUCTO"));
                producto.setNombre(rs.getString("NOMBRE"));
                producto.setPrecio(rs.getBigDecimal("PRECIO"));
                producto.setFechaReg(rs.getDate("FECHA_REG"));
                producto.setIdCategoria(rs.getLong("ID_CATEGORIA"));
                producto.setNombreCategoria(rs.getString("NOMBRE_CATEGORIA"));

                productos.add(producto);
            }

            rs.close();
            cs.close();
            return productos;
        });
    }

    @Override
    public ProductoModel GetById(Long id) {
        return jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall(StringUtils.GetSpFormat(SpNames.Sp_GetProductos, 3));
            cs.setString(1, "GetById");
            cs.setLong(2, id);
            cs.registerOutParameter(3, Types.REF_CURSOR);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(3);
            ProductoModel producto = new ProductoModel();

            while (rs.next()) {
                producto.setIdProducto(rs.getLong("ID_PRODUCTO"));
                producto.setNombre(rs.getString("NOMBRE"));
                producto.setPrecio(rs.getBigDecimal("PRECIO"));
                producto.setFechaReg(rs.getDate("FECHA_REG"));
                producto.setIdCategoria(rs.getLong("ID_CATEGORIA"));
                producto.setNombreCategoria(rs.getString("NOMBRE_CATEGORIA"));
            }
            rs.close();
            cs.close();

            return producto;
        });
    }

    @Override
    public void updateProducto(ProductoModel producto) {
        jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall(StringUtils.GetSpFormat(SpNames.Sp_UpdateProductos, 4));
            cs.setLong(1, producto.getIdProducto());
            cs.setString(2, producto.getNombre());
            cs.setBigDecimal(3, producto.getPrecio());
            cs.setLong(4, producto.getIdCategoria());

            cs.execute();
            cs.close();
            return null;
        });
    }
    
    @Override
    public void insertProducto(ProductoModel producto) {
        jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall(StringUtils.GetSpFormat(SpNames.Sp_InsertProductos, 3));
            cs.setString(1, producto.getNombre());
            cs.setBigDecimal(2, producto.getPrecio());
            cs.setLong(3, producto.getIdCategoria());

            cs.execute();
            cs.close();
            return null;
        });
    }
}
