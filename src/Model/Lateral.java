package Model;

import java.io.Serializable;

public class Lateral extends Jogador implements Serializable {
    private int cruzamentos;

    public Lateral(Jogador j){
        super(j.getNome(),j.getNumeroCamisola(),j.getEquipaAtual(),j.getVelocidade(),j.getResistencia(),
                j.getDestreza(),j.getImpulsao(),j.getJogodecabeca(),j.getRemate(),j.getPasse());
        double c = j.getPasse() * 0.7;
        this.cruzamentos = (int) c;
    }

    public Lateral(String nome,
                   int numeroCamisola,
                   String equipaAtual,
                   int velocidade,
                   int resistencia,
                   int destreza,
                   int impulsao,
                   int jogodecabeca,
                   int remate,
                   int passe,
                   int cruzamentos) {
        super(nome, numeroCamisola, equipaAtual, velocidade, resistencia, destreza, impulsao, jogodecabeca, remate, passe);
        this.cruzamentos = cruzamentos;
    }

    public Lateral (Lateral j){
        super(j.getNome(),j.getNumeroCamisola(),j.getEquipaAtual(),j.getVelocidade(),j.getResistencia(),
                j.getDestreza(),j.getImpulsao(),j.getJogodecabeca(),j.getRemate(),j.getPasse());
        this.cruzamentos = j.getCruzamentos();
    }

    public int getCruzamentos() {
        return cruzamentos;
    }

    public void setCruzamentos(int cruzamentos) {
        this.cruzamentos = cruzamentos;
    }

    /** Método usado para calcular o overall de um avançado contando com os pesos nos atributos relativos à posição.
     *
     */
    @Override
    public void calculateOverall() {
        double overall = (this.getVelocidade() * 0.2) +
                (this.getResistencia() * 0.2) +
                (this.getDestreza() * 0.1) +
                (this.getImpulsao() * 0.05) +
                (this.getJogodecabeca() * 0.05) +
                (this.getRemate() * 0.05) +
                (this.getPasse() * 0.15) +
                (this.getCruzamentos() * 0.2);

        super.setOverall((int) overall);
    }

    public static Lateral fromLine(String line, String nomeEquipa) {
        String[] atributos = line.split(",");
        String nome = atributos[0];
        //LocalDate nascimento = Helper.dateFromString(atributos[2]);
        String equipa = nomeEquipa;
        int numeroCamisola = Integer.parseInt(atributos[1]);
        int velocidade = Integer.parseInt(atributos[2]);
        int resistencia = Integer.parseInt(atributos[3]);
        int destreza = Integer.parseInt(atributos[4]);
        int impulsao = Integer.parseInt(atributos[5]);
        int jogoCabeca = Integer.parseInt(atributos[6]);
        int remate = Integer.parseInt(atributos[7]);
        int passe = Integer.parseInt(atributos[8]);
        int cruzamentos = Integer.parseInt(atributos[9]);
        return new Lateral(nome, numeroCamisola, equipa,velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, passe, cruzamentos);
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
                ", cruzamentos=" + cruzamentos +
                '}';
    }

    public Lateral clone(){
        return new Lateral(this);
    }
}
