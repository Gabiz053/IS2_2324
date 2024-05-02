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

	private List<Vendedor> lista = new LinkedList<>();
	private String direccion;
	private String nombre;

	private final String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 *
	 * @param datos Path absoluto del fichero de datos
	 */
	public Tienda(String datos) { // WMC: +1
		this.datos = datos;

		// iniciamos los datos al crear la tienda
		vendedores();
	}

	/**
	 * Retorna la direccion de la tienda
	 *
	 * @return Direccion de la tienda
	 */
	public String direccion() { // WMC: +1
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 *
	 * @return Nombre de la tienda
	 */
	public String nombre() { // WMC: +1
		return nombre;
	}

	/**
	 * Anhade un nuevo vendedor a la tienda
	 *
	 * @param nuevo El vendedor a anhadir
	 * @return true si el vendedor se ha anhadido false si ya existe el vendedor
	 */
	public boolean anhadeVendedor(Vendedor nuevo) throws DataAccessException { // WMC: +1
		// miramos que no exista ya ese vendedor
		Vendedor v = buscaVendedor(nuevo.getId());
		if (v != null) { // WMC: +1 //CCOG: +1
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
	public boolean eliminaVendedor(String id) throws DataAccessException { // WMC: +1
		// miramos que exista ya ese vendedor
		Vendedor v = buscaVendedor(id);
		if (v == null) { // WMC: +1 //CCOG: +1
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
	public boolean anhadeVentaVendedor(String id, double importe) throws DataAccessException { // WMC: +1
		// miramos si existe el vendedor
		Vendedor v = buscaVendedor(id);
		if (v == null) { // WMC: +1 //CCOG: +1
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
	public Vendedor buscaVendedor(String id) { // WMC: +1

		lista = vendedores();

		// miramos si existe en la lista
		for (Vendedor v : lista) { // WMC: +1 //CCOG: +1
			if (v.getId().equals(id)) { // WMC: +1 //CCOG: +1 +1
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
	public List<Vendedor> vendedores() { // WMC: +1
		lista = new LinkedList<>();

		try (Scanner in = new Scanner(new FileReader(datos))) {

			// configura el formato de numeros
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();

			// deben estar en este orden porque es el del ficehero
			leerVendedoresSenior(in);
			leerVendedoresJunior(in);
			leerVendedoresPracticas(in);

		} catch (FileNotFoundException e) { // WMC: +1 //CCOG: +1
		}
		return lista;
	}

	private void leerVendedoresSenior(Scanner in) { // WMC: +1
		VendedorSenior ven;
		while (in.hasNext() && !in.next().equals(Textos.TIPO_VEN_JUNIOR)) { // WMC: +1 +1 //CCOG: +1 +1
			String nombreVendedor = in.next();
			in.next();
			String idVendedor = in.next();
			in.next();
			String dniVendedor = in.next();
			in.next();
			double totalVentas = in.nextDouble();
			in.next();
			double totalComision = in.nextDouble();
			ven = new VendedorSenior(nombreVendedor, idVendedor, dniVendedor);
			ven.setTotalVentas(totalVentas);
			ven.setTotalComision(totalComision);
			lista.add(ven);
		}
	}

	private void leerVendedoresJunior(Scanner in) { // WMC: +1
		VendedorJunior ven;
		while (in.hasNext() && !in.next().equals(Textos.TIPO_VEN_PRACTICAS)) { // WMC: +1 +1 //CCOG: +1 +1
			String nombreVendedor = in.next();
			in.next();
			String idVendedor = in.next();
			in.next();
			String dniVendedor = in.next();
			in.next();
			double totalVentas = in.nextDouble();
			in.next();
			double totalComision = in.nextDouble();
			ven = new VendedorJunior(nombreVendedor, idVendedor, dniVendedor);
			ven.setTotalVentas(totalVentas);
			ven.setTotalComision(totalComision);
			lista.add(ven);
		}
	}

	private void leerVendedoresPracticas(Scanner in) { // WMC: +1
		VendedorPracticas ven;
		while (in.hasNext()) { // WMC: +1 //CCOG: +1
			in.next();
			String nombreVendedor = in.next();
			in.next();
			String idVendedor = in.next();
			in.next();
			String dniVendedor = in.next();
			in.next();
			double totalVentas = in.nextDouble();
			ven = new VendedorPracticas(nombreVendedor, idVendedor, dniVendedor);
			ven.setTotalVentas(totalVentas);
			lista.add(ven);
		}
	}

	/**
	 * Actualiza el fichero datosTienda.txt con los datos actualizados de los
	 * vendedores
	 */
	private void vuelcaDatos() throws DataAccessException { // WMC: +1
		List<Vendedor> senior = new LinkedList<>();
		List<Vendedor> junior = new LinkedList<>();
		List<Vendedor> practicas = new LinkedList<>();

		// anhadimos a cada lista sus vendedores
		for (Vendedor v : lista) { // WMC: +1 //CCOG: +1
			if (v instanceof VendedorSenior) { // WMC: +1 //CCOG: +1 +1
				senior.add(v);
			} else if (v instanceof VendedorJunior) { // WMC: +1 //CCOG: +1
				junior.add(v);
			} else if (v instanceof VendedorPracticas) { // WMC: +1 //CCOG: +1
				practicas.add(v);
			}
		}

		// empezamos a escribir el fichero
		try (PrintWriter out = new PrintWriter(new FileWriter(datos));) {
			out.println(nombre);
			out.println(direccion);
			out.println();
			out.println(Textos.TIPO_VEN_SENIOR);
			pintaVenderoresPlantilla(out, senior);
			out.println();
			out.println(Textos.TIPO_VEN_JUNIOR);
			pintaVenderoresPlantilla(out, junior);
			out.println();
			out.println(Textos.TIPO_VEN_PRACTICAS);
			pintaVendedoresPracticas(out, practicas);
		} catch (IOException e) { // WMC: +1 //CCOG: +1
			throw new DataAccessException();
		}
	}

	private void pintaVenderoresPlantilla(PrintWriter out, List<Vendedor> vendedores) { // WMC: +1
		for (Vendedor v : vendedores) { // WMC: +1 //CCOG: +1
			out.println(Textos.DATO_VEN_NOMBRE + v.getNombre() + Textos.DATO_VEN_ID + v.getId() + Textos.DATO_VEN_DNI
					+ v.getDni() + Textos.DATO_VEN_VENTAS + v.getTotalVentas() + Textos.DATO_VEN_COMISION
					+ ((VendedorPlantilla) v).getTotalComision());
		}
	}

	private void pintaVendedoresPracticas(PrintWriter out, List<Vendedor> practicas) { // WMC: +1
		for (Vendedor v : practicas) { // WMC: +1 //CCOG: +1
			out.println(Textos.DATO_VEN_NOMBRE + v.getNombre() + Textos.DATO_VEN_ID + v.getId() + Textos.DATO_VEN_DNI
					+ v.getDni() + Textos.DATO_VEN_VENTAS + v.getTotalVentas());
		}
	}

	/**
	 * Busca los vendedores que mas han vendido en el mes
	 *
	 * @return lista con los mejores vendedores del mes
	 */
	public List<Vendedor> buscaVendedoresMes() { // WMC: +1

		LinkedList<Vendedor> vendedoresMes = new LinkedList<>();
		double maxVentas = 0.0;

		// buscamos entre todos los mejores.
		for (Vendedor v : lista) { // WMC: +1 //CCOG: +1
			if (v.getTotalVentas() > maxVentas) { // WMC: +1 //CCOG: +1 +1
				maxVentas = v.getTotalVentas();
				vendedoresMes.clear();
				vendedoresMes.add(v);
			} else if (v.getTotalVentas() == maxVentas) { // WMC: +1 //CCOG: +1
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
	public List<Vendedor> ordenarVendedoresVentas() { // WMC: +1

		// esto esta buscado por internet, no entendia lo de una clase dentro de otra.
		lista.sort(Comparator.comparingDouble(Vendedor::getTotalVentas).reversed());
		return lista;
	}

}
