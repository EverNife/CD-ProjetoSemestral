package br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIMessageExecutor extends Remote, Serializable {
    public abstract RMIMessage runExecutable(RMIMessageExecutable rmiMessage) throws RemoteException;
}
