package com.sanreg.sanatorium.service;

import com.sanreg.sanatorium.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    public void savePatient(Patient patient);
    public List<Patient> findAllPatients();
    public Patient findPatientById(int id);
    public void deletePatient(int id);
}
