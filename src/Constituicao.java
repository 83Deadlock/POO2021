import javax.swing.text.html.parser.Parser;
import java.security.Guard;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constituicao {
    private enum Tatica { QTT, QQD}
    private Jogador guardaRedes;
    private Jogador[] defesas;
    private Jogador[] medios;
    private Jogador[] avançados;
    private Jogador[] laterais;
    private Jogador[] extremos;
    private Tatica tatica;

    public Constituicao(String tatica){
        switch(tatica){
            case "QQD":
                this.tatica = Tatica.QQD;
                this.defesas = new Jogador[2];
                this.laterais = new Jogador[2];
                this.medios = new Jogador[2];
                this.extremos = new Jogador[2];
                this.avançados = new Jogador[2];
                break;
            case "QTT":
                this.tatica = Tatica.QTT;
                this.defesas = new Jogador[2];
                this.laterais = new Jogador[2];
                this.medios = new Jogador[3];
                this.extremos = new Jogador[2];
                this.avançados = new Jogador[1];
                break;
        }
    }

    private String taticToString(Tatica t){
        return switch (t) {
            case QTT -> "4-3-3";
            case QQD -> "4-4-2";
            default -> "";
        };
    }

    public String toString() {
        return "Constituicao{" +
                "tatica=" + taticToString(tatica) +
                '}';
    }

    public void setGuardaRedes(Jogador j){
        this.guardaRedes = j;
    }

    public Jogador[] getDefesas() {
        return defesas;
    }

    public void setDefesas(Jogador[] defesas) {
        this.defesas = defesas;
    }

    public Jogador[] getMedios() {
        return medios;
    }

    public void setMedios(Jogador[] medios) {
        this.medios = medios;
    }

    public Jogador[] getAvançados() {
        return avançados;
    }

    public void setAvançados(Jogador[] avançados) {
        this.avançados = avançados;
    }

    public Jogador[] getLaterais() {
        return laterais;
    }

    public void setLaterais(Jogador[] laterais) {
        this.laterais = laterais;
    }

    public Jogador[] getExtremos() {
        return extremos;
    }

    public void setExtremos(Jogador[] extremos) {
        this.extremos = extremos;
    }

    public Tatica getTatica() {
        return tatica;
    }

    public void setTatica(Tatica tatica) {
        this.tatica = tatica;
    }
}
