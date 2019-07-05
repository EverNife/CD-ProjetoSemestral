package br.com.finalcraft.unesp.cd.client.tcp;

import br.com.finalcraft.unesp.cd.common.clientserver.messages.tcp.TCPMessage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSideTCP extends Thread {

    public static ClientSideTCP clientSide;
    public static void initialize(){
        System.out.println("Iniciando CLIENT_SIDE_TCP");
        clientSide = new ClientSideTCP();
        clientSide.start();
    }

    public static void sendToServer(TCPMessage tcpMessage){
        System.out.println("Sending TCPMessage." + tcpMessage.getClass().getSimpleName() + " to server: " + tcpMessage.toString());
        try {
            clientSide.objectOutputStream.flush();
            clientSide.objectOutputStream.writeObject(tcpMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static TCPMessage readFromServer(){
        try {
            System.out.println("Waiting TCPMessage from server...");
            Object readObject = clientSide.objectInputStream.readObject();
            if (readObject instanceof TCPMessage){
                System.out.println("Received TCPMessage." + readObject.getClass().getSimpleName() + " from Server: " + readObject.toString());
                return (TCPMessage) readObject;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    @Override
    public void run() {
        try {
            // Inicia conexão com o servidor
            Socket serverSocket = new Socket("localhost",12345);
            objectOutputStream = new ObjectOutputStream(serverSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(serverSocket.getInputStream());

            System.out.println("Conectando ao servidor [" + serverSocket.getInetAddress().getHostAddress() + "]");
            System.out.println("\n\n");
        }catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

}
