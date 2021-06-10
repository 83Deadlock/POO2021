package View;

public class ApresentacaoMain {
    private final Output out;

    public ApresentacaoMain(){
        out = new Output();
    }

    /**
     * Apresenta menu welcome
     */
    public void welcome(){
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("|                                                                                     |");
        System.out.println("|                                     FootManager                                     |");
        System.out.println("|                                                                                     |");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Welcome to the game where you are the coach!");
        System.out.println("Press any key to continue.");
    }

    /**
     * Apresenta mensagem comando inválido
     */
    public void printErroComandoInvalido(){
        System.out.println("Comando Inválido");
    }
}
