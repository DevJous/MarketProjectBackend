package com.market.project.repository.local;

import com.market.project.model.LocalModel;
import java.util.List;

public interface ILocalRepository {
    LocalModel insertLocal(LocalModel local);
    List<LocalModel> GetAll();
}
