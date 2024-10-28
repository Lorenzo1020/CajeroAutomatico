package cajeroautomatico;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// Declaramos variables
		Scanner lectura = new Scanner(System.in); // Crea una instancia de Scanner para leer la entrada de datos
		int id;
		String nombre;
		String apellidos;
		String telefono;
		String numCuenta;
		double saldo = 0;
		int pin;

		Cliente cliente = new Cliente(); // Instancia de clase para poder acceder a todo lo que contenga esa clase
		int opc = 0; // Inicializa la variable opc
		Implementacion imp = new Implementacion(); // Instancia de clase en donde se desarrollo la logica de los m�todos

		do {
			System.out.println(
					"\n =================$======================= BIENVENIDO A SECUREBANK =================$======================= \n");
			System.out.println("(1) --- Alta cliente");
			System.out.println("(2) --- Consultar saldo");
			System.out.println("(3) --- Dep�sito en efectivo");
			System.out.println("(4) --- Retiro en efectivo");
			System.out.println("(5) --- Salir \n");
			System.out.println(
					" =================$========================================================$================================= \n");
			System.out.println("Ingrese una opci�n:");

			try { // Validaci�n de entrada de opci�n
				opc = lectura.nextInt();

				switch (opc) {
				case 1:
					// Caso para dae de alta a un cliente
					try {
						System.out.println("Ingrese el ID: ");
						id = lectura.nextInt();
						lectura.nextLine(); // Limpiar

						System.out.println("Ingrese el nombre: ");
						nombre = lectura.nextLine();

						System.out.println("Ingrese los apellidos: ");
						apellidos = lectura.nextLine();

						System.out.println("Ingrese el tel�fono:");
						telefono = lectura.nextLine();

						System.out.println("Ingrese el n�mero de cuenta: ");
						numCuenta = lectura.nextLine();

						System.out.println("Ingrese el PIN ");
						pin = lectura.nextInt();

						cliente = new Cliente(id, nombre, apellidos, telefono, numCuenta, saldo, pin);
						if (!imp.altaCliente(cliente)) {
							System.out.println("Los datos se han guardado correctamente!");
						} else {
							System.out.println(
									"ID o N�mero de cuenta en uso. Recuerda que el ID y el n�mero de cuenta son valores �nicos.");
						}

					} catch (Exception e) {
						System.out.println("Por favor ingresa de manera correcta los datos.");
					}
					break;

				case 2:
					// Caso para consultar saldo
					System.out.println("Por favor, ingrese el n�mero de cuenta para consultar su saldo:");
					lectura.nextLine(); // Limpiar
					numCuenta = lectura.nextLine();
					// B�squeda
					cliente = imp.buscarNumCuenta(numCuenta);
					// Validar que en la busqueda sea diferente a nulo para verificar su saldo
					if (cliente != null) {
						System.out.println("El saldo del cliente " + cliente.getNombre() + " " + cliente.getApellidos()
								+ " es: $" + cliente.getSaldo());
					} else {
						System.out.println("No se encontr� ning�n cliente con ese n�mero de cuenta");
					}
					break;

				case 3:
					// Caso para dep�sito
					System.out.println("Por favor, ingrese el n�mero de cuenta en la que desea realizar el dep�sito:");
					lectura.nextLine(); // Limpiar el buffer
					numCuenta = lectura.nextLine();

					// Validar si el n�mero de cuenta existe
					Cliente clienteEnc = imp.buscarNumCuenta(numCuenta);

					if (cliente != null) { // Si la cuenta existe
						System.out.println("Ingresa el monto que desea depositar: $");
						try {
							double cantidadDep = lectura.nextDouble();

							// Validaci�n del monto a depositar
							if (cantidadDep > 0 && cantidadDep <= 10000) {
								if (imp.depositoEfec(numCuenta, cantidadDep)) {
									System.out.println("Dep�sito realizado con �xito. Nuevo saldo: $"
											+ imp.buscarNumCuenta(numCuenta).getSaldo());
								} else {
									System.out.println("Error al realizar el dep�sito. La cuenta no existe.");
								}
							} else {
								System.out.println(
										"Monto no v�lido. Por favor, ingrese una cantidad mayor a $0 y hasta un m�ximo de $10,000.");
							}
						} catch (Exception e) {
							System.out
									.println("Monto inv�lido. No se permiten letras, por favor ingrese solo n�meros.");
							lectura.next(); // Limpiar
						}
					} else {
						System.out.println("Error. El n�mero de cuenta no existe.");
					}
					break;

				case 4:
					// Caso para retiro
					System.out.println("Por favor, ingrese el n�mero de cuenta de la cual desea retirar fondos:");
					lectura.nextLine(); // Limpiar el buffer
					numCuenta = lectura.nextLine();

					// Validar que la cuenta existe
					Cliente clienteEn = imp.buscarNumCuenta(numCuenta);
					if (cliente != null) {
						System.out.println("Por favor, ingrese el monto que desea retirar:");

						try {
							double cantidadRet = lectura.nextDouble();

							// Validar que la cantidad sea mayor que 0 y menor o igual al saldo disponible
							if (cantidadRet > 0 && cantidadRet <= cliente.getSaldo()) {
								if (imp.retiroEfect(numCuenta, cantidadRet)) {
									System.out
											.println("Retiro realizado con �xito. Nuevo saldo: $" + cliente.getSaldo());
								}
							} else {
								System.out.println(
										"Error. Ingresa un monto mayor a $0 y no mayor a tu saldo disponible: $" + clienteEn.getSaldo());
							}
						} catch (Exception e) {
							System.out.println("Error: La cantidad ingresada no es v�lida. No se permiten letras.");
							lectura.next(); // Limpiar
						}
					} else {
						System.out.println("Error. El n�mero de cuenta no existe.");
					}
					break;

				case 5:
					System.out.println("Gracias por utilizar SecureBank. �Vuelva pronto!");
					break;

				default:
					System.out.println("Opci�n no v�lida. Por favor, selecciona una opci�n del men�.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("\n Error: Ingresa un n�mero v�lido.");
				lectura.next(); // Limpiar
			}

		} while (opc != 5);

	}
}
