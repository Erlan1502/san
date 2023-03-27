package com.sanreg.sanatorium.repository;

import com.sanreg.sanatorium.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PatientRepository extends JpaRepository<Patient,Integer> {
}
