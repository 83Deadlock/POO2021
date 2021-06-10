package Model;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Equipa implements Serializable {
    private String nome;
    private int anoDaFundacao;
    private Map<Integer,Jogador> jogadores;
    private int overall;
    private Constituicao constituicao;
    //private final String[] constituicoes = {"QTT", "CTD", "TQT", "QQD", "QCU"};

    public Equipa(String nome, int anoDaFundacao, Constituicao constituicao) {
        this.nome = nome;
        this.anoDaFundacao = anoDaFundacao;
        this.jogadores = new HashMap<>();
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

    public Equipa(String nomeEquipa){
        this.nome = nomeEquipa;
        //this.anoDaFundacao = ;
        this.jogadores = new HashMap<>();
        this.overall = 0;
        Random r = new Random();
        //int index = r.nextInt(constituicoes.length);
        //int tatic = (Math.random() <= 0.5) ? 1 : 2;
        //this.constituicao = new Constituicao(tatic,jogadores);
        this.calcOverall();
    }

    public String getNome() {
        return nome;
    }

    public int getAnoDaFundacao() {
        return anoDaFundacao;
    }

    public Map<Integer,Jogador> getJogadores() {
        Map<Integer,Jogador> ret = new HashMap<>();

        for(Integer t: this.jogadores.keySet()){
            if(this.jogadores.get(t) instanceof GuardaRedes){
                GuardaRedes j = ((GuardaRedes) this.jogadores.get(t)).clone();
                ret.put(t,j);
            } else if (this.jogadores.get(t) instanceof Defesa){
                Defesa j = ((Defesa) this.jogadores.get(t)).clone();
                ret.put(t,j);
            } else if (this.jogadores.get(t) instanceof Lateral){
                Lateral j = ((Lateral) this.jogadores.get(t)).clone();
                ret.put(t,j);
            } else if (this.jogadores.get(t) instanceof Medio){
                Medio j = ((Medio) this.jogadores.get(t)).clone();
                ret.put(t,j);
            } else if (this.jogadores.get(t) instanceof Avancado){
                Avancado j = ((Avancado) this.jogadores.get(t)).clone();
                ret.put(t,j);
            }
        }
        return ret;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall){
        this.overall = overall;
    }

    public Constituicao getConstituicao() {
        return constituicao;
    }

    public void calcOverall(){
        double sum = 0;

        int count = this.jogadores.keySet().size();
        if(count == 0) this.overall = 0;
        else {
            for(Jogador j: this.jogadores.values()){
                sum += j.getOverall();
            }
            this.overall = (int) sum/count;
        }
    }

    public static Equipa fromLine(String line) {
        String[] atributos = line.split(",");
        String nome = atributos[0];
        return new Equipa(nome);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipa equipa = (Equipa) o;
        return anoDaFundacao == equipa.anoDaFundacao && overall == equipa.overall && Objects.equals(nome, equipa.nome) && Objects.equals(jogadores, equipa.jogadores) && Objects.equals(constituicao, equipa.constituicao);
    }

    public String toString() {
        return "Model.Equipa{" +
                "nome=" + nome +
                ", anoDaFundacao=" + anoDaFundacao +
                ", jogadores=" + jogadores.size() +
                ", overall=" + overall +
                ", constituicao=" + constituicao +
                '}';
    }

    public String geraPlantel(){
        StringBuilder sb = new StringBuilder();
        for(int i: jogadores.keySet()){
            sb.append(i + " - " + jogadores.get(i).basicInfoString()+"\n");
        }
        return sb.toString();
    }

    public Equipa clone(){
        return new Equipa(this);
    }

    public void adicionaJogador(Jogador j){
        if(this.jogadores.keySet().contains(j.getNumeroCamisola())){
            j.setNumeroCamisola(findProxNumeroDisponivel(j.getNumeroCamisola()));
        }
        this.jogadores.put(j.getNumeroCamisola(),j);
    }

    public void removeJogador(int numero){
        this.jogadores.remove(numero);
    }

    public void removeJogador(String nome) {
        Set<Map.Entry<Integer, Jogador>> entries = this.getJogadores().entrySet();
        for (Map.Entry<Integer, Jogador> entry : entries) {
            if (entry.getValue().getNome().equals(nome)) {
                this.removeJogador(entry.getKey());
            }
        }
    }

    public int findProxNumeroDisponivel(int numeroAtual){
        int ret = numeroAtual;
        while(this.jogadores.keySet().contains(ret)){
            if(ret == 99){
                ret = 1;
            } else {
                ret++;
            }
        }
        return ret;
    }

    public void fazConstituicao(){
        int tatic = (Math.random() <= 0.5) ? 1 : 2;
        this.constituicao = new Constituicao(tatic,jogadores);
    }

    // Assumes:
    // nome existe como value em jogadores
    public Jogador getJogadorByName(String nome) {
        return this.jogadores.values().stream().filter(a -> a.getNome().equals(nome)).collect(Collectors.toList()).get(0);
    }
}
