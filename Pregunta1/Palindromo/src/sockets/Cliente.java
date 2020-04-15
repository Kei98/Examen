package sockets;

import java.net.*;
import java.io.*;

/**
 *
 * @author kei98
 */


public class Cliente extends Conexion {
   
    private BufferedReader input;
    private DataInputStream in;
    private DataOutputStream out;

    public Cliente() throws IOException {
        super("cliente");
        
        // Establece la conexión con el servidor
        try {
            System.out.println("Conectado");

            // Lee los mensajes digitados por el usuario
            input = new BufferedReader(new InputStreamReader(System.in));

            // Lee los mensajes del servidor
            in = new DataInputStream(
                    new BufferedInputStream(socketCliente.getInputStream()));
            
            // Envía los mensajes al servidor
            out = new DataOutputStream(socketCliente.getOutputStream());
            
            String d = in.readUTF();
            System.out.println("Servidor: " + d);
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }

        // Variable para almacenar el texto temporalmente
        String line = "";
        String s = "";

        // Lee los mensajes de la terminal. 
        while (!s.equalsIgnoreCase("Exit")) {
            try {
                line = input.readLine();
                s = line;
                out.writeUTF(line);
                line = in.readUTF();
                System.out.println("Servidor: " + line);
            } catch (IOException i) {
                System.out.println(i);
            }
        }
        out.writeUTF(line);
        // Cerrando conexiones
        try {
            in.close();
            input.close();
            out.close();
            socketCliente.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) throws IOException {
        Cliente client = new Cliente();
    }
}
