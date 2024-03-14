package es.unican.is2.FranquiciasUCNegocio;

import es.unican.is2.FranquiciasUCCommon.DataAccessException;
import es.unican.is2.FranquiciasUCCommon.Empleado;
import es.unican.is2.FranquiciasUCCommon.IGestionEmpleados;
import es.unican.is2.FranquiciasUCCommon.OperacionNoValidaException;
import es.unican.is2.FranquiciasUCCommon.Tienda;
import es.unican.is2.FranquiciasUCCommon.ITiendasDAO;
import es.unican.is2.FranquiciasUCCommon.IEmpleadosDAO;

public class GestionEmpleados implements IGestionEmpleados {

	private ITiendasDAO tiendasDAO;
	private IEmpleadosDAO empleadosDAO;

	public GestionEmpleados(ITiendasDAO tiendasDAO, IEmpleadosDAO empleadosDAO) {
		this.tiendasDAO = tiendasDAO;
		this.empleadosDAO = empleadosDAO;
	}

	@Override
	public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException {
		/* primero miramos si existe la tienda */
		Tienda tienda = tiendasDAO.tiendaPorNombre(nombre);
		if (tienda == null) {
			return null;
		}

		/* añadimos de manera ilegal :( */
		tienda.getEmpleados().add(e);
		
		/* modificamos en la bbdd */
		tiendasDAO.modificarTienda(tienda);

		return e;
	}

	@Override
	public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {
		Empleado empleado = empleadosDAO.empleado(dni);
		Tienda tienda = tiendasDAO.tiendaPorNombre(nombre);
		
		/* miramos si alguno no existe */
		if (empleado == null || tienda == null) {
			return null;
		}
		
		/* eliminamos de manera ilegal :( */
		tienda.getEmpleados().remove(empleado);
		
		/* modificamos en la bbdd */
		tiendasDAO.modificarTienda(tienda);
		
		return empleado;
	}

	@Override
	public boolean trasladarEmpleado(String dni, String actual, String destino)
			throws OperacionNoValidaException, DataAccessException {
		Empleado empleado = empleadosDAO.empleado(dni);
		Tienda tiendaActual = tiendasDAO.tiendaPorNombre(actual);
		Tienda tiendaDestino = tiendasDAO.tiendaPorNombre(destino);
		
		/* miramos si alguno no existe */
		if (empleado == null || tiendaActual == null || tiendaDestino == null) {
			return false;
		}
		
		/* empezamos el traslado */
		tiendaActual.getEmpleados().remove(empleado);
		tiendaDestino.getEmpleados().add(empleado);
		
		/* modificamos en la bbdd */
		tiendasDAO.modificarTienda(tiendaActual);
		tiendasDAO.modificarTienda(tiendaDestino);
		
		return true;
	}

	@Override
	public Empleado empleado(String dni) throws DataAccessException {
		empleadosDAO.empleado(dni);
		return null;
	}

}
