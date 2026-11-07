package com.tecsup.petclinic.services;
import com.tecsup.petclinic.entities.Visit;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest @Transactional
public class VisitServiceTest {
 @Autowired VisitService service;
 @Autowired EntityManager em;
 private Visit nuevo() { Visit e=new Visit(); e.setPetId(1); e.setVetId(1); e.setVisitDate(java.time.LocalDate.of(2026,11,6)); e.setDescription("Control general"); e.setCost(new java.math.BigDecimal("50.00")); return e; }
 private void recargar() { em.flush(); em.clear(); }
 @Test void testCrear() { var e=service.create(nuevo()); assertNotNull(e.getId()); recargar(); assertEquals("Control general",service.findById(e.getId()).getDescription()); }
 @Test void testBuscar() { var e=service.create(nuevo()); var id=e.getId(); recargar(); var found=service.findById(id); assertEquals(id,found.getId()); assertEquals("Control general",found.getDescription()); }
 @Test void testActualizar() { var e=service.create(nuevo()); var id=e.getId(); e.setDescription("Control actualizado"); service.update(e); recargar(); var updated=service.findById(id); assertEquals(id,updated.getId()); assertEquals("Control actualizado",updated.getDescription()); }
 @Test void testEliminar() { var e=service.create(nuevo()); var id=e.getId(); service.delete(id); recargar(); assertThrows(java.util.NoSuchElementException.class,()->service.findById(id)); }
}
