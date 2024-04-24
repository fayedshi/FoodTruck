package com.ass.spring;

import com.ass.spring.repository.FoodTruckRepo;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ass.spring.model.FoodTruck;

import java.util.List;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = FoodTruckSpringBootApp.class)
public class FoodTruckRepoTest extends TestCase {

	@Autowired
	FoodTruckRepo repo;

	@Test
	public void testSaveTruck() {
		FoodTruck truck = new FoodTruck(999,"crisp retailer","st louis","00854",12.5,14.8);
		FoodTruck t= repo.save(truck);
		assertEquals(999, t.getLocationId());
	}

	@Test
	public void testFindTruckByAddress() {
		FoodTruck truck = new FoodTruck(998,"crisp retailer","st louis","00854",12.5,14.8);
		List<FoodTruck> trucks= repo.getFoodTrucksByAddress(truck.getAddress());
		assertEquals(998, trucks.get(0).getLocationId());
		trucks= repo.getFoodTrucksByAddress("sss");
		assertEquals(true, trucks.isEmpty());
	}


	@Test
	public void testFindNearByTrucks() {
		// insert source truck
		FoodTruck srcTruck = new FoodTruck(998,"crisp retailer","st louis","00854",12.5,14.8);
		FoodTruck t= repo.save(srcTruck);
		List<FoodTruck> trucks= repo.getFoodTrucksByAddress(srcTruck.getAddress());
		assertEquals(998, trucks.get(0).getLocationId());

		// set up other trucks
		repo.save(new FoodTruck(977,"seafood retailer","st beaver 01","00854",14.5,16.8));
		trucks= repo.getNearByFoodTrucks(srcTruck.getX(),srcTruck.getY(),3);
		// 2 including self
		assertEquals(2, trucks.size());

		// negative test
		trucks= repo.getNearByFoodTrucks(srcTruck.getX(),srcTruck.getY(),1);
		// only self matches
		assertEquals(1, trucks.size());
	}

}
