package View;

public class Output {
    /**
     * Apresenta mensagem
     * @param message mensagem
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printPrompt(String s) {
        System.out.print(s);
    }

    public static void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
