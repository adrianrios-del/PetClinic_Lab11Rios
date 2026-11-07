package com.tecsup.petclinic.services;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.repositories.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service @Transactional
public class VisitService {
 private final VisitRepository repository;
 public VisitService(VisitRepository repository) { this.repository=repository; }
 public Visit create(Visit entity) { entity.setId(null); return repository.saveAndFlush(entity); }
 public Visit findById(Integer id) { return repository.findById(id).orElseThrow(() -> new java.util.NoSuchElementException("Visit no encontrado: " + id)); }
 public Visit update(Visit entity) { findById(entity.getId()); return repository.saveAndFlush(entity); }
 public void delete(Integer id) { repository.delete(findById(id)); repository.flush(); }
}
