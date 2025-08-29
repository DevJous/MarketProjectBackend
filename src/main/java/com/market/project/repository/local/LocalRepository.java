package com.market.project.repository.local;

import com.market.project.model.LocalModel;
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
public class LocalRepository implements ILocalRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public LocalModel insertLocal(LocalModel local) {
        return jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall("{call Sp_ManageLocales(?, ?, ?, ?, ?)}");
            cs.setString(1, "INSERT");
            cs.setNull(2, Types.BIGINT);
            cs.setString(3, local.getNombre());
            cs.setString(4, local.getDireccion());
            cs.registerOutParameter(5, Types.REF_CURSOR);

            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(5);

            LocalModel nuevo = new LocalModel();
            if (rs.next()) {
                nuevo.setIdLocal(rs.getLong("ID_LOCAL"));
                nuevo.setNombre(rs.getString("NOMBRE"));
                nuevo.setDireccion(rs.getString("DIRECCION"));
            }
            rs.close();
            cs.close();
            return nuevo;
        });
    }
    
    @Override
    public List<LocalModel> GetAll() {
        return jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall("{call Sp_ManageLocales(?, ?, ?, ?, ?)}");
            cs.setString(1, "LIST");
            cs.setNull(2, Types.BIGINT);
            cs.registerOutParameter(5, Types.REF_CURSOR);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(5);
            List<LocalModel> locales = new ArrayList<>();

            while (rs.next()) {
                LocalModel local = new LocalModel();
                local.setIdLocal(rs.getLong("ID_LOCAL"));
                local.setNombre(rs.getString("NOMBRE"));
                local.setDireccion(rs.getString("DIRECCION"));

                locales.add(local);
            }

            rs.close();
            cs.close();
            return locales;
        });
    }
}
