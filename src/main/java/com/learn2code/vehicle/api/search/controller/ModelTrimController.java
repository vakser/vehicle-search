package com.learn2code.vehicle.api.search.controller;

import com.learn2code.vehicle.api.search.entity.Model;
import com.learn2code.vehicle.api.search.entity.TrimType;
import com.learn2code.vehicle.api.search.service.ModelTrimService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/model-trim")
public class ModelTrimController {
    private ModelTrimService modelTrimService;

    public ModelTrimController(ModelTrimService modelTrimService) {
        this.modelTrimService = modelTrimService;
    }

    @PostMapping
    public ResponseEntity<Model> createModelTrim(@RequestBody Model model) {
        Model savedRecord = modelTrimService.saveModel(model);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    @PostMapping("/trim-type")
    public ResponseEntity<TrimType> createTrimType(@RequestBody TrimType trimType) {
        TrimType savedTrim = modelTrimService.saveTrimType(trimType);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTrim);
    }
}
