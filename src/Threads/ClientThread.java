package Threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Set;

public class ClientThread extends Thread {
	private Socket s;
	private Set<DataOutputStream> clientes;

	public ClientThread(Socket s, Set<DataOutputStream> listaClientes) {
		this.s = s;
		this.clientes = listaClientes;
	}

	public void run() {
		try {
			DataInputStream inputStream = new DataInputStream(s.getInputStream());
			while(true) {
				String str = (String) inputStream.readUTF();
				System.out.println("message= " + str);
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
