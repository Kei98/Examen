package sockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author kei98
 */

public class Conexion {

    protected final int PUERTO = 5000; //Puerto para la conexión
    protected final String HOST = "192.168.100.13"; //Host para la conexión

    protected String mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
    protected ServerSocket socketServer; //Socket del servidor
    protected Socket socketCliente; //Socket del cliente
    protected DataOutputStream salidaServidor, salidaCliente; //Flujo de datos de salida

    //Constructor
    public Conexion(String tipo) throws IOException {
        if (tipo.equalsIgnoreCase("servidor")) {
            socketServer = new ServerSocket(PUERTO);//Se crea el socket para el servidor en puerto seleccionado
//            socketCliente = new Socket(); //Socket para el cliente
        } else {
            socketCliente = new Socket(HOST, PUERTO); //Socket para el cliente en localhost en puerto seleccionado
        }
    }
}

