package com.tecsup.petclinic.services;
import com.tecsup.petclinic.entities.Vet;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest @Transactional
public class VetServiceTest {
 @Autowired VetService service;
 @Autowired EntityManager em;
 private Vet nuevo() { Vet e=new Vet(); e.setFirstName("Joe"); e.setLastName("Valdeiglesias"); e.setEmail("joe@example.com"); e.setActive(true); return e; }
 private void recargar() { em.flush(); em.clear(); }
 @Test void testCrear() { var e=service.create(nuevo()); assertNotNull(e.getId()); recargar(); assertEquals("Joe",service.findById(e.getId()).getFirstName()); }
 @Test void testBuscar() { var e=service.create(nuevo()); var id=e.getId(); recargar(); var found=service.findById(id); assertEquals(id,found.getId()); assertEquals("Joe",found.getFirstName()); }
 @Test void testActualizar() { var e=service.create(nuevo()); var id=e.getId(); e.setFirstName("Anderson"); service.update(e); recargar(); var updated=service.findById(id); assertEquals(id,updated.getId()); assertEquals("Anderson",updated.getFirstName()); }
 @Test void testEliminar() { var e=service.create(nuevo()); var id=e.getId(); service.delete(id); recargar(); assertThrows(java.util.NoSuchElementException.class,()->service.findById(id)); }
}
