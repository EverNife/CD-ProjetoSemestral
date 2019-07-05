package br.com.finalcraft.unesp.cd.slave.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class SlaveSideRMI {

    public static SlaveRMIExecutor slaveRMIExecutor;

    public static void initialize(){
        System.out.println("Iniciando SLAVE_SIDE");
        try {
            LocateRegistry.createRegistry(1099);
            // Da uma lida nesse paranaue aqui meu confederado
            // --> https://docs.oracle.com/javase/7/docs/technotes/tools/solaris/rmiregistry.html

            slaveRMIExecutor = new SlaveRMIExecutor();

            Naming.rebind("rmi://localhost:1099/SlaveRMIExecutor", slaveRMIExecutor);
        }
        catch( Exception e ) {
            System.out.println( "Trouble: " + e );
        }
    }
}
