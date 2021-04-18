package Jogadores;
import Geral.Equipa;
import Geral.Helper;
import Geral.Jogador;

import java.time.LocalDate;

public class Avancado extends Jogador {
    private int rating_penaltis;

    public Avancado(String nome,
                    LocalDate dataDeNascimento,
                    String equipaAtual,
                    int velocidade,
                    int resistencia,
                    int destreza,
                    int impulsao,
                    int jogodecabeca,
                    int remate,
                    int passe,
                    int rating_penaltis) {
        super(nome, dataDeNascimento, equipaAtual, velocidade, resistencia, destreza, impulsao, jogodecabeca, remate, passe);
        this.rating_penaltis = rating_penaltis;
    }

    @Override
    public void calculateOverall() {

    }

    public static Avancado fromLine(String line) {
        String[] atributos = line.split(",");
        String nome = atributos[1];
        LocalDate nascimento = Helper.dateFromString(atributos[2]);
        String equipa = atributos[3];
        int velocidade = Integer.parseInt(atributos[4]);
        int resistencia = Integer.parseInt(atributos[5]);
        int destreza = Integer.parseInt(atributos[6]);
        int impulsao = Integer.parseInt(atributos[7]);
        int jogoCabeca = Integer.parseInt(atributos[8]);
        int remate = Integer.parseInt(atributos[9]);
        int passe = Integer.parseInt(atributos[10]);
        int ratingPenaltis = Integer.parseInt(atributos[11]);
        return new Avancado(nome, nascimento, equipa, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, passe, ratingPenaltis);
    }
}
