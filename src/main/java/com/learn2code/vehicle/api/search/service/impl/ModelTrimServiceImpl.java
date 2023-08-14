package com.learn2code.vehicle.api.search.service.impl;

import com.learn2code.vehicle.api.search.dao.ModelDAO;
import com.learn2code.vehicle.api.search.dao.TrimTypeDAO;
import com.learn2code.vehicle.api.search.entity.Model;
import com.learn2code.vehicle.api.search.entity.TrimType;
import com.learn2code.vehicle.api.search.service.ModelTrimService;
import org.springframework.stereotype.Service;

@Service
public class ModelTrimServiceImpl implements ModelTrimService {
    private ModelDAO modelDAO;
    private TrimTypeDAO trimTypeDAO;

    public ModelTrimServiceImpl(ModelDAO modelDAO, TrimTypeDAO trimTypeDAO) {
        this.modelDAO = modelDAO;
        this.trimTypeDAO = trimTypeDAO;
    }

    @Override
    public Model saveModel(Model model) {
        Model savedModel = modelDAO.save(model);
        return savedModel;
    }

    @Override
    public TrimType saveTrimType(TrimType trimType) {
        return trimTypeDAO.save(trimType);
    }
}
