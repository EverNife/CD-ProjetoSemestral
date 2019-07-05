package br.com.finalcraft.unesp.cd.main;

public enum ProgramModule {
    MAIN,
    CLIENT,
    SERVER,
    SLAVE;

    public static ProgramModule currentModule = MAIN;

    public static void setModule(ProgramModule programModule){
        currentModule = programModule;
    }

    public static ProgramModule getCurrent(){
        return currentModule;
    }

    public static String getTitle(){
        switch (currentModule){
            case MAIN:
                return "Main Program";
            case SERVER:
                return "Server";
            default:
                return "Dont_Know";
        }
    }

}
