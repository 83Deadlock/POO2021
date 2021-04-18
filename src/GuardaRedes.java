import java.time.LocalDate;

public class GuardaRedes extends Jogador {
    private int elasticidade;

    public GuardaRedes(String nome,
                       LocalDate dataDeNascimento,
                       String equipaAtual,
                       int velocidade,
                       int resistencia,
                       int destreza,
                       int impulsao,
                       int jogodecabeca,
                       int remate,
                       int passe,
                       int elasticidade) {
        super(nome, dataDeNascimento, equipaAtual, velocidade, resistencia, destreza, impulsao, jogodecabeca, remate, passe);
        this.elasticidade = elasticidade;
    }

    @Override
    public void calculateOverall() {

    }

    public static GuardaRedes fromLine(String line) {
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
        int elasticidade = Integer.parseInt(atributos[11]);
        return new GuardaRedes(nome, nascimento, equipa, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, passe, elasticidade);
    }
}
