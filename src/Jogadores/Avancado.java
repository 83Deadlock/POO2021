package Jogadores;
import Geral.Equipa;
import Geral.Jogador;

import java.time.LocalDate;

public class Avancado extends Jogador {
    private int rating_penaltis;

    public Avancado(String nome,
                    LocalDate dataDeNascimento,
                    int overall,
                    Equipa equipaAtual,
                    int velocidade,
                    int resistencia,
                    int destreza,
                    int impulsao,
                    int jogodecabeca,
                    int remate,
                    int passe,
                    int rating_penaltis) {
        super(nome, dataDeNascimento, overall, equipaAtual, velocidade, resistencia, destreza, impulsao, jogodecabeca, remate, passe);
        this.rating_penaltis = rating_penaltis;
    }
}
