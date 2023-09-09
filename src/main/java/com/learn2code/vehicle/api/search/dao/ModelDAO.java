package com.learn2code.vehicle.api.search.dao;

import com.learn2code.vehicle.api.search.entity.Manufacturer;
import com.learn2code.vehicle.api.search.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelDAO extends JpaRepository<Model, Integer> {
    List<Model> findByManufacturer(Manufacturer manufacturer);
    Model findByModelName(String name);

    @Query(value = "SELECT * FROM models WHERE manufacturer_id = ?1", nativeQuery = true)
    List<Model> fetchModelsBasedOnManufacturerId(int manufacturerId);
}
