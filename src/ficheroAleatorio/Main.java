package ficheroAleatorio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class Main {
	
	public static String construirApellido(RandomAccessFile f) throws IOException {
		
		String apellido = "";
	
		
		for(int i=0;i<10;i++) {
			apellido+=f.readChar();
		}
		return apellido;
	}

	public static String pedirDato() {
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
	
			try {
				return br.readLine();
				
			}catch(Exception e) 
			{
				return "Valor no valido";				
			}

	}
	
	
	public static boolean existeId(RandomAccessFile f, int id) {
		
		int tamanoRegistro = 36;
		int posicion = tamanoRegistro*(id-1);
		
			try {
				f.seek(posicion);
					if(id == f.readInt()) {
						return true;}
						else return false;
			}catch(IOException e) {
				return false;
			}
	}
	
	public static boolean altaEmpleado(Empleado e, RandomAccessFile f) throws IOException {
		
		int tamanoRegistro = 36;
		int posicion = tamanoRegistro*(e.getId()-1);
		

					if(!existeId(f, e.getId())){
								f.writeInt(e.getId());
								f.writeChars(e.getApellido());
								f.writeInt(e.getDepartamento());
								f.writeDouble(e.getSalario());
								
								System.out.println("¡Registrado con exito!");
								return true;
							}else {
								System.out.println("¡Ya existe el id!");
								return false;
							}
								
		}
	
	
	public static boolean consultaEmpleado(int id ,RandomAccessFile f) throws IOException {
		
		int tamanoRegistro = 36;
		int posicion = tamanoRegistro*(id-1);
		
		if(!existeId(f, id)) {
			System.out.println("¡No existe el registro!");
			return false;
				}else{
							f.seek(posicion);
							Empleado e = new Empleado(f.readInt(),construirApellido(f),f.readInt(),f.readDouble());
							System.out.println(e);
							return true;
				}
	}
	
	
	public static void main(String[] args) throws IOException {
	
		
		int opc =-1;
		File fichero = new File("empleados.dat");
				
		RandomAccessFile empleados = new RandomAccessFile(fichero, "rw");

		Empleado x = new  Empleado(1,"martinez  ",2,150);
		Empleado s = new  Empleado(1,"cinco     ",2,150);
		Empleado a = new  Empleado(5,"martinez  ",5,350);
		
		
		altaEmpleado(x, empleados);
		altaEmpleado(a, empleados);
		altaEmpleado(s, empleados);
			
		
		
		do {		
			
					System.out.print( "\n\n1- Dar de alta empleado"
									+	"\n2- Dar de baja empleado"
									+	"\n3- Conslutar empleado"
									+	"\n4- Modificar empleado"
									+	"\n5- Salir"
									+	"\n: "
							);
					
						do {	
							try {
								opc = Integer.parseInt(pedirDato());
								System.out.println();
								if(opc<0 || opc >5) {System.err.println("Opción no valida, vuelva a introducir una por favor: ");}
							}catch(NumberFormatException e){System.err.println("Opción no valida, vuelva a introducir una por favor: ");}
						
						
					
					}while(opc<0 || opc >5);
					
					switch(opc) {
					
					case 1:
						
						boolean flag = false;
						Empleado empleadoTmp = new Empleado();
						String apellido = "";
						
						System.out.println("Id: ");
						
						
						//PEDIR ID
						do {
							try {
									empleadoTmp.setId(Integer.parseInt(pedirDato()) );
									if(existeId(empleados,empleadoTmp.getId())) {
										System.out.println("¡El id ya está registrado!");
									}else {
										flag = true;
										System.out.println();	
									}}catch(NumberFormatException e){
										System.err.println("Id no válido, introduzca un id válido por favor");}
						}while(!flag);
						flag = false;
						
						//PEDIR APELLIDO
						System.out.println("Apellido: ");
						
						do {
								apellido = pedirDato();
								if(apellido.length() == 10) {
									flag=true;
								}else if(apellido.length() == 10)	{
									//RELLENAR HASTA QUE SEA 10
									flag = true;
								}else {System.err.println("El apellido no puede tener más de 10 carácteres");}
						}while(!flag);
						empleadoTmp.setApellido(apellido);
						flag = false;
						
						//PEDIR DEPARTAMENTO
						//PEDIR SALARIO
								
						
						
						altaEmpleado(a, empleados);
						break;
						
					case 2:
						
						break;
					
					case 3:
						int id = 0;
						
						try {
							id = Integer.parseInt(pedirDato());
							System.out.println();
						}catch(NumberFormatException e){System.err.println("Id no válido, vuelva a introducir uno por favor: ");}															
						consultaEmpleado(id, empleados);				
						break;
						
						
					case 5:
						empleados.close();
						break;
						
					}
					
					
					
					
			}while(opc!=5);
		
		
			
			
	}}//end}
