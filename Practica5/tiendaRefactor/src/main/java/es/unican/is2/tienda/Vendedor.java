package es.unican.is2.tienda;

/**
 * Vendedor de la tienda. Por cada vendedor se almacenan sus datos personales y
 * sus datos sobre ventas y comisiones
 */
public abstract class Vendedor {

	private final String id;
	private final String nombre;
	private final String dni;
	protected double totalVentas;

	protected Vendedor(String nombre, String id, String dni) { // WMC: +1
		this.nombre = nombre;
		this.id = id;
		this.dni = dni;
		totalVentas = 0;
	}

	/**
	 * Retorna el nombre del vendedor
	 *
	 * @return nombre
	 */
	public String getNombre() { // WMC: +1
		return nombre;
	}

	/**
	 * Retorna el id del vendedor
	 *
	 * @return id
	 */
	public String getId() { // WMC: +1
		return id;
	}

	/**
	 * Retorna el dni del vendedor
	 *
	 * @return dni
	 */
	public String getDni() { // WMC: +1
		return dni;
	}

	/**
	 * Retorna el importe total mensual de ventas
	 *
	 * @return importe total de ventas acumuladas
	 */
	public double getTotalVentas() { // WMC: +1
		return totalVentas;
	}

	/**
	 * Asigna valor al total de ventas mensual
	 *
	 * @param value total de ventas a asignar
	 */
	public void setTotalVentas(double value) { // WMC: +1
		totalVentas = value;
	}

	/**
	 * Anhade una nueva venta al vendedor contando la comision
	 *
	 * @param importe de la venta
	 */
	public void anhadeVenta(double importe) { // WMC: +1
		totalVentas += importe;
	}

	@Override
	public boolean equals(Object obj) { // WMC: +1
		// No es una instancia de Vendedor o es null
		if ((obj == null) || (getClass() != obj.getClass())) { // WMC: +1 +1 //CCOG: +1 +1
			return false;
		}

		Vendedor v = (Vendedor) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni())); // WMC: +1 +1 //CCOG: +1 +1
	}
}
