import java.time.LocalDate;

public class Defesa extends Jogador {
    private int rating_posicionamento;

    public Defesa(String nome,
                  LocalDate dataDeNascimento,
                  String equipaAtual,
                  int velocidade,
                  int resistencia,
                  int destreza,
                  int impulsao,
                  int jogodecabeca,
                  int remate,
                  int passe,
                  int rating_posicionamento) {
        super(nome, dataDeNascimento, equipaAtual, velocidade, resistencia, destreza, impulsao, jogodecabeca, remate, passe);
        this.rating_posicionamento = rating_posicionamento;
    }

    @Override
    public void calculateOverall() {

    }

    public static Defesa fromLine(String line) {
        String[] atributos = line.split(",");
        String nome = atributos[1];
        LocalDate nascimento = Helper.dateFromString(atributos[2]);
        String equipa = atributos[3];
        int velocidade = Integer.parseInt(atributos[4]);
        int resistencia = Integer.parseInt(atributos[5]);
        int destreza = Integer.parseInt(atributos[6]);
        int impulsao = Integer.parseInt(atributos[7]);
        int jogoCabeca = Integer.parseInt(atributos[8]);
        int remate = Integer.parseInt(atributos[9]);
        int passe = Integer.parseInt(atributos[10]);
        int ratingPosicionamento = Integer.parseInt(atributos[11]);
        return new Defesa(nome, nascimento, equipa, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, passe, ratingPosicionamento);
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
                ", posicionamento=" + rating_posicionamento +
                '}';
    }
}
