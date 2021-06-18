package Servidor;

import java.util.Set;

import Chat.Conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import Threads.ClientThread;
import Threads.ThreadCompleteListener;

public class Servidor implements Runnable, ThreadCompleteListener {
	int puerto = 4220;
	ServerSocket ss;
	Set<DataOutputStream> listaClientes = new HashSet<DataOutputStream>();
	public static void main(String[] args) {
		new Servidor().run();
	}
	@Override
	public void run() {
		try {
			ss = new ServerSocket(puerto);
			System.out.println("El Servidor esta escuchando en el puerto " + puerto);
			while (true) {
				Socket s = ss.accept();
				Conexion conexion = new Conexion(s, new DataInputStream(s.getInputStream()),new DataOutputStream(s.getOutputStream()));
				listaClientes.add(conexion.getOutput());
				ClientThread client = new ClientThread(conexion,listaClientes);
				client.addListener(this);
				client.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	@Override
	public void notifyOfThreadComplete(Thread thread) {
		ClientThread c = (ClientThread)thread;
		listaClientes.remove(c.getConexion().getOutput());		
	}

}
