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
						return true;
					}else { return false;}
			}catch(IOException e) {
				return false;
			}
	}
	
	public static boolean altaEmpleado(Empleado e, RandomAccessFile f) throws IOException {
		
		int tamanoRegistro = 36;
		int posicion = tamanoRegistro*(e.getId()-1);
		

					if(!existeId(f, e.getId())){
								f.seek(posicion);
								f.writeInt(e.getId());
								f.writeChars(e.getApellido());
								f.writeInt(e.getDepartamento());
								f.writeDouble(e.getSalario());
								
								System.out.println("¡Registrado con exito!");
								return true;
							}else {
								System.err.println("¡YA EXISTE EL ID " + e.getId() + "!");
								return false;
							}
								
		}
	
	
	public static boolean consultaEmpleado(int id ,RandomAccessFile f) throws IOException {
		
		int tamanoRegistro = 36;
		int posicion = tamanoRegistro*(id-1);
		
		if(!existeId(f, id)) {
			System.err.print("¡NO EXISTE EL REGISTRO!");
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
		int idTmp = 0;
		int depTmp = 0;
		double salTmp = 0;
		String apellido = "";
		
		
		RandomAccessFile empleados = new RandomAccessFile(fichero, "rw");

		Empleado x = new  Empleado(1,"martinez  ",2,150);
		Empleado s = new  Empleado(1,"cinco     ",2,150);
		Empleado a = new  Empleado(5,"martinez  ",5,350);
		
		
		altaEmpleado(x, empleados);
		altaEmpleado(a, empleados);
		altaEmpleado(s, empleados);
			
		
		//MENU PRINCIPAL
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
	
					
	//DAR DE ALTA UN EMPLEADO
					case 1:
						
						boolean flag = false;
						boolean exit = false;
						idTmp = 0;
						depTmp = 0;
						salTmp = 0;
						apellido = "";
						
						
						//PEDIR ID		
						do {
							System.out.print("ID: ");
							try {
									idTmp = Integer.parseInt(pedirDato());
									if(existeId(empleados,idTmp)) {
										System.err.println("YA EXISTE EL REGISTRO!");
										System.out.print("\n¿Quieres introducir otro id? (S/N) ");
												if(pedirDato().equalsIgnoreCase("S")) {
													//REPETIR PEDIR ID
													flag = false;													
													//SALIR
												}else flag=true;exit = true;
									}else {
										flag = true;
										exit = false;
										
									}}catch(NumberFormatException e){
										System.err.print("¡NO ES UN ID!\nIntroduzca id válido por favor (numero entero)\n");}
						}while(!flag);
						flag = false;
						
						if (!exit) {
								
								//PEDIR APELLIDO
									do {
										System.out.print("Apellido: ");
											apellido = pedirDato();
											
											if(apellido.length() == 10) {
												flag=true;
												
											}else if(apellido.length() < 10)	{
												//RELLENAR HASTA QUE SEA 10
													apellido = String.format("%-10s",apellido);					
													flag = true;
											}else {
												System.err.println("El apellido no puede tener más de 10 carácteres\n\n");
												}
									}while(!flag);
									flag = false;
					
									
								//PEDIR DEPARTAMENTO
									do {	
										System.out.print("Departamento: ");
										try {
											depTmp = Integer.parseInt(pedirDato());
											flag=true;
										}catch(NumberFormatException e){System.err.println("Departamaento no válido, vuelva a introducir uno por favor");}
									}while(!flag);
									flag=false;		
									
									
									
								//PEDIR SALARIO
									do {	
										System.out.print("Salario: ");
										try {
											 salTmp = Double.parseDouble(pedirDato());
											 altaEmpleado(new Empleado(idTmp,apellido,depTmp,salTmp), empleados);
											flag=true;
										}catch(NumberFormatException e){System.err.println("Salario no válido, vuelva a introducir uno por favor :");}
									}while(!flag);
									flag = false;	
								
						}else{//doNothing
							}
						break;
						
						
	//DAR DE BAJA UN EMPLEADO		
					case 2:
						flag = false;
						
						do {	
							try {
								System.out.print("ID: ");
								idTmp = Integer.parseInt(pedirDato());
								
								if( !existeId(empleados,idTmp)) {
									System.err.println("¡NO EXISTE EL REGISTRO!");
								}else {
										consultaEmpleado(idTmp, empleados);
										System.out.print("\n¿Quieres eliminar este registro?\n(S/N) ");
										
										if(pedirDato().equalsIgnoreCase("S")) {
											
											empleados.seek(empleados.getFilePointer()-36);
											empleados.writeInt(-1);
											System.out.print("¡Eliminado con exito!\n\n");
											flag = true;
										}}
											System.out.println("\n¿Quieres eliminar otro registro?\n(S/N)");
											if(pedirDato().equalsIgnoreCase("S")) {
												//doNothing
											}else flag=true;
										
							}catch(NumberFormatException e){System.err.println("No existe el empleado, vuelva a introducir un id por favor");}
						}while(!flag);
					
						break;
					
						
						
						
	//CONSULTAR UN EMPLEADO
					case 3:
						
						flag = false;
						int id = 0;
						
						do {
							System.out.print("ID: ");
							try {
								id = Integer.parseInt(pedirDato());
								if(consultaEmpleado(id, empleados)) {flag=true;}
								else {
									System.out.print("\n¿Quieres introducir otro id?\n(S/N)");
									if(pedirDato().equalsIgnoreCase("S")) {
										//doNothing
									}else flag=true;
								}
								System.out.println();
							}catch(NumberFormatException e){
								System.err.println("ID no válido!");
								System.out.print("¿Quieres introducir otro id?\n(S/N)");
								if(pedirDato().equalsIgnoreCase("S")) {
									//doNothing
								}else flag=true;
							}															
						}while(!flag);
											
						break;
			
						
	//MODIFICAR EMPLEADO					
					case 4:
						flag = false;
						
						//PEDIR ID		
						do {
							try {
									System.out.print("ID: ");
									idTmp = Integer.parseInt(pedirDato());
									if(existeId(empleados,idTmp)) {
										
								//SI ID CORRECTO, PEDIR SALARIO
										System.out.print("NUEVO SALARIO: ");
										do {	
											try {
												 salTmp = Double.parseDouble(pedirDato());
												 empleados.seek(36*(idTmp-1)+4+20+4);
												 empleados.writeDouble(salTmp);
												 
												flag=true;
											}catch(NumberFormatException e){System.err.print("SALARIO NO VALIDO\nVuelva a introducir uno por favor :");}
										}while(!flag);	
										
										
								//SINO, PEDIR ID DE NUEVO	
									}else {
										System.err.print("NO EXISTE EL REGISTRO!");
										System.out.print("\n¿Quieres introducir otro id?\n(S/N)");
										if(pedirDato().equalsIgnoreCase("S")) {
											//doNothing
										}else flag=true;
									}}catch(NumberFormatException e){
										System.err.print("NO EXISTE EL REGISTRO!");
										System.out.print("\n¿Quieres introducir otro id?\n(S/N)");
										if(pedirDato().equalsIgnoreCase("S")) {
											//doNothing
										}else flag=true;}
						}while(!flag);
						flag = false;
						break;
						
	//SALIR DE LA APLICACIÓN					
					case 5:
						empleados.close();
						break;
					}
					
			}while(opc!=5);
		
		
			
			
	}}//end}
