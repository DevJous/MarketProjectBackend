package com.market.project.repository.categoria;

import com.market.project.model.CategoriaModel;
import java.util.List;

public interface ICategoriaRepository {
    List<CategoriaModel> getAll();
}
