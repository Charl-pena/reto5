package com.reto5;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

public class Reto5 {
	private static final String ingresosClave = "INGRESOS";
	private static final String retirosClave= "RETIROS";
	
	private static double PedirDecimal(Scanner scanner, String mensaje)
	{
		double numero = 0;
		Boolean esInvalido= true;
		
	    do{
	           System.out.println(mensaje);
	           String entrada = scanner.nextLine();	    	 
	    	 
	            try {
	                numero = Double.parseDouble(entrada); 
	                esInvalido = false; 
	            } catch (NumberFormatException e) {
	                System.out.println("Entrada inválida. Asegúrate de introducir un número decimal.");
	            }
	       }while(esInvalido);
	    
	    return numero;
	}
	
	private static char PedirOpcion(Scanner scanner, String mensaje, char[] opcionesValidas)
	{
		char opcionIn = '1';
		Boolean esInvalido= true;
		
	    do{
	           System.out.println(mensaje);
	           opcionIn = scanner.nextLine().toLowerCase().charAt(0);	
	            
	            for (char o : opcionesValidas) {
	            	if(opcionIn == o )
	            	{
	            		return opcionIn;
	            	}
	            }
	            
	            System.out.println("Opción Invalida por favor ingrese una opción correcta.");
	       }while(esInvalido);
	    
	    return opcionIn;
	}

	public static void main(String[] args) {
		
		String userName, userPassword;
		double saldoActual = 0;
		char opcion;
		HashMap<String, Integer> historial= new HashMap<>();
		
		historial.put(ingresosClave, 0);
		historial.put(retirosClave, 0);
		
		Scanner scanner = new Scanner(System.in);
		
		Boolean noEsCorrecto = true;
		do {
			System.out.println("Ingresa tu usuario: ");
			userName = scanner.nextLine().toLowerCase();	
			System.out.println("¿Es correcto? (Yes (y) o No (n)) " + userName );
			opcion = scanner.nextLine().toLowerCase().charAt(0);	
			noEsCorrecto = (opcion == 'y') ? false : true ;
		}while(noEsCorrecto);
		
		System.out.println("Ingresa tu contraseña: ");
		userPassword = scanner.nextLine();
		
		System.out.println("Has accedido!");
		
		do {
			System.out.println("Selecciona una Opción: ");
			System.out.println("1) Consultar Saldo");		
			System.out.println("2) Ingresar dinero");
			System.out.println("3) Retirar dinero");
			System.out.println("4) Consultar sus ultimos movimientos");
			System.out.println("5) Convertir divisas");
			System.out.println("----------------------- Presione 0 para salir");
			
			opcion = scanner.nextLine().charAt(0);
			
			switch (opcion) {
			case '1':
				System.out.println("Saldo Actual: " + saldoActual);		
				break;
			case '2':
				
				System.out.println(" (Limite $10,000.00)");
				double cantidadIngresada = PedirDecimal(scanner, "Ingresa la cantidad de Dinero:");
				if(cantidadIngresada <= 10000)
				{
					saldoActual += cantidadIngresada;
					System.out.println("Saldo Actual: " + saldoActual);	
					historial.put(ingresosClave, historial.get(ingresosClave) + 1);
					
				}else {
					System.out.println("Ingreso excede cantidad limite. Operación invalida");
				}
				
				break;
			case '3':
				cantidadIngresada = PedirDecimal(scanner, "Ingresa la cantidad a retirar: ");
				if (cantidadIngresada <= saldoActual) {
					saldoActual -= cantidadIngresada;
					System.out.println("Has retirado " + cantidadIngresada);
					historial.put(retirosClave, historial.get(retirosClave) + 1);
				}else {
					System.out.println("Saldo insuficiente");
				}
				break;
			case '4':
				System.out.println("Tus Movimientos:");
				System.out.println("Cantidad de Ingresos que has hecho: " + historial.get(ingresosClave));
				System.out.println("Cantidad de Retiros que has hecho: " + historial.get(retirosClave));
				break;
			case '5':
				char[] opcionesValidas = {'1', '2', '3'};
				System.out.println("Conversiones disponibles :");
				System.out.println("· MXN <-> USD (1)");
				System.out.println("· MXN <-> EUR (2)");
				System.out.println("· USD <-> EUR (3)");
				char tOpcion = PedirOpcion(scanner, "Ingrese opción: ", opcionesValidas);
				switch(tOpcion){
				case '1':
					BigDecimal pesosMxnIn = BigDecimal.valueOf(PedirDecimal(scanner, "Ingresa la cantidad de pesos MXN: "));
					BigDecimal dolares = pesosMxnIn.multiply(BigDecimal.valueOf(0.05));
					System.out.println("Cantidad de Dolares: " + dolares);
					break;
				case '2':
					pesosMxnIn = BigDecimal.valueOf(PedirDecimal(scanner, "Ingresa la cantidad de pesos MXN: "));
					BigDecimal euros = pesosMxnIn.multiply(BigDecimal.valueOf(0.05));
					System.out.println("Cantidad de Euros: " + euros);
					break;
				case '3':
					pesosMxnIn = BigDecimal.valueOf(PedirDecimal(scanner, "Ingresa la cantidad de dolares USD: "));
					euros = pesosMxnIn.multiply(BigDecimal.valueOf(0.92));
					System.out.println("Cantidad de Euros: " + euros);
					break;
				}
				break;
			default:
				break;
			}
			System.out.println();
		}while(opcion != '0');
	}
}