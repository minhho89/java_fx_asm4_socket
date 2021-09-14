package com.example.server;

import com.example.server.business.Server;
import com.example.server.business.ServerThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public final String SERVER_ADDRESS = "localhost";
    public final int PORT = 1234;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ServerBox.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load(), 320, 240);
        stage.setTitle("Server Application");
        stage.setScene(scene);
        stage.show();

        // Start serverThread
        Server server = new Server(SERVER_ADDRESS, PORT);
        ServerThread serverThread = new ServerThread(server);
        new Thread(serverThread).start();
    }

    public static void main(String[] args) {
        launch();
    }
}