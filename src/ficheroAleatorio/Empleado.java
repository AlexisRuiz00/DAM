package ficheroAleatorio;

public class Empleado {

	private int id = -0;
	private String apellido = "          ";
	private int departamento = 0;
	private double salario = 0;
	
	public Empleado() {		
	}
	
	
	public Empleado(int id, String apellido, int departamento, double salario) {
		
		this.id=id;
		this.apellido=apellido;
		this.departamento=departamento;
		this.salario=salario;
	}
	
	@Override
	public String toString() {
		return "Id: "+id+"\nApellido: "+apellido+"\nDepartamento: "+departamento+"\nSalario: "+salario;
	}

	
	
	
	
	
	
	
	
	//GETTERS AND SETTERS

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public int getDepartamento() {
		return departamento;
	}


	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}


	public double getSalario() {
		return salario;
	}


	public void setSalario(double salario) {
		this.salario = salario;
	}

}
