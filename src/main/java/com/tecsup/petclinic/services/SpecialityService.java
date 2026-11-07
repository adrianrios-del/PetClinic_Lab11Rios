package com.tecsup.petclinic.services;
import com.tecsup.petclinic.entities.Speciality;
import com.tecsup.petclinic.repositories.SpecialityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service @Transactional
public class SpecialityService {
 private final SpecialityRepository repository;
 public SpecialityService(SpecialityRepository repository) { this.repository=repository; }
 public Speciality create(Speciality entity) { entity.setId(null); return repository.saveAndFlush(entity); }
 public Speciality findById(Integer id) { return repository.findById(id).orElseThrow(() -> new java.util.NoSuchElementException("Speciality no encontrado: " + id)); }
 public Speciality update(Speciality entity) { findById(entity.getId()); return repository.saveAndFlush(entity); }
 public void delete(Integer id) { repository.delete(findById(id)); repository.flush(); }
}
