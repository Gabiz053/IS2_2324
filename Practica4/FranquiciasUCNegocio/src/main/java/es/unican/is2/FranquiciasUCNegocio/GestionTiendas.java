package es.unican.is2.FranquiciasUCNegocio;

import es.unican.is2.FranquiciasUCCommon.DataAccessException;
import es.unican.is2.FranquiciasUCCommon.IGestionTiendas;
import es.unican.is2.FranquiciasUCCommon.OperacionNoValidaException;
import es.unican.is2.FranquiciasUCCommon.Tienda;

import es.unican.is2.FranquiciasUCCommon.ITiendasDAO;

public class GestionTiendas implements IGestionTiendas {

	private ITiendasDAO tiendasDAO;

	public GestionTiendas(ITiendasDAO tiendasDAO) {
		this.tiendasDAO = tiendasDAO;
	}

	@Override
	public Tienda nuevaTienda(Tienda t) throws DataAccessException {
		return tiendasDAO.crearTienda(t);
	}

	@Override
	public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {
		Tienda tienda = tienda(nombre);
		tiendasDAO.eliminarTienda(tienda.getId());
		return tienda;
	}

	@Override
	public Tienda tienda(String nombre) throws DataAccessException {
		return tiendasDAO.tiendaPorNombre(nombre);
	}

}
