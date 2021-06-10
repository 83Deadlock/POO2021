package View;

public class Apresentacao {

    private final Output out;

    // Construtores
    public Apresentacao(){
        out = new Output();
    }

    /**
     * Apresenta mensagem
     * @param message mensagem
     */
    public void printMessage(String message) {
        out.printMessage(message);
    }
}
