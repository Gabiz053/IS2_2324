package es.unican.is2.tienda;

import java.util.List;

import fundamentos.Menu;
import fundamentos.Lectura;
import fundamentos.Mensaje;

/**
 * Gestion de las comisiones de vendedores de una tienda
 */
public class GestionComisiones {

	// opciones del menu
	public static final int OPCION_NUEVA_VENTA = 0;
	public static final int OPCION_VENDEDOR_DEL_MES = 1;
	public static final int OPCION_VENDEDORES = 2;

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) {

		// variables auxiliares
		String dni;
		Lectura lect;

		List<Vendedor> vendedores;
		List<Vendedor> resultado;
		String msj;

		// crea la tienda
		Tienda tienda = new Tienda(Textos.DATOS_TIENDA_PATH);

		// crea la ventana de menu
		Menu menu = new Menu(Textos.TIENDA_COMISIONES);
		menu.insertaOpcion(Textos.VENTA_ANHADIR, OPCION_NUEVA_VENTA);
		menu.insertaOpcion(Textos.VENDEDOR_MES, OPCION_VENDEDOR_DEL_MES);
		menu.insertaOpcion(Textos.VENDEDOR_VENTAS, OPCION_VENDEDORES);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			// leemos el fichero para conseguir los vendedores actualizados
			try {
				tienda.vendedores();
			} catch (DataAccessException e) {
				mostrarVentanaMensaje(Textos.MSJ_ERROR, Textos.ERROR_NO_ACCEDER);
			}
			
			opcion = menu.leeOpcion();
			
			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case OPCION_NUEVA_VENTA:
				// creamos ventana y cogemos datos
				lect = new Lectura(Textos.VENTA_DATOS);
				lect.creaEntrada(Textos.VENDEDOR_ID, Textos.MSJ_VACIO);
				lect.creaEntrada(Textos.VENTA_IMPORTE, Textos.MSJ_VACIO);
				lect.esperaYCierra();
				dni = lect.leeString(Textos.VENDEDOR_ID);
				double importe = lect.leeDouble(Textos.VENTA_IMPORTE);

				// una vez tenemos los datos tratamos de añadir nueva venta
				try {
					if (!tienda.anhadeVentaVendedor(dni, importe)) {
						mostrarVentanaMensaje(Textos.MSJ_ERROR, Textos.ERROR_VENDEDOR_NO_EXISTE);
					}
				} catch (DataAccessException e) {
					mostrarVentanaMensaje(Textos.MSJ_ERROR, Textos.ERROR_NO_GUARDAR);
				}
				break;

			case OPCION_VENDEDOR_DEL_MES:
				resultado = tienda.buscaVendedoresMes();

				msj = Textos.MSJ_VACIO;

				// ponemos el nombre de cada vendedor del mes para devolverlo
				for (Vendedor vendedor : resultado) {
					msj += vendedor.getNombre() + "\n";
				}
				mostrarVentanaMensaje(Textos.MSJ_VENDERORES_MES, msj);
				break;

			case OPCION_VENDEDORES:
				vendedores = tienda.ordenarVendedoresVentas();

				msj = Textos.MSJ_VACIO;
				for (Vendedor vendedor : vendedores) {
					msj += vendedor.getNombre() + " (" + vendedor.getId() + ") " + vendedor.getTotalVentas() + "\n";
				}
				mostrarVentanaMensaje(Textos.MSJ_VENDEDORES, msj);

				break;
			}
		}
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * 
	 * @param titulo Titulo de la ventana
	 * @param txt    Texto contenido en la ventana
	 */
	private static void mostrarVentanaMensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}

}
