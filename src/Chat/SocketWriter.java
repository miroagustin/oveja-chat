package Chat;

import java.io.DataOutputStream;
import java.io.IOException;

public class SocketWriter {

	private DataOutputStream outStream;

	public SocketWriter() {
		try {
			outStream = new DataOutputStream(SocketManager.getInstance().getOutputStream());

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void write(String text) {
		try {
			if(text != "") {
				outStream.writeUTF(text);
				outStream.flush();
			}
		} catch (Exception e) {
			System.err.println(e);
		}

	}
}
