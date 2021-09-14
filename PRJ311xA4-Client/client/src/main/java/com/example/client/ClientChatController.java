package com.example.client;

import business.Client;
import business.ClientThread;
import business.Server;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientChatController {
    @FXML
    private TextArea txtContent;
    @FXML
    private TextField txtUsername, txtHostIP, txtPort, txtMessage;
    @FXML
    private Button btnConnect;

    ClientThread clientThread = null;

    @FXML
    public void connectToServer() {
        try {
            Client c = new Client(txtUsername.getText(), "");
            Server server = new Server(txtHostIP.getText(), Integer.valueOf(txtPort.getText()));
            clientThread = new ClientThread(server, txtContent);
            Thread t = new Thread(clientThread);
            t.start();
            clientThread.send(":" + c.getUsername());
            txtContent.appendText("Connected to server");
            btnConnect.setDisable(true);

            System.out.println("Client " + c.getUsername() + " is connected");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sendMsgToServer() {
        try {
            clientThread.send(txtMessage.getText());
            // clear chat
            txtMessage.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}