package Conexiones;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Server  extends Thread{

    static final int PUERTO = 5000;
    public static final String END_WORD = "END";
    public static final String CSV_NAME = "log.csv";

    static ArrayList<ClienteCustom> clientes = new ArrayList<ClienteCustom>();
    
    public void run() {
    	try (ServerSocket server = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            // Acepta conexiones de clientes
            while (true) {
                Socket cliente = server.accept();

                // Obtenemos el input para recibir el nombre de usuario como primer mensaje.
                // No se cierra el in porque no se puede reabrir
                DataInputStream in = new DataInputStream(cliente.getInputStream());
                String user = in.readUTF();

                Server.logConnection(user);

                // Creamos el cliente y lo guardamos en la lista
                ClienteCustom cl = new ClienteCustom(user, cliente);
                clientes.add(cl);
                System.out.println("Cliente conectado desde " + cliente.getInetAddress() + ":" + cliente.getPort());

                // Comienza el hilo que se encarga de recibir los mensajes del cliente
                ServerThread hilo = new ServerThread(cl);
                hilo.start();
            }
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public Server() {
        try {
            // Se crea el socket del servidor
            try (ServerSocket server = new ServerSocket(PUERTO)) {
                System.out.println("Servidor iniciado en el puerto " + PUERTO);

                // Acepta conexiones de clientes
                while (true) {
                    Socket cliente = server.accept();

                    // Obtenemos el input para recibir el nombre de usuario como primer mensaje.
                    // No se cierra el in porque no se puede reabrir
                    DataInputStream in = new DataInputStream(cliente.getInputStream());
                    String user = in.readUTF();

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
    public static synchronized void logConnection(String user) {
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

    public static void readCSV() {
        String row;
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(CSV_NAME));
            while ((row = csvReader.readLine()) != null) {
                // 0: user 1: LocalDateTime 2: C/D
                String[] data = row.split(",");
                // do something with the data
            }
            csvReader.close();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
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

//    public static void main(String[] args) {
//        new Server();
//    }
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