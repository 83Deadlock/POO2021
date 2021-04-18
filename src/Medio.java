import java.time.LocalDate;

public class Medio extends Jogador {
    private int recup_bola;

    public Medio(String nome,
                 LocalDate dataDeNascimento,
                 String equipaAtual,
                 int velocidade,
                 int resistencia,
                 int destreza,
                 int impulsao,
                 int jogodecabeca,
                 int remate,
                 int passe,
                 int recup_bola) {
        super(nome, dataDeNascimento, equipaAtual, velocidade, resistencia, destreza, impulsao, jogodecabeca, remate, passe);
        this.recup_bola = recup_bola;
    }

    @Override
    public void calculateOverall() {

    }

    public static Medio fromLine(String line) {
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
        int recup = Integer.parseInt(atributos[11]);
        return new Medio(nome, nascimento, equipa, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, passe, recup);
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
                ", recuperação de bola=" + recup_bola +
                '}';
    }
}
