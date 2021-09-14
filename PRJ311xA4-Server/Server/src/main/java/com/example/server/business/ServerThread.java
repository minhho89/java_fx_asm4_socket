package com.example.server.business;

import com.example.server.Controller.ServerBoxController;
import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerThread implements Runnable, Serializable {

    public static HashMap<String, ClientHandler> clientHandlerMap = new HashMap<>();

    private ServerSocket serverSocket;

    public ServerThread(Server chatServer) {
        try {
            this.serverSocket = new ServerSocket(chatServer.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while(true) {
                // output clients to listview
                Socket socket = this.serverSocket.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());

                // Get first message from client and extract username from it
                String msg = dis.readUTF();
                String username = msg.substring(msg.indexOf(":") + 1);

                // output list of users to listview
                Client client = new Client(username, null, socket);

                // Modify UI components, avoid "Not on FX application thread" exception when multiple clients connect to server
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ServerBoxController.clients.add(client);
                        clientHandlerMap.put(username, new ClientHandler(socket, client));
                    }
                });
                }
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
