package Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Conexion {

	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;

	public Conexion(Socket s, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
		this.socket = s;
		this.input = dataInputStream;
		this.output = dataOutputStream;
	}

	public DataOutputStream getOutput() {
		return output;
	}

	public DataInputStream getInput() {
		// TODO Auto-generated method stub
		return input;
	}

}
