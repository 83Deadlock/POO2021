package View;

import java.util.Map;

public class Apresentacao {

    private final Output out;

    // Construtores
    public Apresentacao(){
        out = new Output();
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
        out.printMessage(message);
    }

    public void imprimeEquipas(Map<Integer, Map.Entry<String, Integer>> menuEscolhas) {
        for (int i : menuEscolhas.keySet()) {
            Map.Entry<String, Integer> stringIntegerEntry = menuEscolhas.get(i);
            String line = i + ". " + stringIntegerEntry.getKey() + ", " + stringIntegerEntry.getValue();
            out.printMessage(line);
        }
    }

    public void printChoiceMenu(Map<Integer, String> menu) {
        for (Map.Entry<Integer, String> entry : menu.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    public void printPrompt(String s) {
        out.printPrompt(s);
    }
}
