package Model;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public abstract class Jogador implements Serializable {
    // Nome do jogador
    private String nome;
    // Rating do Jogador
    private int overall;
    // Número da Camisola do Jogador
    private int numeroCamisola;
    // Set com os Nomes das Equipas onde o Jogador já jogou
    private Set<String> historico;
    // String com o nome da equipa do Jogador
    private String equipaAtual;

    // Atributos
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int jogodecabeca;
    private int remate;
    private int passe;

    public Jogador() {
        this.nome = "";
        this.overall = 0;
        this.numeroCamisola = 0;
        this.historico = new TreeSet<String>();
        this.equipaAtual = "";
        this.velocidade = 0;
        this.resistencia = 0;
        this.destreza = 0;
        this.impulsao = 0;
        this.jogodecabeca = 0;
        this.remate = 0;
        this.passe = 0;
    }

    public Jogador(String nome, int numeroCamisola, String equipaAtual, int velocidade, int resistencia, int destreza, int impulsao, int cabeca, int remate, int passe){
        this.nome = nome;
        this.overall = 0;
        this.numeroCamisola = numeroCamisola;
        this.historico = new TreeSet<>();
        this.equipaAtual = equipaAtual;
        this.historico.add(equipaAtual); //Adiciona a equipa do jogador ao seu histórico
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.impulsao = impulsao;
        this.jogodecabeca = cabeca;
        this.remate = remate;
        this.passe = passe;
        this.calculateOverall();
    }

    public Jogador(Jogador j){
        this.nome = j.getNome();
        this.overall = j.getOverall();
        this.historico = new TreeSet<>(j.getHistorico());
        this.equipaAtual = j.getEquipaAtual();
        this.numeroCamisola = j.getNumeroCamisola();
        this.velocidade = j.getVelocidade();
        this.resistencia = j.getResistencia();
        this.destreza = j.getDestreza();
        this.impulsao = j.getImpulsao();
        this.jogodecabeca = j.getJogodecabeca();
        this.remate = j.getRemate();
        this.passe = j.getPasse();
        this.calculateOverall();
    }

    public abstract void calculateOverall();

    public String getNome() {
        return nome;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public Set<String> getHistorico() {
        return this.historico.stream().collect(Collectors.toSet());
    }

    public String getEquipaAtual() {
        return equipaAtual;
    }

    public void setEquipaAtual(String equipaAtual) {
        this.equipaAtual = equipaAtual;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public int getResistencia() {
        return resistencia;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getImpulsao() {
        return impulsao;
    }

    public int getJogodecabeca() {
        return jogodecabeca;
    }

    public int getRemate() {
        return remate;
    }

    public int getPasse() {
        return passe;
    }

    public int getNumeroCamisola() {
        return numeroCamisola;
    }

    public void setNumeroCamisola(int numeroCamisola) {
        this.numeroCamisola = numeroCamisola;
    }


    /** Métodos para gestão de um jogador
     *
     */
    /** Altera a equipa do Model.Jogador
     *
     * @param nomeEquipa - Nome da equipa nova do jogador
     */
    public void alteraEquipa(String nomeEquipa){
        this.setEquipaAtual(nomeEquipa);
        this.historico.add(nomeEquipa);
    }

    public String toStringBasic() {
        return "Nome: " + this.getNome() + " | Overall: " + this.getOverall();
    }

    public abstract String detalheJogador();

    public String historicoToString(){
        String ret = "[";
        for (String equipa : historico) {
            ret += equipa + ", ";
        }
        ret = removeLastChars(ret, 2);
        ret += "]";
        return ret;
    }

    // Mover para um ficheiro helper ??
    public static String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }

    public void addEquipaToHistorico(String minhaEquipa){
        this.historico.add(minhaEquipa);
    }
}
