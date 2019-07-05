package br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi;

import java.io.Serializable;
import java.rmi.Remote;

public abstract class RMIMessage implements Remote, Serializable {

    public RMIMessageDirection direction;
    public RMIMessageDirection getDirection() {
        return direction;
    }

    public RMIMessage setDirection(RMIMessageDirection direction){
        this.direction = direction;
        return this;
    }

}
