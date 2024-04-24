package com.ass.spring.controller;

import com.ass.spring.model.FoodTruck;
import com.ass.spring.repository.FoodTruckRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/foodtrucks/api")
public class FoodTruckController {
    @Autowired
    FoodTruckRepo ftRepo;

    final int distance = 2; //default to 2km

    @GetMapping("/get/{locationId}")
    public FoodTruck getFoodTruck(@PathVariable("locationId") int locationId) {
        return ftRepo.findOne(locationId);
    }

    @GetMapping("/list")
    public List<FoodTruck> getAllFoodTrucks() {
        return ftRepo.findAll();
    }

    @GetMapping("/address/{srcAddress}")
    public List<FoodTruck> getNearByFoodTrucks(@PathVariable("srcAddress") String srcAddress) throws Exception {
        List<FoodTruck> fts = ftRepo.getFoodTrucksByAddress(srcAddress);
        if (fts == null || fts.isEmpty()) {
            throw new Exception("No address found.");
        }
        // only the first truck applies for the source address
        return ftRepo.getNearByFoodTrucks(fts.get(0).getX(), fts.get(0).getY(), distance);
    }
}
