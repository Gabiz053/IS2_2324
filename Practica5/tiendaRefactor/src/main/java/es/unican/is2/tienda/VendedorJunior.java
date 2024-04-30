package es.unican.is2.tienda;

public class VendedorJunior extends VendedorPlantilla {

	private static final double VALOR_COMISION = 0.005;

	/**
	 * Retorna un nuevo vendedor junior
	 *
	 * @param nombre
	 * @param dni
	 */
	public VendedorJunior(String nombre, String id, String dni) { // WMC: +1
		super(nombre, id, dni);
	}

	@Override
	public void anhadeVenta(double importe) { // WMC: +1
		totalComision += importe * VALOR_COMISION;
		totalVentas += importe;
	}
}
