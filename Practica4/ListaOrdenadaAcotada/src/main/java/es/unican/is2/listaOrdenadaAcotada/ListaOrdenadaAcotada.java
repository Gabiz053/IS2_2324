package es.unican.is2.listaOrdenadaAcotada;

/**
 * Clase que implementa el DTO IListaOrdenadaAcotada Su capacida por defecto es
 * 10, aunque proporciona un constructor para crear la lista con otra capacidad
 */
public class ListaOrdenadaAcotada<E extends Comparable<E>> implements IListaOrdenadaAcotada<E> {

	// Implementacion basada en array
	private E[] lista;
	private int ultimaPosicion;

	/**
	 * Constructor al que se le pasa la capacidad maxima de la lista
	 * 
	 * @throws NegativeArraySizeException si max es negativo
	 */
	@SuppressWarnings("unchecked")
	public ListaOrdenadaAcotada(int max) {
		lista = (E[]) new Comparable[max];
		ultimaPosicion = -1;
	}

	public E get(int indice) {
		// comprueba errores
		if (indice > ultimaPosicion) {
			throw new IndexOutOfBoundsException();
		}
		// retorna el elemento
		return lista[indice];
	}

	private void add(int indice, E elemento) {
		// desplaza elementos hacia adelante
		for (int i = ultimaPosicion; i >= indice; i--) {
			lista[i + 1] = lista[i];
		}
		// anhade el elemento
		lista[indice] = elemento;
		ultimaPosicion++;
	}

	public void add(E elemento) {
		if (elemento == null)
			throw new NullPointerException();
		// compruebo si cabe
		if (ultimaPosicion == lista.length - 1) {
			throw new IllegalStateException();
		}
		// busca el lugar donde debe insertarse
		int indice = 0;
		if (ultimaPosicion != -1) {

			while (indice <= ultimaPosicion && elemento.compareTo(lista[indice]) > 0) {
				indice++;
			}
		}
		add(indice, elemento);
	}

	public E remove(int indice) {
		if (indice > ultimaPosicion) {
			throw new IndexOutOfBoundsException();
		}
		E borrado = lista[indice];
		// desplaza elementos hacia atras
		for (int i = indice + 1; i <= ultimaPosicion; i++) {
			lista[i - 1] = lista[i];
		}
		// actualiza ultimo y retorna el elemento borrado
		ultimaPosicion--;
		return borrado;
	}

	/**
	 * Retorna el tamanho actual de la lista
	 */
	public int size() {
		return ultimaPosicion + 1;
	}

	/**
	 * Vacia la lista
	 */
	public void clear() {
		ultimaPosicion = -1;
	}
}