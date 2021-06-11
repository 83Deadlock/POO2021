package View;

import java.time.LocalDate;
import java.util.Map;

public class Apresentacao {

    private final Output out;
    private ApresentacaoJogo aj;

    // Construtores
    public Apresentacao(){
        out = new Output();
        aj = new ApresentacaoJogo();
    }

    // Apresentação Main

    /**
     *  Apresenta menu de boas-vindas
     */
    public void welcome(){
        out.printMessage("---------------------------------------------------------------------------------------");
        out.printMessage("|                                                                                     |");
        out.printMessage("|                                     FootManager                                     |");
        out.printMessage("|                                                                                     |");
        out.printMessage("---------------------------------------------------------------------------------------");
        out.printMessage("Bem-vindo!");
        out.printMessage("Enter para começar!");
    }

    /**
     * Apresenta mensagem
     * @param message mensagem
     */
    public void printMessage(String message) {
        Output.clearScreen();
        out.printMessage(message);
    }

    public void imprimeEquipas(Map<Integer, Map.Entry<String, Integer>> menuEscolhas) {
        out.printMessage("----------------------------------------------------------------------------------------");
        for (int i : menuEscolhas.keySet()) {
            Map.Entry<String, Integer> stringIntegerEntry = menuEscolhas.get(i);
            String line = i + ". " + stringIntegerEntry.getKey() + ", " + stringIntegerEntry.getValue();
            out.printMessage(line);
        }
        out.printMessage("----------------------------------------------------------------------------------------");
    }

    public void printChoiceMenu(Map<Integer, String> menu) {
        out.printMessage("----------------------------------------------------------------------------------------");
        for (Map.Entry<Integer, String> entry : menu.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
        out.printMessage("----------------------------------------------------------------------------------------");
    }

    public void printPrompt(String s) {
        out.printPrompt(s);
    }

    public void printResultado(String casa, int scoreCasa, String fora, int scoreFora, LocalDate data) {
        aj.printResultado(casa,scoreCasa,fora,scoreFora,data);
    }

    public void printGoloCasa(int min, String casa, int golosCasa, String fora, int golosFora) {
        aj.printGoloCasa(min,casa,golosCasa,fora,golosFora);
    }
    public void printGoloFora(int min, String casa, int golosCasa, String fora, int golosFora) {
        aj.printGoloFora(min,casa,golosCasa,fora,golosFora);
    }

    public void penalti(int min, String eq) {
        aj.penalti(min,eq);
    }

    public void prinCanto(int min, String eq) {
        aj.printCanto(min,eq);
    }

    public void printBreak() {
        aj.printbreak();
    }
}
