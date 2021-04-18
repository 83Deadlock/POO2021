import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Equipa {
    private String nome;
    private int anoDaFundacao;
    private List<Jogador> jogadores;
    private int overall;
    private Constituicao constituicao;

    public Equipa(String nome, int anoDaFundacao, Constituicao constituicao) {
        this.nome = nome;
        this.anoDaFundacao = anoDaFundacao;
        this.jogadores = new ArrayList<>();
        this.overall = 0;
        this.constituicao = constituicao;
    }

    public String getNome() {
        return nome;
    }

    public int getAnoDaFundacao() {
        return anoDaFundacao;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public int getOverall() {
        return overall;
    }

    public Constituicao getConstituicao() {
        return constituicao;
    }

    public void calcOverall(){

    }

    public static Equipa fromLine(String line) {
        String[] atributos = line.split(",");
        String nome = atributos[1];
        int nascimento = Integer.parseInt(atributos[2]);
        Constituicao constituicao = new Constituicao(atributos[3]);

        return new Equipa(nome, nascimento, constituicao);
    }

    public String toString() {
        return "Equipa{" +
                "nome=" + nome +
                ", anoDaFundacao=" + anoDaFundacao +
                ", jogadores=" + jogadores.size() +
                ", overall=" + overall +
                ", constituicao=" + constituicao +
                '}';
    }
}
