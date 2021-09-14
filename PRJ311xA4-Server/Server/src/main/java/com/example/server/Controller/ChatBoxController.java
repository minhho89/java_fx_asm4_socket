package com.example.server.Controller;

import com.example.server.business.ClientHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatBoxController {

    private ClientHandler clientHandler;

    @FXML
    private TextArea txtContent;
    @FXML
    private TextField txtMessage;
    @FXML
    private Button btnSend;

    public void initialize() {
        btnSend.setDisable(true);
    }

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        this.clientHandler.setTxtContent(txtContent);
        new Thread(this.clientHandler).start();
        btnSend.setDisable(false);
    }

    public void btnSendActionPerformed(ActionEvent event) {
        try {
            String message = txtMessage.getText();
            clientHandler.send(message);
            // Clear chat box
            txtMessage.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
