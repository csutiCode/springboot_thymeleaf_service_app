package com.example.wifidemo.controller;

import com.example.wifidemo.dto.QualificationDto;
import com.example.wifidemo.model.Qualification;
import com.example.wifidemo.model.Service;
import com.example.wifidemo.service.QualificationService;
import com.example.wifidemo.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class QualificationController {

    @Autowired
    private QualificationService qualificationService;

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/showNewQualificationForm/{id}")
    public String showNewQualificationForm(@PathVariable (value = "id") int id, Model qModel, Model sModel) {
        //create model attribute to bind form data
        QualificationDto qualificationDto = new QualificationDto();
        Service service = serviceService.getServiceById(id);
        qModel.addAttribute("qualificationDto", qualificationDto);
        sModel.addAttribute("service", service);
        return "/showNewQualificationForm";
    }

    @PostMapping("/saveQualification/{id}")
    public String saveQualification
            (@ModelAttribute("qualificationDto") QualificationDto dto, @PathVariable (value = "id") int id) {
        qualificationService.saveQualification(dtoToQualification(dto, id));
        return "redirect:/";
    }



    public Qualification dtoToQualification(QualificationDto dto, int serviceId) {
        Qualification qualification = new Qualification();
        qualification.setQualifier(dto.getQualifier());
        Service service = serviceService.getServiceById(serviceId);
        qualification.setText(dto.getText());
        service.getQualifications().add(qualification);

        qualification.setService(service);

        return qualification;

    }



}
