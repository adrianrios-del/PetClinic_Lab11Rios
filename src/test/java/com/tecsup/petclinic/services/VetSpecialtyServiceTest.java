package com.tecsup.petclinic.services;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
@SpringBootTest @Transactional
public class VetSpecialtyServiceTest {
 @Autowired VetSpecialtyService service;
 @Autowired JdbcTemplate jdbc;
 @Test void testAsignarEspecialidad() {
  service.asignar(1,3);
  assertEquals(1,jdbc.queryForObject("SELECT COUNT(*) FROM vet_specialties WHERE vet_id=1 AND specialty_id=3",Integer.class));
 }
 @Test void testBuscarEspecialidadesPorVeterinario() {
  service.asignar(1,1); service.asignar(1,3);
  assertEquals(List.of(1,3),service.especialidadesPorVeterinario(1));
 }
 @Test void testBuscarVeterinariosPorEspecialidad() {
  service.asignar(1,3);
  assertEquals(List.of(1,3),service.veterinariosPorEspecialidad(3));
 }
 @Test void testEliminarRelacion() {
  service.asignar(1,3); service.eliminar(1,3);
  assertFalse(service.especialidadesPorVeterinario(1).contains(3));
  assertEquals(1,jdbc.queryForObject("SELECT COUNT(*) FROM vets WHERE id=1",Integer.class));
  assertEquals(1,jdbc.queryForObject("SELECT COUNT(*) FROM specialties WHERE id=3",Integer.class));
 }
}
