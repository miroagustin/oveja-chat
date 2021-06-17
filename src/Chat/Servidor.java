package Chat;

import java.util.List;
import java.util.Set;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import Threads.ClientThread;

public class Servidor {
	Set<DataInputStream> listaInputs = new HashSet<DataInputStream>();
	public Servidor() {
		ServerSocket ss;
		Set<DataOutputStream> listaClientes = new HashSet<DataOutputStream>();
		
		try {
			ss = new ServerSocket(4220);
			System.out.println("El Servidor esta escuchando");
			while (true) {
				Socket s = ss.accept();
				listaClientes.add(new DataOutputStream(s.getOutputStream()));
				ClientThread client = new ClientThread(s,listaClientes);
				client.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void main(String[] args) {
		new Servidor();
	}

}
