package View;

public class Output {
    /**
     * Apresenta mensagem numa linha com um '\n' no final
     * @param message mensagem
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Apresenta mensagem numa linha
     * @param s mensagem
     */
    public void printPrompt(String s) {
        System.out.print(s);
    }

    /**
     * Limpa o ecrã tornando a informação mais legível para o utilizador
     */
    public static void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
