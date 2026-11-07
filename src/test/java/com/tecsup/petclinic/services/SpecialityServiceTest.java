package com.tecsup.petclinic.services;
import com.tecsup.petclinic.entities.Speciality;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest @Transactional
public class SpecialityServiceTest {
 @Autowired SpecialityService service;
 @Autowired EntityManager em;
 private Speciality nuevo() { Speciality e=new Speciality(); e.setName("Cardiologia"); e.setOffice("Consultorio 1"); e.setHOpen(8); e.setHClose(18); return e; }
 private void recargar() { em.flush(); em.clear(); }
 @Test void testCrear() { var e=service.create(nuevo()); assertNotNull(e.getId()); recargar(); assertEquals("Cardiologia",service.findById(e.getId()).getName()); }
 @Test void testBuscar() { var e=service.create(nuevo()); var id=e.getId(); recargar(); var found=service.findById(id); assertEquals(id,found.getId()); assertEquals("Cardiologia",found.getName()); }
 @Test void testActualizar() { var e=service.create(nuevo()); var id=e.getId(); e.setName("Dermatologia"); service.update(e); recargar(); var updated=service.findById(id); assertEquals(id,updated.getId()); assertEquals("Dermatologia",updated.getName()); }
 @Test void testEliminar() { var e=service.create(nuevo()); var id=e.getId(); service.delete(id); recargar(); assertThrows(java.util.NoSuchElementException.class,()->service.findById(id)); }
}
