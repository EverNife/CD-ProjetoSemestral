package br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi;

import java.io.Serializable;

public interface RMIMessageExecutable extends Serializable {

    public abstract RMIMessage execute() throws Exception;
}
