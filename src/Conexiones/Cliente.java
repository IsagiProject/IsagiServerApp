package Conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Visual.Chat;

public class Cliente {
	public static final String END_WORD = "END";
	public Socket socket;
	public DataOutputStream out;
	public ClienteThread hilo;
	public Chat chat;
	public Cliente(Chat chat) {
		this.chat=chat;
		System.setProperty("javax.net.ssl.trustStore", "certificados/usuarioalmacenssl");
		System.setProperty("javax.net.ssl.trustStorePassword", "890123");
		SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try {
			this.socket = (SSLSocket) sfact.createSocket("localhost", 5000);
			this.hilo = new ClienteThread(socket, this.chat);
			this.hilo.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String sendMessage(String mensaje) {
		try {
			
			this.out = new DataOutputStream(this.socket.getOutputStream());
			this.out.writeUTF(mensaje);
			}catch (Exception e) {
				// TODO: handle exception
			}
		return mensaje;

	}
	

	public void close() {
		try {
			this.out.close();
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
 