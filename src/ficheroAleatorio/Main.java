package ficheroAleatorio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class Main {

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
	
	public static boolean altaEmpleado(Empleado e, RandomAccessFile f) throws IOException {
		
		if(consultaEmpleado(e.getId(),f)) {
			return false;
		}else {
			f.write(e.getId());
			f.writeChars(e.getApellido());
			f.writeInt(e.getDepartamento());
			f.writeDouble(e.getSalario());
			f.close();
			return true;
		}
		
		
	}
	
	
	
	
	public static boolean consultaEmpleado(int id ,RandomAccessFile f) throws IOException {
		
		int tamanoRegistro = 36;
		int posicion = tamanoRegistro*(id-1);
		f.seek(posicion);
		
		if(id == f.readInt()) {
			return true;
		}else {return false;}

	}
	
	
	
	
	public static void main(String[] args) throws IOException {
	
		
		int opc =-1;
		File fichero = new File("empleados.dat");
				
		RandomAccessFile empleados = new RandomAccessFile(fichero, "rw");

		
			
		
		
		do {		
			
					System.out.println( "\n\n1- Dar de alta empleado"
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
						Empleado x = new  Empleado(1,"martinez  ",2,150);
						Empleado s = new  Empleado(1,"cinco     ",2,150);
						Empleado a = new  Empleado(5,"martinez  ",5,350);
						
						
						altaEmpleado(x, empleados);
						altaEmpleado(a, empleados);
						altaEmpleado(s, empleados);
					
						break;
					}
					
					
					
			}while(opc!=5);
		
		
			
			
	}}//end}
