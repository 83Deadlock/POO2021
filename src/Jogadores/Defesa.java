package Jogadores;
import Geral.Equipa;
import Geral.Jogador;

import java.time.LocalDate;

public class Defesa extends Jogador {
    private int rating_posicionamento;

    public Defesa(String nome,
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
                  int rating_posicionamento) {
        super(nome, dataDeNascimento, overall, equipaAtual, velocidade, resistencia, destreza, impulsao, jogodecabeca, remate, passe);
        this.rating_posicionamento = rating_posicionamento;
    }
}
