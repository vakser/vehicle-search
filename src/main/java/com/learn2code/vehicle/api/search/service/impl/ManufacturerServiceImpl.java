package com.learn2code.vehicle.api.search.service.impl;

import com.learn2code.vehicle.api.search.dao.ManufacturerDAO;
import com.learn2code.vehicle.api.search.entity.Manufacturer;
import com.learn2code.vehicle.api.search.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private ManufacturerDAO manufacturerDAO;

    public ManufacturerServiceImpl(ManufacturerDAO manufacturerDAO) {
        this.manufacturerDAO = manufacturerDAO;
    }

    @Override
    public Manufacturer saveManufacturer(Manufacturer manufacturer) {
        return manufacturerDAO.save(manufacturer);
    }

    @Override
    public List<Manufacturer> fetchAllManufacturers() {
        return manufacturerDAO.findAll();
    }
}
