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
		Implementacion imp = new Implementacion(); // Instancia de clase en donde se desarrollo la logica de los métodos

		do {
			System.out.println(
					"\n =================$======================= BIENVENIDO A SECUREBANK =================$======================= \n");
			System.out.println("(1) --- Alta cliente");
			System.out.println("(2) --- Consultar saldo");
			System.out.println("(3) --- Depósito en efectivo");
			System.out.println("(4) --- Retiro en efectivo");
			System.out.println("(5) --- Salir \n");
			System.out.println(
					" =================$========================================================$================================= \n");
			System.out.println("Ingrese una opción:");

			try { // Validación de entrada de opción
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

						System.out.println("Ingrese el teléfono:");
						telefono = lectura.nextLine();

						System.out.println("Ingrese el número de cuenta: ");
						numCuenta = lectura.nextLine();

						System.out.println("Ingrese el PIN ");
						pin = lectura.nextInt();

						cliente = new Cliente(id, nombre, apellidos, telefono, numCuenta, saldo, pin);
						if (!imp.altaCliente(cliente)) {
							System.out.println("Los datos se han guardado correctamente!");
						} else {
							System.out.println(
									"ID o Número de cuenta en uso. Recuerda que el ID y el número de cuenta son valores únicos.");
						}

					} catch (Exception e) {
						System.out.println("Por favor ingresa de manera correcta los datos.");
					}
					break;

				case 2:
					// Caso para consultar saldo
					System.out.println("Por favor, ingrese el número de cuenta para consultar su saldo:");
					lectura.nextLine(); // Limpiar
					numCuenta = lectura.nextLine();
					// Búsqueda
					cliente = imp.buscarNumCuenta(numCuenta);
					// Validar que en la busqueda sea diferente a nulo para verificar su saldo
					if (cliente != null) {
						System.out.println("El saldo del cliente " + cliente.getNombre() + " " + cliente.getApellidos()
								+ " es: $" + cliente.getSaldo());
					} else {
						System.out.println("No se encontró ningún cliente con ese número de cuenta");
					}
					break;

				case 3:
					// Caso para depósito
					System.out.println("Por favor, ingrese el número de cuenta en la que desea realizar el depósito:");
					lectura.nextLine(); // Limpiar el buffer
					numCuenta = lectura.nextLine();

					// Validar si el número de cuenta existe
					Cliente clienteEnc = imp.buscarNumCuenta(numCuenta);

					if (cliente != null) { // Si la cuenta existe
						System.out.println("Ingresa el monto que desea depositar: $");
						try {
							double cantidadDep = lectura.nextDouble();

							// Validación del monto a depositar
							if (cantidadDep > 0 && cantidadDep <= 10000) {
								if (imp.depositoEfec(numCuenta, cantidadDep)) {
									System.out.println("Depósito realizado con éxito. Nuevo saldo: $"
											+ imp.buscarNumCuenta(numCuenta).getSaldo());
								} else {
									System.out.println("Error al realizar el depósito. La cuenta no existe.");
								}
							} else {
								System.out.println(
										"Monto no válido. Por favor, ingrese una cantidad mayor a $0 y hasta un máximo de $10,000.");
							}
						} catch (Exception e) {
							System.out
									.println("Monto inválido. No se permiten letras, por favor ingrese solo números.");
							lectura.next(); // Limpiar
						}
					} else {
						System.out.println("Error. El número de cuenta no existe.");
					}
					break;

				case 4:
					// Caso para retiro
					System.out.println("Por favor, ingrese el número de cuenta de la cual desea retirar fondos:");
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
											.println("Retiro realizado con éxito. Nuevo saldo: $" + cliente.getSaldo());
								}
							} else {
								System.out.println(
										"Error. Ingresa un monto mayor a $0 y no mayor a tu saldo disponible: $" + clienteEn.getSaldo());
							}
						} catch (Exception e) {
							System.out.println("Error: La cantidad ingresada no es válida. No se permiten letras.");
							lectura.next(); // Limpiar
						}
					} else {
						System.out.println("Error. El número de cuenta no existe.");
					}
					break;

				case 5:
					System.out.println("Gracias por utilizar SecureBank. ¡Vuelva pronto!");
					break;

				default:
					System.out.println("Opción no válida. Por favor, selecciona una opción del menú.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("\n Error: Ingresa un número válido.");
				lectura.next(); // Limpiar
			}

		} while (opc != 5);

	}
}
