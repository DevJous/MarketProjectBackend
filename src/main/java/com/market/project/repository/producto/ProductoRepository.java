package com.market.project.repository.producto;

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
            CallableStatement cs = con.prepareCall("{call Sp_GetProductos(?, ?, ?)}");
            cs.setString(1, "GetAll");
            cs.setLong(2, 0); // No se requiere enviar ID
            cs.registerOutParameter(3, Types.REF_CURSOR);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(3);
            List<ProductoModel> productos = new ArrayList<>();

            while (rs.next()) {
                System.out.println(rs.getString("NOMBRE"));
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
    
    /*@Override
    public List<ProductoModel> GetAll() {
        String sql = """
        SELECT DISTINCT
            p.id_producto,
            p.nombre,
            p.precio,
            p.fecha_reg,
            p.id_categoria,
            c.nombre as nombre_categoria
        FROM PRODUCTOS p 
        JOIN CATEGORIAS c ON p.id_categoria = c.id_categoria
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProductoModel producto = new ProductoModel();
            producto.setIdProducto(rs.getLong("id_producto"));
            producto.setNombre(rs.getString("nombre"));
            producto.setPrecio(rs.getBigDecimal("precio"));
            producto.setFechaReg(rs.getDate("fecha_reg"));
            producto.setIdCategoria(rs.getLong("id_categoria"));
            producto.setNombreCategoria(rs.getString("nombre_categoria"));
            return producto;
        });
    */
}
