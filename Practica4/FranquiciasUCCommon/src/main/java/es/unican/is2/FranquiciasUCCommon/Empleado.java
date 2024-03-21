package es.unican.is2.FranquiciasUCCommon;

import java.time.LocalDate;

/**
 * Clase que representa un empleado de la franquicia, con sus datos personales y
 * su estado en la franquicia (baja y categoria)
 */
public class Empleado {

	private String DNI;
	private String nombre;
	private Categoria categoria;
	private LocalDate fechaContratacion;
	private boolean baja = false;

	public Empleado() {
	}

	/**
	 * Constructor del empleado con DNI, nombre, categoria y fecha de contratacion.
	 * Por defecto, baja se inicializa a false.
	 * 
	 * @param DNI
	 * @param nombre
	 * @param categoria
	 * @param fechaContratacion
	 */
	public Empleado(String DNI, String nombre, Categoria categoria, LocalDate fechaContratacion) throws DatoIncorrectoException{
		/* Gestion de excepciones, separadas ya que queremos distintos mensajes segun el atributo*/
		if (DNI == null || DNI.isBlank()) {
			throw new DatoIncorrectoException("Introduce un DNI valido");
		}
		if (nombre == null || nombre.isBlank()) {
			throw new DatoIncorrectoException("Introduce un nombre valido");
		}
		if (categoria == null) {
			throw new DatoIncorrectoException("Introduce una categoria valida");
		}
		if (fechaContratacion == null || LocalDate.now().isBefore(fechaContratacion)) {
			throw new DatoIncorrectoException("Introduce una fechaContratacion valida");
		}
		
		this.nombre = nombre;
		this.DNI = DNI;
		this.categoria = categoria;
		this.fechaContratacion = fechaContratacion;
	}

	/**
	 * Retorna el sueldo bruto del empleado
	 */
	public double sueldoBruto() {
		double sueldoBase = 0;
		double complementoAntiguedad = 0;
		double sueldoTotal = 0;

		LocalDate fechaActual = LocalDate.now();

		/* calculamos el sueldo base segun categoria */
		switch (categoria) {
		case ENCARGADO:
			sueldoBase = 2000;
			break;
		case VENDEDOR:
			sueldoBase = 1500;
			break;
		case AUXILIAR:
			sueldoBase = 1000;
		}

		/* calculamos el complemento por antiguedad */
		if (fechaContratacion.plusYears(20).isBefore(fechaActual)) {
			complementoAntiguedad = 200;

		} else if (fechaContratacion.plusYears(10).isBefore(fechaActual)) {
			complementoAntiguedad = 100;

		} else if (fechaContratacion.plusYears(5).isBefore(fechaActual)) {
			complementoAntiguedad = 50;
		}

		/* calculamos el sueldo total */
		sueldoTotal = sueldoBase + complementoAntiguedad;

		/* miramos si estaba de baja */
		if (baja) {
			sueldoTotal = sueldoTotal * 0.75;
		}

		return sueldoTotal;
	}

	/**
	 * Dar de baja al empleado
	 */
	public void darDeBaja() {
		this.baja = true;
	}

	/**
	 * Dar de alta al empleado
	 */
	public void darDeAlta() {
		this.baja = false;
	}

	/**
	 * Retorna el dni del vendedor
	 * 
	 * @return id
	 */
	public String getDNI() {
		return DNI;
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
	 * Retorna la categoria del empleado
	 * 
	 * @return categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * Retorna la fecha de contrato
	 * 
	 * @return Fecha de contratacion
	 */
	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}

	/**
	 * Retorna si el empleado est� de baja
	 * 
	 * @return true si esta de baja false si no lo esta
	 */
	public boolean getBaja() {
		return baja;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
