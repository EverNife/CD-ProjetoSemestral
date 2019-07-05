package br.com.finalcraft.unesp.cd.slave.rmi;

import br.com.finalcraft.unesp.cd.common.SmartLogger;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessage;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessageExecutable;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessageExecutor;
import br.com.finalcraft.unesp.cd.slave.javafx.controller.SlaveController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SlaveRMIExecutor extends UnicastRemoteObject implements RMIMessageExecutor {

    public SlaveRMIExecutor() throws RemoteException {
        super();
    }

    @Override
    public RMIMessage runExecutable(RMIMessageExecutable rmiMessageExecutable) {
        SmartLogger.info("Starting to solve RMIMessageExecutable [" + rmiMessageExecutable.getClass().getSimpleName() + "]... with data " + rmiMessageExecutable.toString());
        long startTime = System.currentTimeMillis();
        SlaveController.setGifOn();
        RMIMessage result = null;
        try {
            result = rmiMessageExecutable.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        SlaveController.setGifOff();
        SmartLogger.info("It took: " + (System.currentTimeMillis() - startTime) + " millis to solve.");
        return result;
    }
}
