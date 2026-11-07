package com.tecsup.petclinic.services;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.repositories.VetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service @Transactional
public class VetService {
 private final VetRepository repository;
 public VetService(VetRepository repository) { this.repository=repository; }
 public Vet create(Vet entity) { entity.setId(null); return repository.saveAndFlush(entity); }
 public Vet findById(Integer id) { return repository.findById(id).orElseThrow(() -> new java.util.NoSuchElementException("Vet no encontrado: " + id)); }
 public Vet update(Vet entity) { findById(entity.getId()); return repository.saveAndFlush(entity); }
 public void delete(Integer id) { repository.delete(findById(id)); repository.flush(); }
}
