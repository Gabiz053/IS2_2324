package es.unican.is2.tiendaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.tienda.VendedorJunior;



public class VendedorSeniorTest {
	
	private static VendedorJunior sutSenior;

	
	@BeforeEach
	public void setUp(){
		sutSenior = new VendedorJunior("Ana", "1", "11111111A");
	}
	
	@Test
	public void testConstructor() {
		assertEquals(sutSenior.getId(), "1");
		assertEquals(sutSenior.getDni(), "11111111A");
		assertEquals(sutSenior.getNombre(), "Ana");
		assertTrue(sutSenior.getTotalVentas()==0.0);
		assertTrue(sutSenior.getTotalComision()==0.0);
	}

	@Test
	public void testAnhadeVenta() {
	
		sutSenior.anhadeVenta(200);
		assertEquals(sutSenior.getTotalVentas(), 200, 0);
		sutSenior.anhadeVenta(300);
		assertEquals(sutSenior.getTotalVentas(), 500, 0);
		sutSenior.anhadeVenta(0);
		assertEquals(sutSenior.getTotalVentas(), 500, 0);
	}
	
	@Test
	public void testSetTotalVentas() {
		
		sutSenior.setTotalVentas(2000);
		assertEquals(sutSenior.getTotalVentas(), 2000, 0);	
		sutSenior.setTotalVentas(4000);
		assertEquals(sutSenior.getTotalVentas(), 4000, 0);	
		sutSenior.setTotalVentas(0);
		assertEquals(sutSenior.getTotalVentas(), 0, 0);
	}
	
	@Test
	public void testSetComision() {
		
		sutSenior.setTotalComision(2000);
		assertEquals(sutSenior.getTotalComision(), 2000, 0);	
		sutSenior.setTotalComision(4000);
		assertEquals(sutSenior.getTotalComision(), 4000, 0);	
		sutSenior.setTotalComision(0);
		assertEquals(sutSenior.getTotalComision(), 0, 0);
	}

	
	@Test
	public void testEquals() {
		VendedorJunior igualJunior = new VendedorJunior("Ana", "1", "11111111A");
		VendedorJunior distintoIdJunior = new VendedorJunior("Ana", "2", "11111111A");
		VendedorJunior distintoDNIJunior = new VendedorJunior("Ana", "1", "222222222A");
		
		assertTrue(sutSenior.equals(igualJunior));
		assertFalse(sutSenior.equals(distintoIdJunior));
		assertFalse(sutSenior.equals(distintoDNIJunior));
		
		assertFalse(sutSenior.equals(new Object()));
	}
	
	
	
}
