package com.example.wifidemo.repository;

import com.example.wifidemo.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {

//    TODO: kiegesziteni

    @Query(value="select * from service where city_enum = :city and cat_enum = :category" ,nativeQuery=true)
    List<Service> findAllByCityAndCategory(@Param("city") String city, @Param("category") String category);

    List<Service> findByCityIdAndCategoryId(int cityId, int catId);
}
