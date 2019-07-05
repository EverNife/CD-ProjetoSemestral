package br.com.finalcraft.unesp.cd.common;

public class SmartLogger {

    public static boolean verbose = true;

    public static void info(String message){
        System.out.println("[INFORM]" + message);
    }

    public static void info(String message, boolean lowLevel){
        if (lowLevel == false){
            info(message);
            return;
        } else if (verbose){
            System.out.println("[DETAIL]" + message);
        }
    }

}
