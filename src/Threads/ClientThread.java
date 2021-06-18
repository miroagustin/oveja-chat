package Threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Set;

public class ClientThread extends Thread {
	private Socket s;
	private Set<DataOutputStream> clientes;
	DataInputStream inputStream;

	public ClientThread(Socket s, Set<DataOutputStream> listaClientes) {
		this.s = s;
		this.clientes = listaClientes;
	}

	public void run() {
		try {
			inputStream = new DataInputStream(s.getInputStream());
			while (true) {
				String str = (String) inputStream.readUTF();
				for (DataOutputStream outCliente : clientes) {
					outCliente.writeUTF(str);
					outCliente.flush();
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
