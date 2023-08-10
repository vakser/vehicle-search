package com.learn2code.vehicle.api.search.service.impl;

import com.learn2code.vehicle.api.search.dao.ManufacturerDAO;
import com.learn2code.vehicle.api.search.entity.Manufacturer;
import com.learn2code.vehicle.api.search.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerDAO manufacturerDAO;

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

    @Override
    public Manufacturer getManufacturerForId(int id) {
        Optional<Manufacturer> dbManufacturer = manufacturerDAO.findById(id);
        return dbManufacturer.orElse(null);
    }

    @Override
    public Manufacturer updateManufacturer(int id, Manufacturer updatedManufacturer) {
        Manufacturer dbManufacturer = getManufacturerForId(id);
        if (dbManufacturer != null && Objects.nonNull(updatedManufacturer)) {
            if (Objects.nonNull(updatedManufacturer.getManufacturerName()) && !"".equalsIgnoreCase(updatedManufacturer.getManufacturerName())) {
                dbManufacturer.setManufacturerName(updatedManufacturer.getManufacturerName());
            }
            if (Objects.nonNull(updatedManufacturer.getCountryOfOrigin()) && !"".equalsIgnoreCase(updatedManufacturer.getCountryOfOrigin())) {
                dbManufacturer.setCountryOfOrigin(updatedManufacturer.getCountryOfOrigin());
            }
            return manufacturerDAO.save(dbManufacturer);
        }
        return dbManufacturer;
    }
}
