package br.com.finalcraft.unesp.cd.server.handlers.tcp;

import br.com.finalcraft.unesp.cd.common.SmartLogger;
import br.com.finalcraft.unesp.cd.common.clientserver.messages.tcp.TCPMessage;
import br.com.finalcraft.unesp.cd.common.clientserver.messages.tcp.TCPMessageDirection;
import br.com.finalcraft.unesp.cd.common.slaveserver.messages.rmi.RMIMessage;
import br.com.finalcraft.unesp.cd.server.handlers.rmi.ServerSideRMI;
import br.com.finalcraft.unesp.cd.server.handlers.rmi.executables.CalculatorSolver;
import br.com.finalcraft.unesp.cd.server.handlers.rmi.executables.FibonacciSolver;
import br.com.finalcraft.unesp.cd.server.handlers.rmi.executables.SliddingPuzzleSolver;
import br.com.finalcraft.unesp.cd.server.handlers.rmi.executables.msg.RMIMessageCalculatorResult;
import br.com.finalcraft.unesp.cd.server.handlers.rmi.executables.msg.RMIMessageFibonacciResult;
import br.com.finalcraft.unesp.cd.server.handlers.rmi.executables.msg.RMIMessageSlidingResult;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ServerSocketThread extends Thread{

    public String identifier;

    Socket clientConnecting;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public ServerSocketThread(Socket clientConnecting) throws Exception{
        this.clientConnecting = clientConnecting;
        this.objectInputStream = new ObjectInputStream(clientConnecting.getInputStream());
        this.objectOutputStream = new ObjectOutputStream(clientConnecting.getOutputStream());
        this.identifier = "[" + clientConnecting.getInetAddress().getHostAddress() + " - " + clientConnecting.getLocalPort() + "]";
    }

    public void sendToClient(TCPMessage tcpMessage) throws Exception{
        info("Sending TCPMessage." + tcpMessage.getClass().getSimpleName() + " to client: " + tcpMessage.toString());
        objectOutputStream.flush();
        objectOutputStream.writeObject(tcpMessage);
    }

    public TCPMessage readFromClient() throws Exception{
        info("Waiting TCPMessage from Client!...");
        Object readObject = objectInputStream.readObject();
        if (readObject != null && readObject instanceof TCPMessage){
            TCPMessage tcpMessage = (TCPMessage) readObject;
            info("Received TCPMessage." + tcpMessage.getClass().getSimpleName() + ": " + tcpMessage.toString());
            return (TCPMessage) readObject;
        }
        throw new Exception("Esparava uma TCPMessage, mas recebi outro objeto...");
    }

    public void info(String message){
        SmartLogger.info("[Thread - " + clientConnecting.getInetAddress().getHostAddress() +":" + clientConnecting.getPort() + "]: " + message.toString());
    }

    public void verboseInfo(String message){
        SmartLogger.info("[Thread - " + clientConnecting.getInetAddress().getHostAddress() +":" + clientConnecting.getPort() + "]: " + message.toString(),true);
    }

    @Override
    public void run() {
        while(true) {
            try {

                TCPMessage tcpMessage = readFromClient();

                handleTCPMessage(tcpMessage);

            }
            catch(SocketException e) {
                System.out.println("Erro: ");
                e.printStackTrace();
                break;
            }
            catch(Exception e) {
                System.out.println("Erro: ");
                e.printStackTrace();
            }
        }

    }

    public void handleTCPMessage(TCPMessage tcpMessage) throws Exception{
        if (tcpMessage instanceof TCPMessage.Calculator){
            handleCalculatorMessage((TCPMessage.Calculator) tcpMessage);
        }
        else if (tcpMessage instanceof TCPMessage.Fibonacci){
            handleFibonacciMessage((TCPMessage.Fibonacci) tcpMessage);
        }
        else if (tcpMessage instanceof TCPMessage.SliddingPuzzle){
            handleSliddingPuzzleMessage((TCPMessage.SliddingPuzzle) tcpMessage);
        }
    }

    public void handleCalculatorMessage(TCPMessage.Calculator tcpMessage) throws Exception{
        CalculatorSolver calculatorSolver = new CalculatorSolver(tcpMessage);
        RMIMessage rmiMessageResult = ServerSideRMI.getExecutor().runExecutable(calculatorSolver);

        info("RMIResultingMessage : " + rmiMessageResult);

        RMIMessageCalculatorResult calcResult = (RMIMessageCalculatorResult) rmiMessageResult;
        sendToClient(new TCPMessage.Calculator(calcResult.getTheResultingExpression(), TCPMessageDirection.SERVER_TO_CLIENT));
    }

    public void handleFibonacciMessage(TCPMessage.Fibonacci tcpMessage) throws Exception{
        FibonacciSolver fibonacciSolver = new FibonacciSolver(tcpMessage);
        RMIMessage rmiMessageResult = ServerSideRMI.getExecutor().runExecutable(fibonacciSolver);

        info("RMIResultingMessage : " + rmiMessageResult);

        RMIMessageFibonacciResult fibonacciResult = (RMIMessageFibonacciResult) rmiMessageResult;
        sendToClient(new TCPMessage.Fibonacci(fibonacciResult.getTheResult(), TCPMessageDirection.SERVER_TO_CLIENT));
    }

    public void handleSliddingPuzzleMessage(TCPMessage.SliddingPuzzle tcpMessage) throws Exception{
        SliddingPuzzleSolver sliddingPuzzleSolver = new SliddingPuzzleSolver(tcpMessage);
        RMIMessage rmiMessageResult = ServerSideRMI.getExecutor().runExecutable(sliddingPuzzleSolver);

        info("RMIResultingMessage : " + rmiMessageResult);

        RMIMessageSlidingResult slidingResult = (RMIMessageSlidingResult) rmiMessageResult;
        sendToClient(new TCPMessage.SliddingPuzzle(slidingResult.getSolvingDirections(), TCPMessageDirection.SERVER_TO_CLIENT));
    }
}
