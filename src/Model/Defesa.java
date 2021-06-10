package Model;

import java.io.Serializable;

public class Defesa extends Jogador implements Serializable {

    public Defesa(String nome,
                  int numeroCamisola,
                  String equipaAtual,
                  int velocidade,
                  int resistencia,
                  int destreza,
                  int impulsao,
                  int jogodecabeca,
                  int remate,
                  int passe) {
        super(nome, numeroCamisola, equipaAtual, velocidade, resistencia, destreza, impulsao, jogodecabeca, remate, passe);
    }

    public Defesa (Defesa j){
        super(j.getNome(),j.getNumeroCamisola(),j.getEquipaAtual(),j.getVelocidade(),j.getResistencia(),
                j.getDestreza(),j.getImpulsao(),j.getJogodecabeca(),j.getRemate(),j.getPasse());
    }

    /** Método usado para calcular o overall de um avançado contando com os pesos nos atributos relativos à posição.
     *
     */
    @Override
    public void calculateOverall() {
        double overall = (this.getVelocidade() * 0.15) +
                (this.getResistencia() * 0.15) +
                (this.getDestreza() * 0.25) +
                (this.getImpulsao() * 0.15) +
                (this.getJogodecabeca() * 0.15) +
                (this.getRemate() * 0.05) +
                (this.getPasse() * 0.1);

        super.setOverall((int) overall);
    }

    public static Defesa fromLine(String line, String nomeEquipa) {
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
        return new Defesa(nome, numeroCamisola, equipa, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, passe);
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
                '}';
    }

    public Defesa clone(){
        return new Defesa(this);
    }

}
