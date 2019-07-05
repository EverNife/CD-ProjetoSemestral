package br.com.finalcraft.unesp.cd.server.handlers.rmi.executables;

import br.com.finalcraft.unesp.cd.common.clientserver.messages.tcp.TCPMessage;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessage;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessageDirection;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessageExecutable;
import br.com.finalcraft.unesp.cd.server.handlers.rmi.executables.msg.RMIMessageFibonacciResult;

import java.math.BigInteger;

public class FibonacciSolver extends RMIMessage implements RMIMessageExecutable {

    private final BigInteger amount;

    public FibonacciSolver(TCPMessage.Fibonacci tcpMessage) {
        this.amount = tcpMessage.getAmount();
        this.direction = RMIMessageDirection.SERVER_TO_SLAVE;
    }

    @Override
    public RMIMessage execute() {
        return new RMIMessageFibonacciResult(fibo(amount.longValue()));
    }

    private BigInteger fibo(long n) {
        if (n == 0) return new BigInteger("0");
        if (n == 1) return new BigInteger("1");
        if (n == 2) return new BigInteger("1");

        n = n - 2;

        BigInteger bnum1 = new BigInteger("1");
        BigInteger bnum2 = new BigInteger("0");
        for(long i = 0; i < n; i++){
            bnum1 = bnum1.add(bnum2);
            bnum2 = bnum1.subtract(bnum2);
        }
        return bnum1.add(bnum2);
    }

    @Override
    public String toString() {
        return "[" + direction + " - Amount: \"" + amount + "\"]";
    }

}
