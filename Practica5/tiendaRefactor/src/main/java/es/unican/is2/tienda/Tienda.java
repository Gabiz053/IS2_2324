package es.unican.is2.tienda;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Clase que representa una tienda con un conjunto de vendedores. Gestiona las
 * ventas realizadas y las comisiones asignadas a cada vendedor. Los datos de la
 * tienda se almacenan en un fichero de texto que se pasa como parametro al
 * crear la tienda
 */
public class Tienda {

	private List<Vendedor> lista = new LinkedList<Vendedor>();
	private String direccion;
	private String nombre;

	private String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * 
	 * @param datos Path absoluto del fichero de datos
	 */
	public Tienda(String datos) {
		this.datos = datos;
	}

	/**
	 * Retorna la direccion de la tienda
	 * 
	 * @return Direccion de la tienda
	 */
	public String direccion() {
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * 
	 * @return Nombre de la tienda
	 */
	public String nombre() {
		return nombre;
	}

	/**
	 * Anhade un nuevo vendedor a la tienda
	 * 
	 * @param nuevo El vendedor a anhadir
	 * @return true si el vendedor se ha anhadido false si ya existe el vendedor
	 */
	public boolean anhadeVendedor(Vendedor nuevo) throws DataAccessException {
		// miramos que no exista ya ese vendedor
		Vendedor v = buscaVendedor(nuevo.getId());
		if (v != null) {
			return false;
		}

		// anhadimos a la lista y escribimos en el fichero.
		lista.add(nuevo);
		vuelcaDatos();
		return true;
	}

	/**
	 * Elimina el vendedor cuyo id se pasa como argumento
	 * 
	 * @param id
	 * @return true si se elimina el vendedor false si no existe el vendedor
	 */
	public boolean eliminaVendedor(String id) throws DataAccessException {
		// miramos que exista ya ese vendedor
		Vendedor v = buscaVendedor(id);
		if (v == null) {
			return false;
		}

		// quitamos de la lista y escribimos en el fichero.
		lista.remove(v);
		vuelcaDatos();
		return true;
	}

	/**
	 * Anhade una venta a un vendedor
	 * 
	 * @param id      Id del vendedor
	 * @param importe Importe de la venta
	 * @return true si se anhade la venta false si no se encuentra el vendedor
	 */
	public boolean anhadeVentaVendedor(String id, double importe) throws DataAccessException {
		// miramos si existe el vendedor
		Vendedor v = buscaVendedor(id);
		if (v == null) {
			return false;
		}

		// anhadimos esta venta y lo escribimos
		v.anhadeVenta(importe);
		vuelcaDatos();
		return true;
	}

	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese dni o null si no existe ninguno
	 */
	public Vendedor buscaVendedor(String id) throws DataAccessException {

		lista = vendedores();

		// miramos si existe en la lista
		for (Vendedor v : lista) {
			if (v.getId().equals(id)) {
				return v;
			}
		}
		return null;
	}

	/**
	 * Retorna la lista de vendedores actuales de la tienda
	 * 
	 * @return La lista de vendedores
	 */
	public List<Vendedor> vendedores() throws DataAccessException {
		lista = new LinkedList<Vendedor>();
		Scanner in = null;

		try {
			// abre el fichero
			in = new Scanner(new FileReader(datos));
			// configura el formato de numeros
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();

			// deben estar en este orden porque es el del ficehero
			leerVendedoresSenior(in);
			leerVendedoresJunior(in);
			leerVendedoresPracticas(in);

		} catch (FileNotFoundException e) {
			throw new DataAccessException();

		} finally {
			if (in != null) {
				in.close();
			}
		}
		return lista;
	}

	private void leerVendedoresSenior(Scanner in) {
		Vendedor ven;
		while (in.hasNext() && !in.next().equals(Textos.TIPO_VEN_JUNIOR)) {
			String nombre = in.next();
			in.next();
			String idIn = in.next();
			in.next();
			String dni = in.next();
			in.next();
			double totalVentas = in.nextDouble();
			in.next();
			double totalComision = in.nextDouble();
			ven = new VendedorSenior(nombre, idIn, dni);
			ven.setTotalVentas(totalVentas);
			ven.setTotalComision(totalComision);
			lista.add(ven);
		}
	}

	private void leerVendedoresJunior(Scanner in) {
		Vendedor ven;
		while (in.hasNext() && !in.next().equals(Textos.TIPO_VEN_PRACTICAS)) {
			String nombre = in.next();
			in.next();
			String idIn = in.next();
			in.next();
			String dni = in.next();
			in.next();
			double totalVentas = in.nextDouble();
			in.next();
			double totalComision = in.nextDouble();
			ven = new VendedorJunior(nombre, idIn, dni);
			ven.setTotalVentas(totalVentas);
			ven.setTotalComision(totalComision);
			lista.add(ven);
		}
	}

	private void leerVendedoresPracticas(Scanner in) {
		Vendedor ven;
		while (in.hasNext()) {
			in.next();
			String nombre = in.next();
			in.next();
			String idIn = in.next();
			in.next();
			String dni = in.next();
			in.next();
			double totalVentas = in.nextDouble();
			ven = new VendedorPracticas(nombre, idIn, dni);
			ven.setTotalVentas(totalVentas);
			lista.add(ven);
		}
	}

	/**
	 * Actualiza el fichero datosTienda.txt con los datos actualizados de los
	 * vendedores
	 */
	private void vuelcaDatos() throws DataAccessException {
		PrintWriter out = null;
		List<Vendedor> senior = new LinkedList<Vendedor>();
		List<Vendedor> junior = new LinkedList<Vendedor>();
		List<Vendedor> practicas = new LinkedList<Vendedor>();

		// anhadimos a cada lista sus vendedores
		for (Vendedor v : lista) {
			if (v instanceof VendedorSenior) {
				senior.add(v);
			}
			else if (v instanceof VendedorJunior) {
				junior.add(v);
			}
			else if (v instanceof VendedorPracticas) {
				practicas.add(v);
			}
		}

		// empezamos a escribir el fichero
		try {
			out = new PrintWriter(new FileWriter(datos));

			out.println(nombre);
			out.println(direccion);
			out.println();
			out.println(Textos.TIPO_VEN_SENIOR);
			for (Vendedor v : senior) {
				out.println(Textos.DATO_VEN_NOMBRE + v.getNombre() + Textos.DATO_VEN_ID + v.getId() + Textos.DATO_VEN_DNI + v.getDni()
						+ Textos.DATO_VEN_VENTAS + v.getTotalVentas() + Textos.DATO_VEN_COMISION + v.getTotalComision());
			}
			out.println();
			out.println(Textos.TIPO_VEN_JUNIOR);
			for (Vendedor v : junior) {
				out.println(Textos.DATO_VEN_NOMBRE + v.getNombre() + Textos.DATO_VEN_ID + v.getId() + Textos.DATO_VEN_DNI + v.getDni()
						+ Textos.DATO_VEN_VENTAS + v.getTotalVentas() + Textos.DATO_VEN_COMISION + v.getTotalComision());
			}
			out.println();
			out.println(Textos.TIPO_VEN_PRACTICAS);
			for (Vendedor v : practicas) {
				out.println(Textos.DATO_VEN_NOMBRE + v.getNombre() + Textos.DATO_VEN_ID + v.getId() + Textos.DATO_VEN_DNI + v.getDni()
						+ Textos.DATO_VEN_VENTAS + v.getTotalVentas());
			}
		} catch (IOException e) {
			throw new DataAccessException();

		} finally {
			if (out != null)
				out.close();
		}
	}

	/**
	 * Busca los vendedores que mas han vendido en el mes
	 * 
	 * @return lista con los mejores vendedores del mes
	 */
	public List<Vendedor> buscaVendedoresMes() {

		LinkedList<Vendedor> vendedoresMes = new LinkedList<Vendedor>();
		double maxVentas = 0.0;

		// buscamos entre todos los mejores.
		for (Vendedor v : lista) {
			if (v.getTotalVentas() > maxVentas) {
				maxVentas = v.getTotalVentas();
				vendedoresMes.clear();
				vendedoresMes.add(v);
			} else if (v.getTotalVentas() == maxVentas) {
				vendedoresMes.add(v);
			}
		}
		return vendedoresMes;
	}

	/**
	 * Ordena la lista de vendedores por ventas
	 * 
	 * @return lista de vendedores ordenada por ventas
	 */
	public List<Vendedor> ordenarVendedoresVentas() {

		// esto esta buscado por internet, no entendia lo de una clase dentro de otra.
		lista.sort(Comparator.comparingDouble(Vendedor::getTotalVentas).reversed());
		return lista;
	}

}
