package com.example.wifidemo.controller;

import com.example.wifidemo.dto.ServiceDto;
import com.example.wifidemo.model.*;
import com.example.wifidemo.repository.UserRepository;
import com.example.wifidemo.service.CityandCategoryService;
import com.example.wifidemo.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityandCategoryService cityandCategoryService;

    @GetMapping("/all")
    public String viewHomePage(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/forLoggedInUser")
    public String findAllForLoggedId(Model model) {
        List<Service> services = serviceService.findAll()
                    .stream()
                    .filter(a -> a.getUser().getId()==getCurrentUser().getId())
                    .collect(Collectors.toList());

        model.addAttribute("listServices", services);
        return "forLoggedInUser";
    }

    @GetMapping("/showNewServiceForm")
    public String showNewEmployeeForm(Model model) {
        //create model attribute to bind form data
        ServiceDto serviceDto = new ServiceDto();
        List<City> cities = cityandCategoryService.findAllCities();
        List<Category> categories = cityandCategoryService.findAllCategories();

        model.addAttribute("serviceDto", serviceDto);
        model.addAttribute("cities", cities);
        model.addAttribute("categories", categories);
        return "showNewServiceForm";
    }

    @RequestMapping("/saveService")
    public String saveService(@ModelAttribute("serviceDto") ServiceDto dto) {
        Service service = dtoToService(dto);
        serviceService.saveService(service);
        return "redirect:/forLoggedInUser";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        //set service as a model attribute to pre-populate the form
        model.addAttribute("service", serviceService.getServiceById(id));
        return "update_service";
    }

    @GetMapping("/deleteServiceById/{id}")
    public String deleteServiceById(@PathVariable(value = "id") int id) {
        serviceService.deleteServiceById(id);
        return "redirect:/forLoggedInUser";
    }

    @GetMapping("/info/{id}")
    public String showInfo(@PathVariable(value = "id") int id, Model model) {
        //set service as a model attribute to pre-populate the form
        Service service = serviceService.getServiceById(id);
        Set<Qualification> qualifications = service.getQualifications();
        model.addAttribute("service", service);
        model.addAttribute("qualifications", qualifications);
        //System.out.println(service.getQualifications());
        return "info";
    }

    //Pagination
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                Model model) {
        int pageSize = 6;

        Page<Service> page = serviceService.findPaginated(pageNo, pageSize);
        List<Service> services = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("services", services);
        //System.out.println(services);
        return "index";
    }

    //copy the dto attributes to the service object
    public Service dtoToService(ServiceDto dto) {
        Service service = new Service();
        service.setName(dto.getName());
        service.setProvidedService(dto.getProvidedService());
        service.setAbout(dto.getAbout());
        service.setCity(cityandCategoryService.findCityByName(dto.getCity()));
        service.setCategory(cityandCategoryService.findCategoryByName(dto.getCategory()));
        service.setEmail(dto.getEmail());
        service.setTelephone(dto.getTelephone());

        service.setUser(getCurrentUser());
        return service;
    }


    public String getCurrentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal!=null) {
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        } else {
            return null;
        }
    }

    public User getCurrentUser() {
        String username = getCurrentUserName();
        List<User> user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("The user " + username + " does not exist");
        }
        return user.get(0);
    }



}