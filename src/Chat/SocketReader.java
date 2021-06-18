package Chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

public class SocketReader extends SwingWorker<Void, String> {

    private List<ActionListener> actionListeners;

    public SocketReader() {
        actionListeners = new ArrayList<>(25);
    }

    public void addActionListener(ActionListener listener) {
        actionListeners.add(listener);
    }

    public void removeActionListener(ActionListener listener) {
        actionListeners.remove(listener);
    }

    @Override
    protected Void doInBackground() throws Exception {

        try (DataInputStream in = new DataInputStream(SocketManager.getInstance().getInputStream())) {

            String serverInput = null;
            do {
                // HANDLE INPUT PART HERE
                serverInput = in.readUTF();

                if (serverInput != null) {
                    System.out.println("Read " + serverInput);
                    publish(serverInput);
                }

            } while (true);
        }
    }

    @Override
    protected void process(List<String> chunks) {
        for (String text : chunks) {
            ActionEvent evt = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, text);
            for (ActionListener listener : actionListeners) {
                listener.actionPerformed(evt);
            }
        }
    }
}