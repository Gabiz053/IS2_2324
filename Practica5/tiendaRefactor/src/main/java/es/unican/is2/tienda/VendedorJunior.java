package es.unican.is2.tienda;

public class VendedorJunior extends Vendedor {

	private static final double VALOR_COMISION = 0.005;

	/**
	 * Retorna un nuevo vendedor junior
	 * 
	 * @param nombre
	 * @param dni
	 */
	public VendedorJunior(String nombre, String id, String dni) {
		super(nombre, id, dni);
	}

	@Override
	public void anhadeVenta(double importe) {
		totalComision += importe * VALOR_COMISION;
		totalVentas += importe;
	}
}
