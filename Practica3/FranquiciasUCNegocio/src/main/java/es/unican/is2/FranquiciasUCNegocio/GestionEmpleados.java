package es.unican.is2.FranquiciasUCNegocio;

import es.unican.is2.FranquiciasUCCommon.DataAccessException;
import es.unican.is2.FranquiciasUCCommon.Empleado;
import es.unican.is2.FranquiciasUCCommon.IGestionEmpleados;
import es.unican.is2.FranquiciasUCCommon.OperacionNoValidaException;

import es.unican.is2.FranquiciasUCCommon.ITiendasDAO;
import es.unican.is2.FranquiciasUCCommon.IEmpleadosDAO;

public class GestionEmpleados implements IGestionEmpleados {

	public GestionEmpleados(ITiendasDAO tiendasDAO, IEmpleadosDAO empleadosDAO) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean trasladarEmpleado(String dni, String actual, String destino)
			throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Empleado empleado(String dni) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
