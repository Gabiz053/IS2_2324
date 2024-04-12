package es.unican.is2.ListaOrdenadaAcotada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import es.unican.is2.listaOrdenadaAcotada.ListaOrdenadaAcotada;

class ListaOrdenadaAcotadaTest {

	ListaOrdenadaAcotada<Integer> lista;

	@Test
	void testGet() {
		/* casos de prueba validos, tenemos 3 */
		/* usamos el does not throw por si hay una excepcion inesperada que falle */
		assertDoesNotThrow(() -> {
			/* lista llena */
			lista = new ListaOrdenadaAcotada<Integer>(5);
			lista.add(1);
			lista.add(2);
			lista.add(3);
			lista.add(4);
			lista.add(5);

			/* por el principio y final */
			assertEquals(5, lista.size());
			assertEquals(1, lista.get(0));
			assertEquals(5, lista.get(4));
			
			/* lista con elementos */
			lista = new ListaOrdenadaAcotada<Integer>(10);
			lista.add(1);
			lista.add(2);
			lista.add(3);
			lista.add(4);
			lista.add(5);

			/* por el medio*/
			assertEquals(5, lista.size());
			assertEquals(2, lista.get(1));
		});

		/* casos de prueba no validos, tenemos 5 */
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-10));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(10));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(5));

		/* lista vacia */
		lista = new ListaOrdenadaAcotada<Integer>(5);
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(0));
	}

	@Test
	void testAdd() {
		/* casos de prueba validos, tenemos 4 */
		/* usamos el does not throw por si hay una excepcion inesperada que falle */
		assertDoesNotThrow(() -> {
			lista = new ListaOrdenadaAcotada<Integer>(5);

			/* añadir en vacia, delante y detras */
			lista.add(2);
			assertEquals(2, lista.get(0));
			assertEquals(1, lista.size());
			
			lista.add(1);
			assertEquals(1, lista.get(0));
			assertEquals(2, lista.size());
			
			lista.add(5);
			assertEquals(5, lista.get(2));
			assertEquals(3, lista.size());

			/* añadir en medio */
			lista.add(4);
			lista.add(3);
			assertEquals(3, lista.get(2));
			assertEquals(5, lista.size());

		});
		/* casos de prueba no validos, tenemos 2 */
		assertThrows(NullPointerException.class, () -> lista.add(null));
		assertThrows(IllegalStateException.class, () -> lista.add(0));
	}

	@Test
	void testRemove() {
		/* casos de prueba validos, tenemos 3 */
		/* usamos el does not throw por si hay una excepcion inesperada que falle */
		assertDoesNotThrow(() -> {
			/* lista con elementos */
			lista = new ListaOrdenadaAcotada<Integer>(10);
			lista.add(1);
			lista.add(2);
			lista.add(3);
			lista.add(4);
			lista.add(5);

			/* por el principio */
			assertEquals(5, lista.size());
			assertEquals(1, lista.remove(0));
			assertEquals(4, lista.size());

			/* lista llena */
			lista = new ListaOrdenadaAcotada<Integer>(5);
			lista.add(1);
			lista.add(2);
			lista.add(3);
			lista.add(4);
			lista.add(5);

			/* por el medio y por el final */
			assertEquals(2, lista.remove(1));
			assertEquals(4, lista.size());
			/* [1,3,4,5] */
			
			assertEquals(5, lista.remove(3));
			assertEquals(3, lista.size());
			/* [1,3,4] */
		});

		/* casos de prueba no validos, tenemos 5 */
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-10));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(10));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(5));

		/* lista vacia */
		lista = new ListaOrdenadaAcotada<Integer>(5);
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(0));
	}

	@Test
	void testSize() {
		/* casos de prueba validos, tenemos 3 */
		/* usamos el does not throw por si hay una excepcion inesperada que falle */
		assertDoesNotThrow(() -> {
			/* lista vacia */
			lista = new ListaOrdenadaAcotada<Integer>(5);
			assertEquals(0, lista.size());

			/* con elementos */
			lista.add(1);
			lista.add(2);
			lista.add(3);
			assertEquals(3, lista.size());

			/* lista llena */
			lista.add(4);
			lista.add(5);
			assertEquals(5, lista.size());
		});
	}

	@Test
	void testClear() {
		/* casos de prueba validos, tenemos 3 */
		/* usamos el does not throw por si hay una excepcion inesperada que falle */
		assertDoesNotThrow(() -> {
			/* lista vacia */
			lista = new ListaOrdenadaAcotada<Integer>(5);
			lista.clear();
			assertEquals(0, lista.size());

			/* con elementos */
			lista.add(1);
			lista.add(2);
			lista.add(3);
			lista.clear();
			assertEquals(0, lista.size());

			/* lista llena */
			lista.add(1);
			lista.add(2);
			lista.add(3);
			lista.add(4);
			lista.add(5);
			lista.clear();
			assertEquals(0, lista.size());
			
		});
	}

}
