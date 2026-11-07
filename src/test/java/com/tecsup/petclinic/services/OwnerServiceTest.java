package com.tecsup.petclinic.services;
import com.tecsup.petclinic.entities.Owner;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest @Transactional
public class OwnerServiceTest {
 @Autowired OwnerService service;
 @Autowired EntityManager em;
 private Owner nuevo() { Owner e=new Owner(); e.setFirstName("Joe"); e.setLastName("Valdeiglesias"); e.setCity("Arequipa"); return e; }
 private void recargar() { em.flush(); em.clear(); }
 @Test void testCrear() { var e=service.create(nuevo()); assertNotNull(e.getId()); recargar(); assertEquals("Arequipa",service.findById(e.getId()).getCity()); }
 @Test void testBuscar() { var e=service.create(nuevo()); var id=e.getId(); recargar(); var found=service.findById(id); assertEquals(id,found.getId()); assertEquals("Arequipa",found.getCity()); }
 @Test void testActualizar() { var e=service.create(nuevo()); var id=e.getId(); e.setCity("Lima"); service.update(e); recargar(); var updated=service.findById(id); assertEquals(id,updated.getId()); assertEquals("Lima",updated.getCity()); }
 @Test void testEliminar() { var e=service.create(nuevo()); var id=e.getId(); service.delete(id); recargar(); assertThrows(java.util.NoSuchElementException.class,()->service.findById(id)); }
}
