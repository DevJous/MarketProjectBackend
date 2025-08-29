package com.market.project.service.local;

import com.market.project.model.LocalModel;
import com.market.project.repository.local.ILocalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalService implements ILocalService {
    @Autowired
    private ILocalRepository localRepository;
    
    @Override
    public LocalModel insertLocal(LocalModel local) {
        return localRepository.insertLocal(local);
    }
    
    @Override
    public List<LocalModel> GetAll() {
        return localRepository.GetAll();
    }
}
