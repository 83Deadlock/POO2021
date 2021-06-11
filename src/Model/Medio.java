package Model;

import java.io.Serializable;

public class Medio extends Jogador implements Serializable {
    private int recuperacao;

    public Medio(Jogador j){
        super(j.getNome(),j.getNumeroCamisola(),j.getEquipaAtual(),j.getVelocidade(),j.getResistencia(),
                j.getDestreza(),j.getImpulsao(),j.getJogodecabeca(),j.getRemate(),j.getPasse());
        double r = j.getDestreza() * 0.6;
        this.recuperacao = (int) r;
    }

    public Medio(String nome,
                 int numeroCamisola,
                 String equipaAtual,
                 int velocidade,
                 int resistencia,
                 int destreza,
                 int impulsao,
                 int jogodecabeca,
                 int remate,
                 int passe,
                 int recuperacao) {
        super(nome, numeroCamisola, equipaAtual, velocidade, resistencia, destreza, impulsao, jogodecabeca, remate, passe);
        this.recuperacao = recuperacao;
    }

    public Medio (Medio j){
        super(j.getNome(),j.getNumeroCamisola(),j.getEquipaAtual(),j.getVelocidade(),j.getResistencia(),
                j.getDestreza(),j.getImpulsao(),j.getJogodecabeca(),j.getRemate(),j.getPasse());
        this.recuperacao = j.getRecuperacao();
    }

    public int getRecuperacao() {
        return recuperacao;
    }

    public void setRecuperacao(int recuperacao) {
        this.recuperacao = recuperacao;
    }

    /** Método usado para calcular o overall de um avançado contando com os pesos nos atributos relativos à posição.
     *
     */
    @Override
    public void calculateOverall() {
        double overall = (this.getVelocidade() * 0.1) +
                (this.getResistencia() * 0.2) +
                (this.getDestreza() * 0.1) +
                (this.getImpulsao() * 0.05) +
                (this.getJogodecabeca() * 0.05) +
                (this.getRemate() * 0.1) +
                (this.getPasse() * 0.2) +
                (this.getRecuperacao() * 0.2);

        super.setOverall((int) overall);
    }

    public String detalheJogador() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: " + this.getNome()+"\n");
        sb.append("Posição: Médio\n");
        sb.append("Número: " + this.getNumeroCamisola()+"\n");
        sb.append("---> Atributos <---\n");
        sb.append("Velocidade = " + this.getVelocidade() + "\n");
        sb.append("Resistência = " + this.getResistencia() + "\n");
        sb.append("Destreza = " + this.getDestreza() + "\n");
        sb.append("Impulsão = " + this.getImpulsao() + "\n");
        sb.append("Jogo de Cabeça = " + this.getJogodecabeca() + "\n");
        sb.append("Remate = " + this.getRemate() + "\n");
        sb.append("Passe = " + this.getPasse() + "\n");
        sb.append("Recuperação de Bolas = " + this.getRecuperacao() + "\n");
        sb.append("---> Histórico <---\n");
        sb.append(this.historicoToString()+"\n");
        return sb.toString();
    }

    public static Medio fromLine(String line, String nomeEquipa) {
        String[] atributos = line.split(",");
        String nome = atributos[0];
        String equipa = nomeEquipa;
        int numeroCamisola = Integer.parseInt(atributos[1]);
        int velocidade = Integer.parseInt(atributos[2]);
        int resistencia = Integer.parseInt(atributos[3]);
        int destreza = Integer.parseInt(atributos[4]);
        int impulsao = Integer.parseInt(atributos[5]);
        int jogoCabeca = Integer.parseInt(atributos[6]);
        int remate = Integer.parseInt(atributos[7]);
        int passe = Integer.parseInt(atributos[8]);
        int recup = Integer.parseInt(atributos[9]);
        return new Medio(nome, numeroCamisola, equipa, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, passe, recup);
    }


    public String toString() {
        return "Model.Jogador{" +
                "nome='" + super.getNome() + '\'' +
                ", overall=" + super.getOverall() +
                ", equipaAtual=" + super.getEquipaAtual() +
                ", historico=" + super.getHistorico() +
                ", velocidade=" + super.getVelocidade() +
                ", resistencia=" + super.getResistencia() +
                ", destreza=" + super.getDestreza() +
                ", impulsao=" + super.getImpulsao() +
                ", jogodecabeca=" + super.getJogodecabeca() +
                ", remate=" + super.getRemate() +
                ", passe=" + super.getPasse() +
                ", recuperação de bola=" + recuperacao +
                '}';
    }

    public Medio clone(){
        return new Medio(this);
    }

}
