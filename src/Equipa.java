import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public Equipa(Equipa e) {
        this.nome = e.getNome();
        this.anoDaFundacao = e.getAnoDaFundacao();
        this.jogadores = e.getJogadores();
        this.overall = e.getOverall();
        this.constituicao = e.getConstituicao();
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
        double sum = 0;

        int count = this.jogadores.size();
        if(count == 0) this.overall = 0;
        else {
            for(Jogador j: this.jogadores){
                sum += j.getOverall();
            }
            this.overall = (int) sum/count;
        }
    }

    public static Equipa fromLine(String line) {
        String[] atributos = line.split(",");
        String nome = atributos[1];
        int nascimento = Integer.parseInt(atributos[2]);
        Constituicao constituicao = new Constituicao(atributos[3]);

        return new Equipa(nome, nascimento, constituicao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipa equipa = (Equipa) o;
        return anoDaFundacao == equipa.anoDaFundacao && overall == equipa.overall && Objects.equals(nome, equipa.nome) && Objects.equals(jogadores, equipa.jogadores) && Objects.equals(constituicao, equipa.constituicao);
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

    public Equipa clone(){
        return new Equipa(this);
    }

    public void adicionaJogador(Jogador j){
        this.jogadores.add(j);
    }

    public void removeJogador(Jogador j){
        this.jogadores.remove(j);
    }

    public String generateID(){
        return (this.getNome() + this.getAnoDaFundacao());
    }
}
