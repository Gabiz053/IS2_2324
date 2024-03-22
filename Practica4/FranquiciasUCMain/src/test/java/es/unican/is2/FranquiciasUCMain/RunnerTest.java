package es.unican.is2.FranquiciasUCMain;

import org.fest.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;

import es.unican.is2.FranquiciasUCDatos.EmpleadosDAO;
import es.unican.is2.FranquiciasUCDatos.TiendasDAO;
import es.unican.is2.FranquiciasUCNegocio.GestionEmpleados;
import es.unican.is2.FranquiciasUCNegocio.GestionTiendas;
import es.unican.is2.FranquiciasUCPresentacion.VistaGerente;

class RunnerTest {

	/*
	 * tenemos que crear un runner en si porque necesitamos conseguir datos de la
	 * BBDD y comunicarnos con toda la aplicacion
	 */

	// Crear componentes capa DAO
	TiendasDAO tiendasDAO = new TiendasDAO();
	EmpleadosDAO empleadosDAO = new EmpleadosDAO();

	// Crear componentes capa negocio
	GestionTiendas gTiendas = new GestionTiendas(tiendasDAO);
	GestionEmpleados gEmpleados = new GestionEmpleados(tiendasDAO, empleadosDAO);

	private FrameFixture demo;
	private VistaGerente vista;

	@BeforeEach
	public void setUp() {
		// Crear componentes capa presentacion
		vista = new VistaGerente(gTiendas, gEmpleados);
		demo = new FrameFixture(vista);
		vista.setVisible(true);
	}

	@AfterEach
	public void tearDown() {
		demo.cleanUp();
	}

	@Test
	void testConsultaTienda() {
		
		// tienda con empleados
		demo.textBox("txtNombreTienda").setText(null);
		demo.textBox("txtNombreTienda").enterText("Tienda A");
		demo.button("btnBuscar").click();
		demo.textBox("txtDireccionTienda").requireText("Tienda A");
		demo.list("listNombreEmpleados").requireItemCount(3);
		demo.textBox("txtTotalSueldos").requireText("4362.5");
		
		// tienda con empleado
		demo.textBox("txtNombreTienda").setText(null);
		demo.textBox("txtNombreTienda").enterText("Tienda B");
		demo.button("btnBuscar").click();
		demo.textBox("txtDireccionTienda").requireText("Tienda B");
		demo.list("listNombreEmpleados").requireItemCount(1);
		demo.textBox("txtTotalSueldos").requireText("2100.0");
		
		// tienda sin empleados
		demo.textBox("txtNombreTienda").setText(null);
		demo.textBox("txtNombreTienda").enterText("Tienda C");
		demo.button("btnBuscar").click();
		demo.textBox("txtDireccionTienda").requireText("Tienda C");
		demo.list("listNombreEmpleados").requireItemCount(0);
		demo.textBox("txtTotalSueldos").requireText("0.0");
		
		// tienda no existe
		demo.textBox("txtNombreTienda").setText(null);
		demo.textBox("txtNombreTienda").enterText("Tienda D");
		demo.button("btnBuscar").click();
		demo.textBox("txtDireccionTienda").requireText("Tienda no existe");


	}

}