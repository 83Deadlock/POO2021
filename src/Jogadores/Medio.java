package Jogadores;
import Geral.Equipa;
import Geral.Jogador;

import java.time.LocalDate;

public class Medio extends Jogador {
    private int recup_bola;

    public Medio(String nome,
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
                 int recup_bola) {
        super(nome, dataDeNascimento, overall, equipaAtual, velocidade, resistencia, destreza, impulsao, jogodecabeca, remate, passe);
        this.recup_bola = recup_bola;
    }
}
