package View;

import java.util.Map;

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

    public void mainMenu() {
        System.out.println("1. Novo jogo");
        System.out.println("2. Carregar jogo");
        System.out.println("3. Sair");
    }

    public void imprimeEquipas(Map<Integer, Map.Entry<String, Integer>> menuEscolhas) {
        for (int i : menuEscolhas.keySet()) {
            Map.Entry<String, Integer> stringIntegerEntry = menuEscolhas.get(i);
            String line = i + ". " + stringIntegerEntry.getKey() + ", " + stringIntegerEntry.getValue();
            System.out.println(line);
        }
    }

    public void menuGestEquipa() {
        System.out.println("1. Editar constituição da equipa");
        System.out.println("2. Transferências");
        System.out.println("3. Ver constituição atual");
        System.out.println("4. Ver jogos realizados");
        System.out.println("5. Voltar");
    }

    public void printChoiceMenu(Map<Integer, String> menu) {
        for (Map.Entry<Integer, String> entry : menu.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }
}
