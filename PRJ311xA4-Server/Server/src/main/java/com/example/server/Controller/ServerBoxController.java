package com.example.server.Controller;

import com.example.server.business.Client;
import com.example.server.business.ClientHandler;
import com.example.server.business.ServerThread;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ServerBoxController{

    public static ObservableList<Client> clients = FXCollections.observableArrayList();

    @FXML
    private ListView<Client> listview_clients;

    public void initialize() {
        listview_clients.setItems(clients);
    }

    @FXML
    public void onClientListViewClicked(MouseEvent event) {
        try {
            if (event.getClickCount() == 2) {

                String clientName = listview_clients.getSelectionModel().getSelectedItem().getUsername();

                // Show Chatbox when a client name is selected
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatBox.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Chat with " + clientName);
                stage.show();

                // Assign a ClientHandler to Chatbox
                ClientHandler clientHandler = ServerThread.clientHandlerMap.get(clientName);
                ChatBoxController controller = loader.getController();
                controller.setClientHandler(clientHandler);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
