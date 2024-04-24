package com.ass.spring;

import com.ass.spring.model.FoodTruck;
import com.ass.spring.repository.FoodTruckRepo;
import com.csvreader.CsvReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@SpringBootApplication
public class FoodTruckSpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(FoodTruckSpringBootApp.class, args);
    }

    @Bean
    InitializingBean saveData(FoodTruckRepo repo) {
        return () -> {
            String filePath = "Mobile_Food_Facility_Permit.csv";
            Resource resource = new ClassPathResource(filePath);
            try {
                CsvReader reader = new CsvReader(resource.getFile().getPath());
                String path = resource.getFile().getPath();
                System.out.println(resource.getFile().getPath());
                reader.readHeaders();
                while (reader.readRecord()) {
                    System.out.println(reader.getRawRecord());
                    repo.save(new FoodTruck(Integer.parseInt(reader.get("locationid")),
                            reader.get("Applicant"),
                            reader.get("Address"),
                            reader.get("blockLot"),
                            reader.get("X").isEmpty() ? 0 : Double.parseDouble(reader.get("X")),
                            reader.get("Y").isEmpty() ? 0 : Double.parseDouble(reader.get("Y"))
                                    ));
//                            reader.get("FoodItems").substring(0, reader.get("FoodItems").length()>10?10:reader.get("FoodItems").length()-1)));
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}