package sockets;

import java.io.*;
import logica.Palindromo;
/**
 *
 * @author kei98
 */

public class Servidor extends Conexion {

    private DataInputStream in;
    //private BufferedReader input;
    private DataOutputStream out;
    private Palindromo pal;
    
    
    public Servidor() throws IOException {
        super("servidor");

        // Inicializa el servidor
        try {
            pal = new Palindromo();
            
            System.out.println("Servidor inicializado");

            System.out.println("Esperando al cliente ...");

            socketCliente = socketServer.accept();
            System.out.println("Cliente aceptado");
            
            
            
            // Recibe los mensajes desde el cliente 
            in = new DataInputStream(
                    new BufferedInputStream(socketCliente.getInputStream()));

            // Lee los mensajes del teclado
            //input = new BufferedReader(new InputStreamReader(System.in));

            // Envía los mensajes al cliente
            out = new DataOutputStream(socketCliente.getOutputStream());
            
            out.writeUTF("Bienvenido. Ingrese una palabra o frase para saber si es Palíndromo o no");
            
            String line = "";
            String s = "";
            
            
            // Lee los mensajes del cliente. El programa finaliza con la palabra exit
            while (!line.equalsIgnoreCase("Exit")) {
                try {
                    line = in.readUTF();
                    System.out.println("Cliente: " + line);
                    pal.setFraseO(line);
                    if(pal.esPalindromo()){
                        s= "Es palíndromo";
                    }else{
                        s= "No es palíndromo";
                    }
                    
                    out.writeUTF(s);
                    //line = input.readLine();

                } catch (IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Cerrando conexiones");
            // Cerrando conexiones
            socketCliente.close();
            socketServer.close();
            in.close();
            out.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }
    

    public static void main(String args[]) throws IOException {
        Servidor server = new Servidor();
    }
}
