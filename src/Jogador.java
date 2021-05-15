import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Jogador {
    private String nome;
    private LocalDate dataDeNascimento;
    private int overall;
    private String equipaAtual;
    private int numeroCamisola;
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
        this.nome = "";
        this.dataDeNascimento = LocalDate.now();
        this.overall = 0;
        this.equipaAtual = "";
        this.numeroCamisola = 0;
        this.historico = new ArrayList<>();
        this.velocidade = 0;
        this.resistencia = 0;
        this.destreza = 0;
        this.impulsao = 0;
        this.jogodecabeca = 0;
        this.remate = 0;
        this.passe = 0;
    }

    public Jogador(String nome, LocalDate dataDeNascimento, String equipaAtual,
                   int velocidade, int resistencia, int destreza,
                   int impulsao, int jogodecabeca, int remate,
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

    public String getNome() {
        return nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public String getEquipaAtual() {
        return equipaAtual;
    }

    public void setEquipaAtual(String equipaAtual) {
        this.equipaAtual = equipaAtual;
    }

    public List<String> getHistorico() {
        return historico;
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

    /** Método utilizado para alterar o número na camisola de um jogador
     *
     * @param novo - novo numero da camisola do jogador
     */
    public void alteraNumeroCamisola(int novo){
        this.setNumeroCamisola(novo);
    }

    /** Altera a equipa atual do jogador e adiciona a sua atual equipa ao seu histórico
     *
     * @param nome - Nome da equipa nova do jogador
     */
    public void alteraEquipa(String nome){
        this.historico.add(this.getEquipaAtual()); //Adiciona a equipa atual ao historico
        this.setEquipaAtual(nome);  //Altera a equipa atual
    }
}
