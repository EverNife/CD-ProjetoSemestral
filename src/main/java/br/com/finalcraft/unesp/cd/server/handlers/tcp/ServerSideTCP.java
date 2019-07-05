package br.com.finalcraft.unesp.cd.server.handlers.tcp;

import br.com.finalcraft.unesp.cd.server.javafx.controller.ServerController;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSideTCP extends Thread {

    public static ServerSideTCP serverSide;
    public static List<ServerSocketThread> serverSocketThreadList = new ArrayList<ServerSocketThread>();

    public static void initialize(){
        serverSide = new ServerSideTCP();
        serverSide.start();
        System.out.println("Iniciando SERVER_SIDE (TCP)");
    }

    @Override
    public void run() {
        try {
            // Instancia o ServerSocket ouvindo a porta 12345
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("----------------------------------\n\nServidor ouvindo a porta " + servidor.getLocalPort());

            while (true){
                try {
                    Socket clientConnecting = servidor.accept();
                    ServerSocketThread serverSocketThread = new ServerSocketThread(clientConnecting);
                    serverSocketThread.start();

                    serverSocketThreadList.add(serverSocketThread);
                }catch (Exception e){
                    System.out.println("Erro ao tentar conectar com possivel cliente...");
                    e.printStackTrace();
                }
            }
        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
