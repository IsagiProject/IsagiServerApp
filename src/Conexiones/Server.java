package Conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class Server {

    static final int PUERTO = 5000;
    public static final String END_WORD = "END";
    public static final String CSV_NAME = "log.csv";

    static ArrayList<ClienteCustom> clientes = new ArrayList<ClienteCustom>();

    public Server() {

        System.setProperty("javax.net.ssl.keyStore", "certificados/almacenssl");
        System.setProperty("javax.net.ssl.keyStorePassword", "1234567");

        try {
            // Se crea el socket del servidor
            SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            try (SSLServerSocket server = (SSLServerSocket) sfact.createServerSocket(PUERTO);) {
                System.out.println("Servidor iniciado en el puerto " + PUERTO);

                // Acepta conexiones de clientes
                while (true) {
                    SSLSocket cliente = (SSLSocket) server.accept();

                    // Obtenemos el input para recibir el nombre de usuario como primer mensaje.
                    // No se cierra el in porque no se puede reabrir
                    DataInputStream in = new DataInputStream(cliente.getInputStream());
                    String user = in.readUTF();
                    System.out.println(user);
                    // if (!userAuthorized(user)) {
                    // System.out.println("Cliente cerrado");
                    // DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                    // // create new thread
                    // new Thread(new Runnable() {
                    // @Override
                    // public void run() {
                    // try {
                    // Thread.sleep(500);
                    // out.writeUTF("Usuario no autorizado");
                    // cliente.close();
                    // } catch (Exception e) {
                    // e.printStackTrace();
                    // }
                    // }
                    // }).start();
                    // continue;
                    // }
                    Server.logConnection(user);

                    // Creamos el cliente y lo guardamos en la lista
                    ClienteCustom cl = new ClienteCustom(user, cliente);
                    clientes.add(cl);
                    System.out.println("Cliente conectado desde " + cliente.getInetAddress() + ":" + cliente.getPort());

                    // Comienza el hilo que se encarga de recibir los mensajes del cliente
                    ServerThread hilo = new ServerThread(cl);
                    hilo.start();
                }
            }

        } catch (Exception e) {
        }
    }

    /**
     * Registra la conexión de un usuario
     * 
     * @param user
     */
    public static void logConnection(String user) {
        try {
            FileWriter fw = new FileWriter(CSV_NAME, true);
            fw.append(user + "," + LocalDateTime.now() + ",C\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error al escribir en el log");
        }
    }

    /**
     * Registra la desconexión de un usuario
     * 
     * @param user
     */
    public static synchronized void logDisconnection(String user) {
        try {
            FileWriter fw = new FileWriter(CSV_NAME, true);
            fw.append(user + "," + LocalDateTime.now() + ",D\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error al escribir en el log");
        }
    }

    /**
     * Metodo que envia un mensaje a todos los clientes conectados (excepto al que
     * lo envia)
     * 
     * @param sender
     * @param mensaje
     */
    public static void sendToAll(ClienteCustom sender, String mensaje) {
        ArrayList<ClienteCustom> clientsToRemove = new ArrayList<ClienteCustom>();
        for (ClienteCustom cliente : clientes) {

            if (cliente.getSocket().isClosed()) {
                clientsToRemove.add(cliente);
                continue;
            }
            if (cliente.equals(sender)) {
                continue;
            }

            // Obtemos el output del cliente y enviamos el mensaje
            try {
                DataOutputStream out = new DataOutputStream(cliente.getSocket().getOutputStream());
                out.writeUTF(sender.getUser() + "\n" + mensaje);
            } catch (Exception e) {

            }
        }

        // Eliminamos los clientes que se han desconectado
        for (ClienteCustom cliente : clientsToRemove) {
            clientes.remove(cliente);
            logDisconnection(cliente.getUser());
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}

class ServerThread extends Thread {

    private ClienteCustom cliente;

    public ServerThread(ClienteCustom cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(cliente.getSocket().getInputStream());
            // Leemos infinitamente los mensajes del cliente
            // hasta que envie la palabra para finalizar
            while (true) {
                String mensaje = in.readUTF();
                System.out.println("Mensaje recibido: " + mensaje);
                if (mensaje.equals(Server.END_WORD)) {
                    cliente.getSocket().close();
                    System.out.println("Cerrado cliente: " + cliente.getSocket().getPort());
                    Server.clientes.remove(cliente);
                    Server.logDisconnection(cliente.getUser());
                    break;
                }

                // Enviamos a todos
                Server.sendToAll(cliente, mensaje);
            }
        } catch (Exception e) {
        }
    }

}
