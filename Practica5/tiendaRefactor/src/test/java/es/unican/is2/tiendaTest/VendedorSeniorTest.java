package es.unican.is2.tiendaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.tienda.VendedorSenior;

class VendedorSeniorTest {

	static VendedorSenior sutSenior;

	@BeforeEach
	void setUp() {
		sutSenior = new VendedorSenior("Ana", "1", "11111111A");
	}

	@Test
	void testConstructor() {
		assertEquals("1", sutSenior.getId());
		assertEquals("11111111A", sutSenior.getDni());
		assertEquals("Ana", sutSenior.getNombre());
		assertEquals(0.0, sutSenior.getTotalVentas());
		assertEquals(0.0, sutSenior.getTotalComision());
	}

	@Test
	void testAnhadeVenta() {
		sutSenior.anhadeVenta(200);
		assertEquals(200, sutSenior.getTotalVentas());

		sutSenior.anhadeVenta(300);
		assertEquals(500, sutSenior.getTotalVentas());

		sutSenior.anhadeVenta(0);
		assertEquals(500, sutSenior.getTotalVentas());
	}

	@Test
	void testSetTotalVentas() {

		sutSenior.setTotalVentas(2000);
		assertEquals(2000, sutSenior.getTotalVentas());

		sutSenior.setTotalVentas(4000);
		assertEquals(4000, sutSenior.getTotalVentas());

		sutSenior.setTotalVentas(0);
		assertEquals(0, sutSenior.getTotalVentas());
	}

	@Test
	void testSetComision() {

		sutSenior.setTotalComision(2000);
		assertEquals(2000, sutSenior.getTotalComision());

		sutSenior.setTotalComision(4000);
		assertEquals(4000, sutSenior.getTotalComision());

		sutSenior.setTotalComision(0);
		assertEquals(0, sutSenior.getTotalComision());
	}

	@Test
	void testEquals() {
		VendedorSenior igual = new VendedorSenior("Ana", "1", "11111111A");
		VendedorSenior distintoId = new VendedorSenior("Ana", "2", "11111111A");
		VendedorSenior distintoDNI = new VendedorSenior("Ana", "1", "222222222A");

		assertEquals(sutSenior, igual);
		assertNotEquals(sutSenior, distintoId);
		assertNotEquals(sutSenior, distintoDNI);
		assertNotEquals(sutSenior, new Object());
	}

}
