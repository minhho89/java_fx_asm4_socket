package com.example.server.business;

import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Client client;
    private final Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private TextArea txtContent;

    public ClientHandler(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;
    }


    @Override
    public void run() {
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String messageFromClient = dis.readUTF();
                txtContent.appendText("\n" + client.getUsername() + ":" + messageFromClient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String message) throws Exception {
        // send a message to client
        dos.writeUTF(message);
        txtContent.appendText("\nMe:" + message);
    }

    public void setTxtContent(TextArea txtContent) {
        this.txtContent = txtContent;
    }
}
