package com.market.project.controller;

import com.market.project.common.constants.Messages;
import com.market.project.model.LocalModel;
import com.market.project.service.local.ILocalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/locals")
public class LocalController {
    
    @Autowired
    private ILocalService localService;
    
    @GetMapping("/all")
    public List<LocalModel> GetAll() {
        return localService.GetAll();
    }
    
    @PostMapping("/add")
    public String insertLocal(@RequestBody LocalModel local) {
        localService.insertLocal(local);
        return Messages.InsertedLocal;
    }
}
