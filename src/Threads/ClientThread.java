package Threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Set;

import Chat.Conexion;

public class ClientThread extends ThreadNotificador {
	private Conexion conexion;
	private Set<DataOutputStream> clientes;
	DataInputStream inputStream;

	public ClientThread(Conexion conexion, Set<DataOutputStream> listaClientes) {
		this.conexion = conexion;
		this.clientes = listaClientes;
	}
	@Override
	public void doRun() {
		try {
			inputStream = conexion.getInput();
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
	public Conexion getConexion() {
		return conexion;
	}
}
