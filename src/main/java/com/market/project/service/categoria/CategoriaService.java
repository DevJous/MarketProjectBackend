package com.market.project.service.categoria;

import com.market.project.model.CategoriaModel;
import com.market.project.repository.categoria.ICategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService implements ICategoriaService {
    
    @Autowired
    private ICategoriaRepository categoriaRepository;
    
    @Override
    public List<CategoriaModel> getAll() {
        return categoriaRepository.getAll();
    }
}
