package View;

import java.time.LocalDate;

public class ApresentacaoJogo {
    public void printGoloCasa(int min, String casa, int golosCasa, String fora, int golosFora) {
        System.out.println(min + "' -> GOLO DO " + casa.toUpperCase() + "!!!\n" + "Resultado: " + casa + " " + golosCasa + " - " + golosFora + " " + fora);
    }

    public void printGoloFora(int min, String casa, int golosCasa, String fora, int golosFora) {
        System.out.println(min + "' -> GOLO DO " + fora.toUpperCase() + "!!!\n" + "Resultado: " + casa + " " + golosCasa + " - " + golosFora + " " + fora);
    }

    public void penalti(int min, String eq) {
        System.out.println(min +"' -> PENALTI PARA " + eq.toUpperCase());
    }

    public void printCanto(int min, String eq) {
        System.out.println(min +"' -> CANTO PARA " + eq.toUpperCase());
    }

    public void printbreak() {
        System.out.println("-----> Intervalo <-----");
    }

    public void printResultado(String casa, int scoreCasa, String fora, int scoreFora, LocalDate data) {
        Output.clearScreen();
        System.out.println("-------------------------------------- " + data + " --------------------------------------");
        System.out.println(casa + " " + scoreCasa + " - " + scoreFora + " " + fora);
    }
}
