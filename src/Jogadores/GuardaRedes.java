package Jogadores;
import Geral.Equipa;
import Geral.Jogador;

import java.time.LocalDate;

public class GuardaRedes extends Jogador {
    private int elasticidade;

    public GuardaRedes(String nome,
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
                       int elasticidade) {
        super(nome, dataDeNascimento, overall, equipaAtual, velocidade, resistencia, destreza, impulsao, jogodecabeca, remate, passe);
        this.elasticidade = elasticidade;
    }
}
