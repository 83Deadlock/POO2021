package View;

public class Apresentacao {

    private final Output out;
    private final ApresentacaoMain am;

    // Construtores
    public Apresentacao(){
        out = new Output();
        am = new ApresentacaoMain();
    }

    // Apresentação Main

    /**
     *  Apresenta menu de boas-vindas
     */
    public void welcome(){
        am.welcome();
    }

    /**
     * Apresenta mensagem
     * @param message mensagem
     */
    public void printMessage(String message) {
        out.printMessage(message);
    }
}
