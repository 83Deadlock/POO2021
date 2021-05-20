import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorJogos {
    private List<Jogo> jogos;

    public GestorJogos(){
        this.jogos = new ArrayList<>();
    }

    public GestorJogos(List<Jogo> jogos){
        this.jogos = new ArrayList<>();
        for(Jogo j: jogos){
            this.jogos.add(j.clone());
        }
    }

    public GestorJogos(GestorJogos gj){
        this.jogos = gj.getJogos();
    }

    public List<Jogo> getJogos() {
        List<Jogo> j = new ArrayList<>();
        for(Jogo jogo: this.jogos){
            j.add(jogo.clone());
        }
        return j;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = new ArrayList<>();
        for(Jogo j: jogos){
            this.jogos.add(j.clone());
        }
    }

    public GestorJogos clone(){
        return new GestorJogos(this);
    }
}
