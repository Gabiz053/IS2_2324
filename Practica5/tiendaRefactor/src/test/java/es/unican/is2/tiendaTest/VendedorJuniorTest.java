package es.unican.is2.tiendaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.tienda.VendedorJunior;



public class VendedorJuniorTest {
	
	private static VendedorJunior sutJunior;

	
	@BeforeEach
	public void setUp(){
		sutJunior = new VendedorJunior("Ana", "1", "11111111A");
	}
	
	@Test
	public void testConstructor() {
		assertEquals(sutJunior.getId(), "1");
		assertEquals(sutJunior.getDni(), "11111111A");
		assertEquals(sutJunior.getNombre(), "Ana");
		assertTrue(sutJunior.getTotalVentas()==0.0);
		assertTrue(sutJunior.getTotalComision()==0.0);
	}

	@Test
	public void testAnhadeVenta() {
	
		sutJunior.anhadeVenta(200);
		assertEquals(sutJunior.getTotalVentas(), 200, 0);
		sutJunior.anhadeVenta(300);
		assertEquals(sutJunior.getTotalVentas(), 500, 0);
		sutJunior.anhadeVenta(0);
		assertEquals(sutJunior.getTotalVentas(), 500, 0);
	}
	
	@Test
	public void testSetTotalVentas() {
		
		sutJunior.setTotalVentas(2000);
		assertEquals(sutJunior.getTotalVentas(), 2000, 0);	
		sutJunior.setTotalVentas(4000);
		assertEquals(sutJunior.getTotalVentas(), 4000, 0);	
		sutJunior.setTotalVentas(0);
		assertEquals(sutJunior.getTotalVentas(), 0, 0);
	}
	
	@Test
	public void testSetComision() {
		
		sutJunior.setTotalComision(2000);
		assertEquals(sutJunior.getTotalComision(), 2000, 0);	
		sutJunior.setTotalComision(4000);
		assertEquals(sutJunior.getTotalComision(), 4000, 0);	
		sutJunior.setTotalComision(0);
		assertEquals(sutJunior.getTotalComision(), 0, 0);
	}

	
	@Test
	public void testEquals() {
		VendedorJunior igualJunior = new VendedorJunior("Ana", "1", "11111111A");
		VendedorJunior distintoIdJunior = new VendedorJunior("Ana", "2", "11111111A");
		VendedorJunior distintoDNIJunior = new VendedorJunior("Ana", "1", "222222222A");
		
		assertTrue(sutJunior.equals(igualJunior));
		assertFalse(sutJunior.equals(distintoIdJunior));
		assertFalse(sutJunior.equals(distintoDNIJunior));
		
		assertFalse(sutJunior.equals(new Object()));
	}
	
	
	
}
