package View;

import java.time.LocalDate;
import java.util.Map;

public interface IApresentacao {
    void welcome();

    void printMessage(String message);

    void imprimeEquipas(Map<Integer, Map.Entry<String, Integer>> menuEscolhas);

    void printChoiceMenu(Map<Integer, String> menu);

    void printPrompt(String s);

    void printResultado(String casa, int scoreCasa, String fora, int scoreFora, LocalDate data);

    void printGoloCasa(int min, String casa, int golosCasa, String fora, int golosFora);

    void printGoloFora(int min, String casa, int golosCasa, String fora, int golosFora);

    void penalti(int min, String eq);

    void prinCanto(int min, String eq);

    void printBreak();

    void printDetalheJogador(String detalheJogador);

    void clearScreen();
}
