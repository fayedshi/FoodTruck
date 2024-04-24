package com.ass.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ass.spring.model.FoodTruck;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodTruckRepo extends JpaRepository<FoodTruck, Integer> {
    @Query(value = "from FoodTruck where POWER(:x - x, 2) + POWER(:y - y, 2) <= POWER(:distance,2)")
    List<FoodTruck> getNearByFoodTrucks(@Param("x") double x,@Param("y") double y, @Param("distance") int distance);

    // locate source address with exact match, may return multiple rows
    List<FoodTruck> getFoodTrucksByAddress(String address);

}
