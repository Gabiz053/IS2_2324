package es.unican.is2.FranquiciasUCCommon;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

class TiendaTest {

	private Tienda tienda;
	private DatoIncorrectoException thrown;

	@Test
	void testTiendaStringString() {
		/* casos de prueba validos, tenemos 1 */
		/* usamos el does not throw por si hay una excepcion inesperada que falle */
		assertDoesNotThrow(() -> {
			tienda = new Tienda("tienda1", "calle1");
			assertEquals("tienda1", tienda.getNombre());
			assertEquals("calle1", tienda.getDireccion());
		});

		/* casos de prueba no validos, tenemos 4 */
		thrown = assertThrows(DatoIncorrectoException.class, () -> new Tienda(null, "calle1"));
		assertEquals("Introduce un nombre valido", thrown.getMessage());

		thrown = assertThrows(DatoIncorrectoException.class, () -> new Tienda("", "calle1"));
		assertEquals("Introduce un nombre valido", thrown.getMessage());

		thrown = assertThrows(DatoIncorrectoException.class, () -> new Tienda("tienda1", null));
		assertEquals("Introduce una direccion valida", thrown.getMessage());

		thrown = assertThrows(DatoIncorrectoException.class, () -> new Tienda("tienda1", ""));
		assertEquals("Introduce una direccion valida", thrown.getMessage());
	}

	@Test
	void testGastoMensualSueldos() {
		/* antes de todo necesitamos crear los empleados y ponerlos en la tienda */
		Empleado empleado1 = new Empleado("12345678A", "Gabriel", Categoria.ENCARGADO, LocalDate.now());
		;
		Empleado empleado2 = new Empleado("12345678A", "Gabriel", Categoria.VENDEDOR, LocalDate.now().minusYears(1));
		;
		Empleado empleado3 = new Empleado("12345678A", "Gabriel", Categoria.AUXILIAR, LocalDate.now().minusYears(5));
		;
		empleado1.darDeBaja();
		empleado3.darDeBaja();

		tienda = new Tienda("tienda1", "calle1");
		List<Empleado> lista = tienda.getEmpleados();

		/*
		 * Esta vez creo los casos al reves, primero el no valido para solo tener que ir
		 * añadiendo empleados a la tienda
		 */

		/* casos de prueba no validos, tenemos 1 */
		assertDoesNotThrow(() -> {
			assertEquals(0.0, tienda.gastoMensualSueldos());
		});

		/* casos de prueba validos, tenemos 2 */
		/* usamos el does not throw por si hay una excepcion inesperada que falle */
		assertDoesNotThrow(() -> {
			lista.add(empleado1);
			assertEquals(1500, tienda.gastoMensualSueldos());
			
			lista.add(empleado2);
			lista.add(empleado3);
			assertEquals(3750, tienda.gastoMensualSueldos());
		});

	}

}
