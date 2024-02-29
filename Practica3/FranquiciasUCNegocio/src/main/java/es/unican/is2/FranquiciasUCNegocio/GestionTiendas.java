package es.unican.is2.FranquiciasUCNegocio;

import es.unican.is2.FranquiciasUCCommon.DataAccessException;
import es.unican.is2.FranquiciasUCCommon.IGestionTiendas;
import es.unican.is2.FranquiciasUCCommon.OperacionNoValidaException;
import es.unican.is2.FranquiciasUCCommon.Tienda;

import es.unican.is2.FranquiciasUCCommon.ITiendasDAO;

public class GestionTiendas implements IGestionTiendas {

	public GestionTiendas(ITiendasDAO tiendasDAO) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Tienda nuevaTienda(Tienda t) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tienda tienda(String nombre) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
