package Conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static final String END_WORD = "END";

    public Cliente() {
        try {
            Socket socket = new Socket("localhost", 5000);
            ClienteThread hilo = new ClienteThread(socket);
            hilo.start();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("");
            Scanner sc = new Scanner(System.in);
            while (true) {
                String mensaje = sc.nextLine();
                out.writeUTF(mensaje);
                if (mensaje.equals(END_WORD)) {
                    break;
                }
            }
            sc.close();
            out.close();
            socket.close();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        new Cliente();
    }
}

class ClienteThread extends Thread {

    private Socket socket;

    public ClienteThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            while (true) {
                String mensaje = in.readUTF();
                if (mensaje.equals(Cliente.END_WORD)) {
                    socket.close();
                    System.out.println("Cerrando cliente " + socket.getPort());
                    break;
                }
                System.out.println(mensaje);
            }
            in.close();
        } catch (Exception e) {

        }
    }
}