package es.unican.is2.tiendaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.tienda.Vendedor;
import es.unican.is2.tienda.VendedorPracticas;

class VendedorPracticasTest {

	static Vendedor sut;

	@BeforeEach
	void setUp() {
		sut = new VendedorPracticas("Ana", "1", "11111111A");
	}

	@Test
	void testConstructor() {
		assertEquals("1", sut.getId());
		assertEquals("Ana", sut.getNombre());
		assertEquals("11111111A", sut.getDni());
		assertEquals(0.0, sut.getTotalVentas());
		assertEquals(0.0, sut.getTotalComision());
	}

	@Test
	void testSetT() {
		sut.setTotalVentas(100);
		assertEquals(100.0, sut.getTotalVentas());

		sut.setTotalVentas(230);
		assertEquals(230.0, sut.getTotalVentas());

		sut.setTotalVentas(0);
		assertEquals(0.0, sut.getTotalVentas());
	}

	@Test
	void testSetC() {
		sut.setTotalComision(100);
		assertEquals(100.0, sut.getTotalComision());

		sut.setTotalComision(230);
		assertEquals(230.0, sut.getTotalComision());

		sut.setTotalComision(0);
		assertEquals(0.0, sut.getTotalComision());
	}

	@Test
	void testAnhadeVenta() {
		sut.anhadeVenta(200);
		assertEquals(200.0, sut.getTotalVentas());

		sut.anhadeVenta(300);
		assertEquals(500.0, sut.getTotalVentas());

		sut.anhadeVenta(0);
		assertEquals(500.0, sut.getTotalVentas());

	}

	@Test
	void testEquals() {
		Vendedor igual = new VendedorPracticas("Ana", "1", "11111111A");
		Vendedor distintoId = new VendedorPracticas("Ana", "2", "11111111A");
		Vendedor distintoNombre = new VendedorPracticas("Pepe", "1", "222222222A");

		assertEquals(sut, igual);
		assertNotEquals(sut, distintoId);
		assertNotEquals(sut, distintoNombre);
		assertNotEquals(sut, new Object());
	}

}
