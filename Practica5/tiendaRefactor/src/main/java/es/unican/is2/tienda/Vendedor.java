package es.unican.is2.tienda;

/**
 * Vendedor de la tienda. Por cada vendedor se almacenan sus datos personales y
 * sus datos sobre ventas y comisiones
 */
public abstract class Vendedor {

	private String id;
	private String nombre;
	private String dni;
	protected double totalComision;
	protected double totalVentas;

	public Vendedor(String nombre, String id, String dni) {
		this.nombre = nombre;
		this.id = id;
		this.dni = dni;
		totalComision = 0;
		totalVentas = 0;
	}

	/**
	 * Retorna el nombre del vendedor
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Retorna el id del vendedor
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Retorna el dni del vendedor
	 * 
	 * @return dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Retorna la comision mensual acumulada
	 * 
	 * @return Comision total acumulada
	 */
	public double getTotalComision() {
		return totalComision;
	}

	/**
	 * Asigna valor a la comision mensual acumulada
	 * 
	 * @param value comision a asignar
	 */
	public void setTotalComision(double value) {
		this.totalComision = value;
	}

	/**
	 * Retorna el importe total mensual de ventas
	 * 
	 * @return importe total de ventas acumuladas
	 */
	public double getTotalVentas() {
		return totalVentas;
	}

	/**
	 * Asigna valor al total de ventas mensual
	 * 
	 * @param value total de ventas a asignar
	 */
	public void setTotalVentas(double value) {
		totalVentas = value;
	}

	/**
	 * Anhade una nueva venta al vendedor contando la comision
	 * 
	 * @param importe de la venta
	 */
	public void anhadeVenta(double importe) {
		totalVentas += importe;
	}

	@Override
	public boolean equals(Object obj) {
		// No es una instancia de Vendedor o es null
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Vendedor v = (Vendedor) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni()));
	}
}
