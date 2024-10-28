package cajeroautomatico;

import java.util.List;

public interface Metodos {
	
	public boolean altaCliente(Cliente cliente);
	
	public boolean depositoEfec(String numCuenta, double cantidad);
	
	public boolean retiroEfect(String numCuenta, double cantidadRet);
	
}

