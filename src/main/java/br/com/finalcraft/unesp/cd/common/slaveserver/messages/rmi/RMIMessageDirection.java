package br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi;

import java.io.Serializable;

public enum RMIMessageDirection implements Serializable {
    SLAVE_TO_SERVER,
    SERVER_TO_SLAVE;
}
