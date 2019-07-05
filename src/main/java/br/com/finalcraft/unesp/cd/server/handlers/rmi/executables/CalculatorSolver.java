package br.com.finalcraft.unesp.cd.server.handlers.rmi.executables;

import br.com.finalcraft.unesp.cd.common.clientserver.messages.tcp.TCPMessage;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessage;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessageDirection;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessageExecutable;
import br.com.finalcraft.unesp.cd.server.handlers.rmi.executables.msg.RMIMessageCalculatorResult;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class CalculatorSolver extends RMIMessage implements RMIMessageExecutable {

    private final String expressionToSolve;

    public CalculatorSolver(TCPMessage.Calculator tcpMessage) {
        this.expressionToSolve = tcpMessage.getTheExpression();
        this.direction = RMIMessageDirection.SERVER_TO_SLAVE;
    }

    public CalculatorSolver(String expressionToSolve) {
        this.expressionToSolve = expressionToSolve;
    }

    @Override
    public RMIMessage execute() {
        String result = "";
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            try {
                result = engine.eval(this.expressionToSolve).toString();
            }catch (Exception e){
                result = "!Error: NumberFormat";
            }
        }catch (Exception e){
            System.out.println("Error getting JavaScriptEngines....");
        }
        return new RMIMessageCalculatorResult(result);
    }

    @Override
    public String toString() {
        return "[" + direction + " - TheExpression: \"" + expressionToSolve + "\"]";
    }
}
