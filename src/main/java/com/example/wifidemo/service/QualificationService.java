package com.example.wifidemo.service;


import com.example.wifidemo.model.Qualification;
import com.example.wifidemo.repository.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QualificationService {


    @Autowired
    private QualificationRepository qualificationRepository;

    public void saveQualification(Qualification qualification) {
        qualificationRepository.save(qualification);
    }


}
