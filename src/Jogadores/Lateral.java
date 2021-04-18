package Jogadores;
import Geral.Equipa;
import Geral.Jogador;

import java.time.LocalDate;

public class Lateral extends Jogador {
    private int rating_cruzamentos;

    public Lateral(String nome,
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
                   int rating_cruzamentos) {
        super(nome, dataDeNascimento, overall, equipaAtual, velocidade, resistencia, destreza, impulsao, jogodecabeca, remate, passe);
        this.rating_cruzamentos = rating_cruzamentos;
    }
}
