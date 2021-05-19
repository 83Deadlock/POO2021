import java.time.LocalDate;
import java.util.Random;

public class Avancado extends Jogador {

    public Avancado(String nome,
                    LocalDate dataDeNascimento,
                    String equipaAtual,
                    int velocidade,
                    int resistencia,
                    int destreza,
                    int impulsao,
                    int jogodecabeca,
                    int remate,
                    int passe) {
        super(nome, dataDeNascimento, equipaAtual, velocidade, resistencia, destreza, impulsao, jogodecabeca, remate, passe);
    }

    public Avancado(String nome,
                    int numeroCamisola,
                    int velocidade,
                    int resistencia,
                    int destreza,
                    int impulsao,
                    int jogodecabeca,
                    int remate,
                    int passe) {
        super(nome, numeroCamisola, velocidade, resistencia, destreza, impulsao, jogodecabeca, remate, passe);
    }

    /** Método usado para calcular o overall de um avançado contando com os pesos nos atributos relativos à posição.
     *
     */
    @Override
    public void calculateOverall() {
        double overall = (this.getVelocidade() * 0.2) +
                (this.getResistencia() * 0.1) +
                (this.getDestreza() * 0.1) +
                (this.getImpulsao() * 0.15) +
                (this.getJogodecabeca() * 0.15) +
                (this.getRemate() * 0.2) +
                (this.getPasse() * 0.1);

        super.setOverall((int) overall);
    }

    public static Avancado fromLine(String line) {
        String[] atributos = line.split(",");
        String nome = atributos[0];
        //LocalDate nascimento = Helper.dateFromString(atributos[2]);
        //String equipa = atributos[3];
        int numeroCamisola = Integer.parseInt(atributos[1]);
        int velocidade = Integer.parseInt(atributos[2]);
        int resistencia = Integer.parseInt(atributos[3]);
        int destreza = Integer.parseInt(atributos[4]);
        int impulsao = Integer.parseInt(atributos[5]);
        int jogoCabeca = Integer.parseInt(atributos[6]);
        int remate = Integer.parseInt(atributos[7]);
        int passe = Integer.parseInt(atributos[8]);
        //int ratingPenaltis = random;
        return new Avancado(nome, numeroCamisola, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, passe);
    }


    public String toString() {
        return "Jogador{" +
                "nome='" + super.getNome() + '\'' +
                ", dataDeNascimento=" + super.getDataDeNascimento() +
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
}
