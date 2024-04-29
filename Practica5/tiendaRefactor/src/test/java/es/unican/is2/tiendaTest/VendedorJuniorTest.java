package es.unican.is2.tiendaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.tienda.VendedorJunior;

class VendedorJuniorTest {

	static VendedorJunior sutJunior;

	@BeforeEach
	void setUp() {
		sutJunior = new VendedorJunior("Ana", "1", "11111111A");
	}

	@Test
	void testConstructor() {
		assertEquals("1", sutJunior.getId());
		assertEquals("11111111A", sutJunior.getDni());
		assertEquals("Ana", sutJunior.getNombre());
		assertEquals(0.0, sutJunior.getTotalVentas());
		assertEquals(0.0, sutJunior.getTotalComision());
	}

	@Test
	void testAnhadeVenta() {
		sutJunior.anhadeVenta(200);
		assertEquals(200, sutJunior.getTotalVentas());

		sutJunior.anhadeVenta(300);
		assertEquals(500, sutJunior.getTotalVentas());

		sutJunior.anhadeVenta(0);
		assertEquals(500, sutJunior.getTotalVentas());
	}

	@Test
	void testSetTotalVentas() {

		sutJunior.setTotalVentas(2000);
		assertEquals(2000, sutJunior.getTotalVentas());

		sutJunior.setTotalVentas(4000);
		assertEquals(4000, sutJunior.getTotalVentas());

		sutJunior.setTotalVentas(0);
		assertEquals(0, sutJunior.getTotalVentas());
	}

	@Test
	void testSetComision() {

		sutJunior.setTotalComision(2000);
		assertEquals(2000, sutJunior.getTotalComision());

		sutJunior.setTotalComision(4000);
		assertEquals(4000, sutJunior.getTotalComision());

		sutJunior.setTotalComision(0);
		assertEquals(0, sutJunior.getTotalComision());
	}

	@Test
	void testEquals() {
		VendedorJunior igual = new VendedorJunior("Ana", "1", "11111111A");
		VendedorJunior distintoId = new VendedorJunior("Ana", "2", "11111111A");
		VendedorJunior distintoDNI = new VendedorJunior("Ana", "1", "222222222A");

		assertEquals(sutJunior, igual);
		assertNotEquals(sutJunior, distintoId);
		assertNotEquals(sutJunior, distintoDNI);
		assertNotEquals(sutJunior, new Object());
	}

}
