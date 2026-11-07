package com.tecsup.petclinic.services;
import com.tecsup.petclinic.entities.Type;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest @Transactional
public class TypeServiceTest {
 @Autowired TypeService service;
 @Autowired EntityManager em;
 private Type nuevo() { Type e=new Type(); e.setName("Cobayo"); e.setDescription("Mascota pequena"); e.setActive(true); return e; }
 private void recargar() { em.flush(); em.clear(); }
 @Test void testCrear() { var e=service.create(nuevo()); assertNotNull(e.getId()); recargar(); assertEquals("Cobayo",service.findById(e.getId()).getName()); }
 @Test void testBuscar() { var e=service.create(nuevo()); var id=e.getId(); recargar(); var found=service.findById(id); assertEquals(id,found.getId()); assertEquals("Cobayo",found.getName()); }
 @Test void testActualizar() { var e=service.create(nuevo()); var id=e.getId(); e.setName("Cuy"); service.update(e); recargar(); var updated=service.findById(id); assertEquals(id,updated.getId()); assertEquals("Cuy",updated.getName()); }
 @Test void testEliminar() { var e=service.create(nuevo()); var id=e.getId(); service.delete(id); recargar(); assertThrows(java.util.NoSuchElementException.class,()->service.findById(id)); }
}
