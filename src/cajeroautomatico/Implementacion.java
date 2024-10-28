package cajeroautomatico;

import java.util.ArrayList;
import java.util.List;

public class Implementacion implements Metodos {

	// Declaracion de lista
	List<Cliente> listaClientes = new ArrayList<Cliente>();

	// Validar que el ID o el numeroCuenta no se repitan
	@Override
	public boolean altaCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		boolean bandera = false;
		for (Cliente c : listaClientes) {
			if (c.getId() == cliente.getId() || c.getNumCuenta().equals(cliente.getNumCuenta())) {
				bandera = true;
				break;
			}

		}
		if (bandera == false)
			listaClientes.add(cliente);

		return bandera;
	}

	public Cliente buscarNumCuenta(String numCuenta) {
		// TODO Auto-generated method stub
		// Ciclo
		// Condicion
		// Recorremos la lista y en la condicion verificamos con comparacion que exista
		// ese numero de
		// cuenta
		for (int i = 0; i < listaClientes.size(); i++) {
			if (numCuenta.equals(listaClientes.get(i).getNumCuenta())) {

				return listaClientes.get(i);

			}
		}
		return null;
	}

	@Override
	public boolean depositoEfec(String numCuenta, double cantidad) {
		// TODO Auto-generated method stub
		// Validamos que la cantidad no sea menor a 0 o mayor a 10000
		if (cantidad <= 0 || cantidad > 10000) {
			System.out.println("Error: La cantidad a depositar debe ser mayor que 0 y menor o igual a 10,000.");
			return false; // Retorna falso si la validación falla
		}

		Cliente cliente = buscarNumCuenta(numCuenta); // Buscar cliente por número de cuenta
		if (cliente != null) { // Si el cliente existe hacemos la operacion
			cliente.setSaldo(cliente.getSaldo() + cantidad);
			return true;

		}
		return false;
	}

	@Override
	public boolean retiroEfect(String numCuenta, double cantidadRet) {
		// Validar que la cantidad a retirar sea mayor a 0 y menor o igual a 10,000
		if (cantidadRet <= 0 || cantidadRet > 10000) {
			System.out.println("Error: La cantidad máxima a retirar es $10,000 y debe ser mayor que $0.");
			return false;
		}

		// Buscar cliente por número de cuenta
		Cliente cliente = buscarNumCuenta(numCuenta);
		if (cliente != null) {
			// Validar si el cliente tiene suficiente saldo
			if (cantidadRet > cliente.getSaldo()) {
				System.out.println("Lo siento, no tienes suficiente saldo para realizar este retiro.");
				return false; // No se puede realizar el retiro
			} else {
				// Realizar el retiro
				cliente.setSaldo(cliente.getSaldo() - cantidadRet);
				return true; // Retiro exitoso
			}
		} else {
			System.out.println("Error. El número de cuenta no existe.");
		}

		return false; // Si no se cumple ninguna de las condiciones anteriores
	}

}
