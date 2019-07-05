package br.com.finalcraft.unesp.cd.server.handlers.rmi.executables.msg;

import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessage;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessageDirection;

public class RMIMessageCalculatorResult extends RMIMessage {

    private final String theResultingExpression;

    public String getTheResultingExpression() {
        return theResultingExpression;
    }

    public RMIMessageCalculatorResult(String theResultingExpression) {
        this.theResultingExpression = theResultingExpression;
        this.direction = RMIMessageDirection.SLAVE_TO_SERVER;
    }

    @Override
    public String toString() {
        return "[" + direction + " - TheExpression: \"" + theResultingExpression + "\"]";
    }
}