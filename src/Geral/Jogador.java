package Geral;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private LocalDate dataDeNascimento;
    private int overall;
    private Equipa equipaAtual;
    private List<Equipa> historico;

    // Atributos
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int jogodecabeca;
    private int remate;
    private int passe;

    public Jogador() {

    }
    public Jogador(String nome,
                   LocalDate dataDeNascimento,
                   int overall,
                   Equipa equipaAtual,
                   int velocidade,
                   int resistencia,
                   int destreza,
                   int impulsao,
                   int jogodecabeca,
                   int remate,
                   int passe) {
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.overall = overall;
        this.equipaAtual = equipaAtual;
        this.historico = new ArrayList<>();
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.impulsao = impulsao;
        this.jogodecabeca = jogodecabeca;
        this.remate = remate;
        this.passe = passe;
    }
}
