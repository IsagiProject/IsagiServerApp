package Conexiones;

import java.net.Socket;

/**
 * Clase que representa a un cliente conectado al servidor.
 */
public class ClienteCustom {
    String user;
    Socket socket;

    public ClienteCustom(String user, Socket socket) {
        this.user = user;
        this.socket = socket;
    }

    public String getUser() {
        return user;
    }

    public Socket getSocket() {
        return socket;
    }
}