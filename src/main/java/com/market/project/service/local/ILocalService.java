package com.market.project.service.local;

import com.market.project.model.LocalModel;
import java.util.List;

public interface ILocalService {
    LocalModel insertLocal(LocalModel local);
    List<LocalModel> GetAll();
}
