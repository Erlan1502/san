package com.sanreg.sanatorium.controller;

import com.sanreg.sanatorium.entity.Patient;
import com.sanreg.sanatorium.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyController {

    PatientService patientService;
    @Autowired
    public MyController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/")
    public String startingPage(){
        return "index";
    }
    @RequestMapping("/registration")
    public String newPatientRegistration(Model model){
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "registration-san-page";
    }
    @PostMapping("/savePatient")
    public String savePatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registration-san-page";
        }
        patientService.savePatient(patient);
        return "redirect:/";
    }

    @GetMapping("/findAllPatients")
    public String findAllPatients(Model model){
        List<Patient> patientList = patientService.findAllPatients();
        model.addAttribute("patientList",patientList);
        return "all-patients";
    }

    @GetMapping("/updatePatient/{id}")
    public String updatePatient(@PathVariable int id, Model model){
        Patient patient = patientService.findPatientById(id);
        model.addAttribute("patient",patient);
        return "patient-info-changer";
    }

    @DeleteMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable int id){
        patientService.deletePatient(id);
        return "redirect:/findAllPatients";
    }
    @GetMapping("/addByDoctor")
    public String newPatientAddedByDoctor(Model model){
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "add-by-doctor";
    }


}
