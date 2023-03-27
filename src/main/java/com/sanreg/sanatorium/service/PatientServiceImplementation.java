package com.sanreg.sanatorium.service;

import com.sanreg.sanatorium.entity.Patient;
import com.sanreg.sanatorium.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImplementation implements PatientService {
    PatientRepository patientRepository;
    @Autowired
    public PatientServiceImplementation(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public List<Patient> findAllPatients(){
        List<Patient> patientList = patientRepository.findAll();
        return  patientList;
    }

    @Override
    public Patient findPatientById(int id) {
        Patient patient = null;
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if(optionalPatient.isPresent()){
            patient = optionalPatient.get();
        }
        return patient;
    }

    @Override
    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }
}
