package es.unican.is2.FranquiciasUCCommon;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class EmpleadoTest {

	/* atributo para no tener que estar creando uno todo el tiempo */
	private Empleado empleado;
	private DatoIncorrectoException thrown;

	@Test
	void testEmpleado() {

		/* casos de prueba validos, tenemos 3 */
		/* usamos el does not throw por si hay una excepcion inesperada que falle */
		assertDoesNotThrow(() -> {

			empleado = new Empleado("12345678A", "Gabriel", Categoria.ENCARGADO, LocalDate.now());
			assertEquals("12345678A", empleado.getDNI());
			assertEquals("Gabriel", empleado.getNombre());
			assertEquals(Categoria.ENCARGADO, empleado.getCategoria());
			assertEquals(LocalDate.now(), empleado.getFechaContratacion());
			assertEquals(false, empleado.getBaja());

			empleado = new Empleado("87654321A", "Paco", Categoria.VENDEDOR, LocalDate.now().minusDays(1));
			assertEquals("87654321A", empleado.getDNI());
			assertEquals("Paco", empleado.getNombre());
			assertEquals(Categoria.VENDEDOR, empleado.getCategoria());
			assertEquals(LocalDate.now().minusDays(1), empleado.getFechaContratacion());
			assertEquals(false, empleado.getBaja());

			empleado = new Empleado("99999999Z", "Carmela", Categoria.AUXILIAR, LocalDate.now().minusDays(30));
			assertEquals("99999999Z", empleado.getDNI());
			assertEquals("Carmela", empleado.getNombre());
			assertEquals(Categoria.AUXILIAR, empleado.getCategoria());
			assertEquals(LocalDate.now().minusDays(30), empleado.getFechaContratacion());
			assertEquals(false, empleado.getBaja());

		});

		/* casos de prueba no validos, tenemos 9 */
		thrown = assertThrows(DatoIncorrectoException.class,
				() -> new Empleado(null, "Gabriel", Categoria.ENCARGADO, LocalDate.now()));
		assertEquals("Introduce un DNI valido", thrown.getMessage());
		
		thrown = assertThrows(DatoIncorrectoException.class,
				() -> new Empleado("", "Gabriel", Categoria.ENCARGADO, LocalDate.now()));
		assertEquals("Introduce un DNI valido", thrown.getMessage());
		
		thrown = assertThrows(DatoIncorrectoException.class,
				() -> new Empleado("12345678A", null, Categoria.ENCARGADO, LocalDate.now()));
		assertEquals("Introduce un nombre valido", thrown.getMessage());
		
		thrown = assertThrows(DatoIncorrectoException.class,
				() -> new Empleado("12345678A", "", Categoria.ENCARGADO, LocalDate.now()));
		assertEquals("Introduce un nombre valido", thrown.getMessage());
		
		thrown = assertThrows(DatoIncorrectoException.class, 
				() -> new Empleado("12345678A", "Gabriel", null, LocalDate.now()));
		assertEquals("Introduce una categoria valida", thrown.getMessage());
		
		thrown = assertThrows(DatoIncorrectoException.class,
				() -> new Empleado("12345678A", "Gabriel", Categoria.ENCARGADO, LocalDate.now().plusDays(1)));
		assertEquals("Introduce una fechaContratacion valida", thrown.getMessage());
		
		thrown = assertThrows(DatoIncorrectoException.class,
				() -> new Empleado("12345678A", "Gabriel", Categoria.ENCARGADO, LocalDate.now().plusDays(30)));
		assertEquals("Introduce una fechaContratacion valida", thrown.getMessage());
		
		thrown = assertThrows(DatoIncorrectoException.class,
				() -> new Empleado("12345678A", "Gabriel", Categoria.ENCARGADO, null));
		assertEquals("Introduce una fechaContratacion valida", thrown.getMessage());
		
		/*
		 * Esta no se puede porque en java son enums y si no pertenece pues no existe
		 * 
		 * assertThrows(DatoIncorrectoException.class, () -> new
		 * Empleado("12345678A","Gabriel",Categoria.PATATA, LocalDate.now()));
		 */
	}

	@Test
	void testSueldoBruto() {
		/* casos de prueba validos, tenemos 11 */
		/* usamos el does not throw por si hay una excepcion inesperada que falle */
		assertDoesNotThrow(() -> {
			empleado = new Empleado("12345678A", "Gabriel", Categoria.ENCARGADO, LocalDate.now());
			
			empleado.setCategoria(Categoria.ENCARGADO);
			empleado.setBaja(true);
			empleado.setFechaContratacion(LocalDate.now());
			assertEquals(1500, empleado.sueldoBruto());
			
			empleado.setCategoria(Categoria.VENDEDOR);
			empleado.setBaja(false);
			empleado.setFechaContratacion(LocalDate.now().minusYears(1));
			assertEquals(1500, empleado.sueldoBruto());
			
			empleado.setCategoria(Categoria.AUXILIAR);
			empleado.setBaja(true);
			empleado.setFechaContratacion(LocalDate.now().minusYears(5));
			assertEquals(750, empleado.sueldoBruto());
			
			empleado.setCategoria(Categoria.ENCARGADO);
			empleado.setBaja(false);
			empleado.setFechaContratacion(LocalDate.now().minusYears(6));
			assertEquals(2050, empleado.sueldoBruto());
			
			empleado.setCategoria(Categoria.VENDEDOR);
			empleado.setBaja(true);
			empleado.setFechaContratacion(LocalDate.now().minusYears(7));
			assertEquals(1162.5, empleado.sueldoBruto());
			
			empleado.setCategoria(Categoria.AUXILIAR);
			empleado.setBaja(false);
			empleado.setFechaContratacion(LocalDate.now().minusYears(10));
			assertEquals(1050, empleado.sueldoBruto());
			
			empleado.setCategoria(Categoria.ENCARGADO);
			empleado.setBaja(true);
			empleado.setFechaContratacion(LocalDate.now().minusYears(11));
			assertEquals(1575, empleado.sueldoBruto());
			
			empleado.setCategoria(Categoria.VENDEDOR);
			empleado.setBaja(false);
			empleado.setFechaContratacion(LocalDate.now().minusYears(15));
			assertEquals(1600, empleado.sueldoBruto());
			
			empleado.setCategoria(Categoria.AUXILIAR);
			empleado.setBaja(true);
			empleado.setFechaContratacion(LocalDate.now().minusYears(20));
			assertEquals(825, empleado.sueldoBruto());
			
			empleado.setCategoria(Categoria.ENCARGADO);
			empleado.darDeAlta();
			empleado.setFechaContratacion(LocalDate.now().minusYears(21));
			assertEquals(2200, empleado.sueldoBruto());
			
			empleado.setCategoria(Categoria.VENDEDOR);
			empleado.darDeBaja();
			empleado.setFechaContratacion(LocalDate.now().minusYears(30));
			assertEquals(1275, empleado.sueldoBruto());
		});
		/* casos de prueba no validos, tenemos 4 */
		
		/*
		 * No hace falta implementar estos, ya que al intentar crear un empleado con los
		 * valores no validos, va a saltar una excepcion y no se va a crear ni nos va a
		 * dejar llegar hasta aqui.
		 */		
	}
}
