package es.unican.is2.tiendaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.tienda.Vendedor;
import es.unican.is2.tienda.VendedorPracticas;


public class VendedorPracticasTest {
	
	private static Vendedor sut;

	@BeforeEach
	public void setUp(){
		sut = new VendedorPracticas("Ana", "1", "11111111A");
	}
	
	@Test
	public void testConstructor() {
		assertEquals(sut.getId(), "1");
		assertEquals(sut.getNombre(), "Ana");
		assertEquals(sut.getDni(), "11111111A");
		assertEquals(0.0, sut.getTotalVentas());
		assertEquals(0.0, sut.getTotalComision());
	}
	
	@Test
	public void testSetT() {
		sut.setTotalVentas(100);
		assertTrue(sut.getTotalVentas()==100.0);
		
		sut.setTotalVentas(230);
		assertTrue(sut.getTotalVentas()==230.0);
		
		sut.setTotalVentas(0);
		assertTrue(sut.getTotalVentas()==0.0);
	}
	
	@Test
	public void testSetC() {
		sut.setTotalComision(100);
		assertTrue(sut.getTotalComision()==100.0);
		
		sut.setTotalComision(230);
		assertTrue(sut.getTotalComision()==230.0);
		
		sut.setTotalComision(0);
		assertTrue(sut.getTotalComision()==0.0);
	}

	@Test
	public void testAnhadeVenta() {
		sut.anhadeVenta(200);
		assertTrue(sut.getTotalVentas() == 200.0);
		
		sut.anhadeVenta(300);
		assertTrue(sut.getTotalVentas() == 500.0);	
		
		sut.anhadeVenta(0);
		assertTrue(sut.getTotalVentas() == 500.0);
		
	}
	
	@Test
	public void testEquals() {
		Vendedor igual = new VendedorPracticas("Ana", "1", "11111111A");
		Vendedor distintoId = new VendedorPracticas("Ana", "2", "11111111A");
		Vendedor distintoNombre = new VendedorPracticas("Pepe", "1", "222222222A");
		
		assertTrue(sut.equals(igual));
		assertFalse(sut.equals(distintoId));
		assertFalse(sut.equals(distintoNombre));
		
		assertFalse(sut.equals(new Object()));
	}
	
	
	
}
