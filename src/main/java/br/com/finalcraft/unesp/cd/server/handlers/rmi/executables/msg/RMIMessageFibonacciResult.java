package br.com.finalcraft.unesp.cd.server.handlers.rmi.executables.msg;

import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessage;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessageDirection;

import java.math.BigInteger;

public class RMIMessageFibonacciResult extends RMIMessage {

    private final BigInteger theResult;

    public RMIMessageFibonacciResult(BigInteger theResult) {
        this.theResult = theResult;
        this.direction = RMIMessageDirection.SLAVE_TO_SERVER;
    }

    public BigInteger getTheResult() {
        return theResult;
    }

    @Override
    public String toString() {
        return "[" + direction + " - FiboResult: \"" + theResult.toString() + "\"]";
    }
}