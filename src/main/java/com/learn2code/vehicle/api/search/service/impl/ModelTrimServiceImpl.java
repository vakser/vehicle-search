package com.learn2code.vehicle.api.search.service.impl;

import com.learn2code.vehicle.api.search.dao.ManufacturerDAO;
import com.learn2code.vehicle.api.search.dao.ModelDAO;
import com.learn2code.vehicle.api.search.dao.TrimTypeDAO;
import com.learn2code.vehicle.api.search.entity.Manufacturer;
import com.learn2code.vehicle.api.search.entity.Model;
import com.learn2code.vehicle.api.search.entity.TrimType;
import com.learn2code.vehicle.api.search.exception.ManufacturerNotFoundException;
import com.learn2code.vehicle.api.search.exception.ModelNotFoundException;
import com.learn2code.vehicle.api.search.exception.TrimTypeNotFoundException;
import com.learn2code.vehicle.api.search.service.ModelTrimService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ModelTrimServiceImpl implements ModelTrimService {
    private final ModelDAO modelDAO;
    private final TrimTypeDAO trimTypeDAO;
    private final ManufacturerDAO manufacturerDAO;

    public ModelTrimServiceImpl(ModelDAO modelDAO, TrimTypeDAO trimTypeDAO, ManufacturerDAO manufacturerDAO) {
        this.modelDAO = modelDAO;
        this.trimTypeDAO = trimTypeDAO;
        this.manufacturerDAO = manufacturerDAO;
    }

    @Override
    public Model saveModel(Model model) {
        return modelDAO.save(model);
    }

    @Override
    public TrimType saveTrimType(TrimType trimType) {
        return trimTypeDAO.save(trimType);
    }

    @Override
    public List<Model> getAllModels() {
        return modelDAO.findAll();
    }

    @Override
    public Model modifyModel(int id, Model model) throws ModelNotFoundException {
        Model dbModel = getModelById(id);
        if (dbModel != null && Objects.nonNull(model)) {
            if (Objects.nonNull(model.getModelName()) && !"".equalsIgnoreCase(model.getModelName())) {
                dbModel.setModelName(model.getModelName());
            }
            dbModel = modelDAO.save(dbModel);
        }
        return dbModel;
    }

    @Override
    public TrimType modifyTrimType(int id, TrimType trimType) throws TrimTypeNotFoundException {
        TrimType dbTrimType = getTrimTypeById(id);
        if (dbTrimType != null && Objects.nonNull(trimType)) {
            if (Objects.nonNull(trimType.getTrimType()) && !"".equalsIgnoreCase(trimType.getTrimType())) {
                dbTrimType.setTrimType(trimType.getTrimType());
            }
            dbTrimType = trimTypeDAO.save(dbTrimType);
        }
        return dbTrimType;
    }

    @Override
    public Model getModelById(int id) throws ModelNotFoundException {
        Optional<Model> dbModel = modelDAO.findById(id);
        if (!dbModel.isPresent()) {
            throw new ModelNotFoundException("No model found with id " + id);
        }
        return dbModel.get();
    }

    @Override
    public TrimType getTrimTypeById(int id) throws TrimTypeNotFoundException {
        Optional<TrimType> dbTrimType = trimTypeDAO.findById(id);
        if (!dbTrimType.isPresent()) {
            throw new TrimTypeNotFoundException("No trim type found with id " + id);
        }
        return dbTrimType.get();
    }

    @Override
    public void deleteModelById(int id) throws ModelNotFoundException {
        Model dbModel = getModelById(id);
        try {
            modelDAO.deleteById(id);
        } catch (Exception e) {
            System.out.println("***** Unable to delete model. Check DB connection. *****" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTrimType(int id) throws TrimTypeNotFoundException {
        TrimType dbTrim = getTrimTypeById(id);
        try {
            trimTypeDAO.deleteById(id);
        } catch (Exception e) {
            System.out.println("***** Unable to delete trim type. Check DB connection. *****" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Model> getModelsByManufacturerId(int manufacturerId) throws ManufacturerNotFoundException {
        Optional<Manufacturer> dbManufacturer = manufacturerDAO.findById(manufacturerId);
        if (!dbManufacturer.isPresent()) {
            throw new ManufacturerNotFoundException("No manufacturer found for id " + manufacturerId);
        }
        return modelDAO.findByManufacturer(dbManufacturer.get());
    }

    @Override
    public List<Model> getModelsByManufacturerName(String name) throws ManufacturerNotFoundException {
        Manufacturer dbManufacturer = manufacturerDAO.findByManufacturerName(name);
        if (dbManufacturer == null) {
            throw new ManufacturerNotFoundException("No manufacturer found for name " + name);
        }
        int manufacturerId = dbManufacturer.getId();
        return modelDAO.fetchModelsBasedOnManufacturerId(manufacturerId);
    }
}
