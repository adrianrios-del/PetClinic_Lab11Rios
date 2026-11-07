package com.tecsup.petclinic.services;
import com.tecsup.petclinic.entities.Type;
import com.tecsup.petclinic.repositories.TypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service @Transactional
public class TypeService {
 private final TypeRepository repository;
 public TypeService(TypeRepository repository) { this.repository=repository; }
 public Type create(Type entity) { entity.setId(null); return repository.saveAndFlush(entity); }
 public Type findById(Integer id) { return repository.findById(id).orElseThrow(() -> new java.util.NoSuchElementException("Type no encontrado: " + id)); }
 public Type update(Type entity) { findById(entity.getId()); return repository.saveAndFlush(entity); }
 public void delete(Integer id) { repository.delete(findById(id)); repository.flush(); }
}
