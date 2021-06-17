package Chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;

public class SocketManager {

	private static SocketManager INSTANCE = null;

	private String host = "localhost";
    private int port = 4220;
    private Socket socket;

    public Socket open() throws IOException {
        if (socket != null) {
            close();
        }
        socket = new Socket(host, port);
        return socket;
    }

    public void close() throws IOException {
        if (socket == null) {
            return;
        }
        socket.close();
    }
    public static SocketManager getInstance() {
    	if(INSTANCE == null)
    		INSTANCE = new SocketManager();
    	return INSTANCE;
    }

    public boolean isOpen() {
        return socket != null
            && socket.isConnected()
            && !socket.isClosed()
            && !socket.isInputShutdown()
            && !socket.isOutputShutdown();
    }

    public InputStream getInputStream() throws IOException {
        Objects.requireNonNull(socket, "Socket is not open");
        return socket.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        Objects.requireNonNull(socket, "Socket is not open");
        return socket.getOutputStream();
    }

}
