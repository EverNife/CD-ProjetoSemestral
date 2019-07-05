package br.com.finalcraft.unesp.cd.server.handlers.rmi;

import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessageExecutor;
import br.com.finalcraft.unesp.cd.server.handlers.tcp.ServerSocketThread;
import br.com.finalcraft.unesp.cd.server.javafx.controller.ServerController;

import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class ServerSideRMI {

    private static RMIMessageExecutor rmiMessageExecutor;

    public static void initialize(){
        System.out.println("Iniciando SERVER_SIDE (RMI)");
        try {
            rmiMessageExecutor = (RMIMessageExecutor) Naming.lookup( "rmi://localhost:1099/SlaveRMIExecutor" );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static RMIMessageExecutor getExecutor(){
        System.out.println("Getting RMI Executor...");
        return rmiMessageExecutor;
    }

}
