package com.tecsup.petclinic.services;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service @Transactional
public class OwnerService {
 private final OwnerRepository repository;
 public OwnerService(OwnerRepository repository) { this.repository=repository; }
 public Owner create(Owner entity) { entity.setId(null); return repository.saveAndFlush(entity); }
 public Owner findById(Integer id) { return repository.findById(id).orElseThrow(() -> new java.util.NoSuchElementException("Owner no encontrado: " + id)); }
 public Owner update(Owner entity) { findById(entity.getId()); return repository.saveAndFlush(entity); }
 public void delete(Integer id) { repository.delete(findById(id)); repository.flush(); }
}
