package es.unican.is2.tienda;

public abstract class VendedorPlantilla extends Vendedor {

	protected double totalComision;

	protected VendedorPlantilla(String nombre, String id, String dni) { // WMC: +1
		super(nombre, id, dni);
		totalComision = 0;
	}

	/**
	 * Retorna la comision mensual acumulada
	 *
	 * @return Comision total acumulada
	 */
	public double getTotalComision() { // WMC: +1
		return totalComision;
	}

	/**
	 * Asigna valor a la comision mensual acumulada
	 *
	 * @param value comision a asignar
	 */
	public void setTotalComision(double value) { // WMC: +1
		totalComision = value;
	}

	@Override
	public boolean equals(Object obj) { // WMC: +1
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
