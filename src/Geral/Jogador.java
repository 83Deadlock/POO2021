package Geral;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Jogador {
    private String nome;
    private LocalDate dataDeNascimento;
    private int overall;
    private String equipaAtual;
    private List<String> historico;

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
                   String equipaAtual,
                   int velocidade,
                   int resistencia,
                   int destreza,
                   int impulsao,
                   int jogodecabeca,
                   int remate,
                   int passe) {
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.overall = 0;
        this.equipaAtual = equipaAtual;
        this.historico = new ArrayList<String>();
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.impulsao = impulsao;
        this.jogodecabeca = jogodecabeca;
        this.remate = remate;
        this.passe = passe;
        this.calculateOverall();
    }

    public abstract void calculateOverall();
}
