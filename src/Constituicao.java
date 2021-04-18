import javax.swing.text.html.parser.Parser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constituicao {
    private enum Tatica { QTT, CTD, TQT, QQD, QCU }
    private Jogador guardaRedes;
    private Jogador[] defesas;
    private Jogador[] medios;
    private Jogador[] avançados;
    private Jogador[] laterais;
    private Tatica tatica;

    public Constituicao(String tatica){
        switch(tatica){
            case "QQD":
                this.tatica = Tatica.QQD;
                this.defesas = new Jogador[2];
                this.medios = new Jogador[2];
                this.avançados = new Jogador[2];
                this.laterais = new Jogador[4];
                break;
            case "QTT":
                this.tatica = Tatica.QTT;
                this.defesas = new Jogador[2];
                this.medios = new Jogador[3];
                this.avançados = new Jogador[1];
                this.laterais = new Jogador[4];
                break;
            case "TQT":
                this.tatica = Tatica.TQT;
                this.defesas = new Jogador[3];
                this.medios = new Jogador[2];
                this.avançados = new Jogador[3];
                this.laterais = new Jogador[2];
                break;
            case "QCU":
                this.tatica = Tatica.QCU;
                this.defesas = new Jogador[2];
                this.medios = new Jogador[5];
                this.avançados = new Jogador[1];
                this.laterais = new Jogador[2];
                break;
        }

    }

    private String taticToString(Tatica t){
        return switch (t) {
            case QTT -> "4-3-3";
            case TQT -> "3-4-3";
            case QQD -> "4-4-2";
            case QCU -> "4-5-1";
            default -> "";
        };
    }
    @Override
    public String toString() {
        return "Constituicao{" +
                "tatica=" + taticToString(tatica) +
                '}';
    }
}
