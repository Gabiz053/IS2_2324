package es.unican.is2.tienda;

public class VendedorSenior extends Vendedor {

	private static final double VALOR_COMISION = 0.01;

	/**
	 * Retorna un nuevo vendedor Senior
	 *
	 * @param nombre
	 * @param dni
	 */
	public VendedorSenior(String nombre, String id, String dni) { // WMC: +1
		super(nombre, id, dni);
	}

	@Override
	public void anhadeVenta(double importe) { // WMC: +1
		totalComision += importe * VALOR_COMISION;
		totalVentas += importe;
	}
}
