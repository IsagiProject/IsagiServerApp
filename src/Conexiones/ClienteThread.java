package Conexiones;

import java.io.DataInputStream;
import java.net.Socket;

import Visual.Chat;

public class ClienteThread extends Thread {
	static int i = 0;
	private Socket socket;
	public static String mensaje;
	Chat chat;

	public ClienteThread(Socket socket, Chat chat) {
		this.socket = socket;
		this.chat = chat;
	}

	@Override
	public void run() {

		try {

			DataInputStream in = new DataInputStream(socket.getInputStream());
			while (true) {

				this.mensaje = in.readUTF();
				if (this.mensaje.equals(Cliente.END_WORD)) {
					socket.close();
					System.out.println("Cerrando cliente " + socket.getPort());
					break;
				}
				System.out.println(this.mensaje);
				this.chat.textPane.setText(this.chat.textPane.getText() + "\n\n" + mensaje);
				i++;
			}
			in.close();
		} catch (Exception e) {
			System.out.println("no readable");
		}

	}
}
