package com.example.server.business;

import java.io.Serializable;
import java.net.Socket;

public class Client implements Serializable {
    private String username;
    private String password;
    private Socket socket;

    public String toString() {
        return this.username;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return this.password;
    }

    public void setType(String password) {
        this.password = password;
    }

    public Client() {
    }

    public Client(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Client(String username, String password, Socket socket) {
        this.username = username;
        this.password = password;
        this.socket = socket;
    }
}