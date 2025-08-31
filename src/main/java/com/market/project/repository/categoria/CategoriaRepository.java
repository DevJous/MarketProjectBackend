package com.market.project.repository.categoria;

import com.market.project.common.constants.SpNames;
import com.market.project.common.utils.StringUtils;
import com.market.project.model.CategoriaModel;
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
public class CategoriaRepository implements ICategoriaRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<CategoriaModel> getAll() {
        return jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall(StringUtils.GetSpFormat(SpNames.Sp_GetCategoria, 2));
            cs.setString(1, "GetAll");
            cs.registerOutParameter(2, Types.REF_CURSOR);

            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(2);

            List<CategoriaModel> categorias = new ArrayList();
            while (rs.next()) {
                CategoriaModel categoria = new CategoriaModel();
                categoria.setIdCategoria(rs.getLong("ID_CATEGORIA"));
                categoria.setNombre(rs.getString("NOMBRE"));
                categorias.add(categoria);
            }
            rs.close();
            cs.close();
            return categorias;
        });
    }
}
