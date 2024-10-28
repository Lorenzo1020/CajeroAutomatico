package cajeroautomatico;

public class Cliente {
	private int id;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String numCuenta;
	private double saldo;
	private int pin;

	// Constructor vacío: Permite crear objetos sin inicializar sus atributos de
	// inmediato. Útil cuando se desea establecer valores más adelante
	public Cliente() {
	}

	public Cliente(double saldo) {
		this.saldo = 0.0;
	}

	// Constructor con todos los atributos: Inicializa todos 
	public Cliente(int id, String nombre, String apellidos, String telefono, String numCuenta, double saldo, int pin) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.numCuenta = numCuenta;
		this.saldo = saldo;
		this.pin = pin;
	}

	// Metodo toString: Devuelve una representación en cadena del objeto
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono
				+ ", numCuenta=" + numCuenta + ", saldo=" + saldo + ", pin=" + pin + "\n]";
	}

	// Getters y setters: Permite acceder (get) y modificar (set) los atributos
	// privados del objeto

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

}
