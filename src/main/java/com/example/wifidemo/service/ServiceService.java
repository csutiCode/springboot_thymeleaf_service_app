package com.example.wifidemo.service;

import com.example.wifidemo.model.Service;
import com.example.wifidemo.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    ServiceRepository serviceRepository;


    public List<Service> findAll() {
        return serviceRepository.findAll();
    }

    public void saveService(Service service) {
        serviceRepository.save(service);
    }

    public Service getServiceById(int id) {
        Optional<Service> optional = serviceRepository.findById(id);
        Service service = null;
        if (optional.isPresent()) {
            service = optional.get();
        } else {
            throw new RuntimeException("Service not found for id " + id);
        }
        return service;
    }

    public void deleteServiceById(int id) {
        serviceRepository.deleteById(id);
    };

    public List<Service> findAllByCityAndCategory(String city, String category) {

        return serviceRepository.findAllByCityAndCategory(city, category);
    }

    //returns paginated some values
    public Page<Service> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return serviceRepository.findAll(pageable);
    }

    public List<Service> findByCityIdAndCategoryId(int cityId, int catId) {

        return serviceRepository.findByCityIdAndCategoryId(cityId, catId);
    }






}
