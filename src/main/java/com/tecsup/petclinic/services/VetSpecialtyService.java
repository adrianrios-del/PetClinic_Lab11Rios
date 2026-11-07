package com.tecsup.petclinic.services;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service @Transactional
public class VetSpecialtyService {
 private final JdbcTemplate jdbc;
 public VetSpecialtyService(JdbcTemplate jdbc) { this.jdbc=jdbc; }
 public void asignar(int vetId,int specialtyId) {
  jdbc.update("INSERT INTO vet_specialties(vet_id,specialty_id,years_experience,is_primary) VALUES (?,?,?,?)",vetId,specialtyId,1,false);
 }
 public List<Integer> especialidadesPorVeterinario(int vetId) {
  return jdbc.queryForList("SELECT specialty_id FROM vet_specialties WHERE vet_id=? ORDER BY specialty_id",Integer.class,vetId);
 }
 public List<Integer> veterinariosPorEspecialidad(int specialtyId) {
  return jdbc.queryForList("SELECT vet_id FROM vet_specialties WHERE specialty_id=? ORDER BY vet_id",Integer.class,specialtyId);
 }
 public void eliminar(int vetId,int specialtyId) {
  if(jdbc.update("DELETE FROM vet_specialties WHERE vet_id=? AND specialty_id=?",vetId,specialtyId)!=1)
   throw new java.util.NoSuchElementException("Relacion no encontrada");
 }
}
