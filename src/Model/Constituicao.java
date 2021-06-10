package Model;

import java.io.Serializable;
import java.util.*;

public class Constituicao implements Serializable {

    private enum Tatica { QTT, QQD}
    private GuardaRedes guardaRedes;
    private Defesa[] defesas;
    private Medio[] medios;
    private Avancado[] avançados;
    private Lateral[] laterais;
    private Lateral[] extremos;
    private Tatica tatica;


    public Constituicao(int tatica, Map<Integer,Jogador> jogadores){
        switch(tatica){
            case 1:
                this.tatica = Tatica.QQD;
                setGuardaRedes(getMelhorGuardaRedes(jogadores));
                this.defesas = new Defesa[2];
                setDefesas(getMelhoresDefesas(jogadores,2));
                this.laterais = new Lateral[2];
                setLaterais(getMelhoreslaterais(jogadores,2,new Lateral[0]));
                this.medios = new Medio[2];
                setMedios(getMelhoresMedios(jogadores,2));
                this.extremos = new Lateral[2];
                setExtremos(getMelhoreslaterais(jogadores,2,this.laterais));
                this.avançados = new Avancado[2];
                setAvançados(getMelhoresAvancados(jogadores,2));
                break;
            case 2:
                this.tatica = Tatica.QTT;
                setGuardaRedes(getMelhorGuardaRedes(jogadores));
                this.defesas = new Defesa[2];
                setDefesas(getMelhoresDefesas(jogadores,2));
                this.laterais = new Lateral[2];
                setLaterais(getMelhoreslaterais(jogadores,2,new Lateral[0]));
                this.medios = new Medio[3];
                setMedios(getMelhoresMedios(jogadores,3));
                this.extremos = new Lateral[2];
                setExtremos(getMelhoreslaterais(jogadores,2,this.laterais));
                this.avançados = new Avancado[1];
                setAvançados(getMelhoresAvancados(jogadores,1));
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
        return "Model.Constituicao{" +
                "tatica=" + taticToString(tatica) +
                '}';
    }

    public void setGuardaRedes(GuardaRedes j){
        this.guardaRedes = j;
    }

    public Defesa[] getDefesas() {
        return defesas;
    }

    public void setDefesas(Defesa[] defesas) {
        this.defesas = defesas;
    }

    public Medio[] getMedios() {
        return medios;
    }

    public void setMedios(Medio[] medios) {
        this.medios = medios;
    }

    public Avancado[] getAvançados() {
        return avançados;
    }

    public void setAvançados(Avancado[] avançados) {
        this.avançados = avançados;
    }

    public Lateral[] getLaterais() {
        return laterais;
    }

    public void setLaterais(Lateral[] laterais) {
        this.laterais = laterais;
    }

    public Lateral[] getExtremos() {
        return extremos;
    }

    public void setExtremos(Lateral[] extremos) {
        this.extremos = extremos;
    }

    public Tatica getTatica() {
        return tatica;
    }

    public void setTatica(Tatica tatica) {
        this.tatica = tatica;
    }

    public String toStringPrintable() {
        StringBuilder sb = new StringBuilder();
        sb.append(tatica + "\n");
        sb.append("Guarda-Redes: " + guardaRedes.getNumeroCamisola() + " - " + guardaRedes.getNome() + "\n");
        sb.append("Defesas Laterais: ");
        for(int i = 0; i < laterais.length; i++){
            sb.append(laterais[i].getNumeroCamisola() + " - " + laterais[i].getNome());
            if(i+1 != laterais.length){
                sb.append(", ");
            }
        }
        sb.append("\n");

        sb.append("Defesas: ");
        for(int i = 0; i < defesas.length; i++){
            sb.append(defesas[i].getNumeroCamisola() + " - " + defesas[i].getNome());
            if(i+1 != defesas.length){
                sb.append(", ");
            }
        }
        sb.append("\n");

        sb.append("Medios: ");
        for(int i = 0; i < medios.length; i++){
            sb.append(medios[i].getNumeroCamisola() + " - " + medios[i].getNome());
            if(i+1 != medios.length){
                sb.append(", ");
            }
        }
        sb.append("\n");

        sb.append("Extremos: ");
        for(int i = 0; i < extremos.length; i++){
            sb.append(extremos[i].getNumeroCamisola() + " - " + extremos[i].getNome());
            if(i+1 != extremos.length){
                sb.append(", ");
            }
        }
        sb.append("\n");

        sb.append("Avançados: ");
        for(int i = 0; i < avançados.length; i++){
            sb.append(avançados[i].getNumeroCamisola() + " - " + avançados[i].getNome());
            if(i+1 != avançados.length){
                sb.append(", ");
            }
        }
        sb.append("\n");

        return sb.toString();
    }

    public GuardaRedes getMelhorGuardaRedes(Map<Integer,Jogador> jogadores){
        GuardaRedes gr = null;
        for(Jogador j: jogadores.values()){
            if (j instanceof Model.GuardaRedes){
                if(gr == null){
                    GuardaRedes g = (GuardaRedes) j;
                    gr = g.clone();
                }
                if(gr.getOverall() < j.getOverall()){
                    GuardaRedes g = (GuardaRedes) j;
                    gr = g.clone();
                }
            }
        }
        return gr;
    }

    public Defesa[] getMelhoresDefesas(Map<Integer,Jogador> jogadores, int n){
        List<Defesa> aux = new ArrayList<>();
        for(Jogador j: jogadores.values()){
            if (j instanceof Model.Defesa){
                Defesa d = (Defesa) j;
                aux.add(d.clone());
            }
        }
        aux.sort(new JogadorComparatorOverall());
        Defesa defs[] = new Defesa[n];
        for(int i = 0; i < n; i++){
            defs[i] = aux.get(i);
        }
        return defs;
    }

    public Medio[] getMelhoresMedios(Map<Integer,Jogador> jogadores, int n){
        List<Medio> aux = new ArrayList<>();
        for(Jogador j: jogadores.values()){
            if (j instanceof Model.Medio){
                Medio d = (Medio) j;
                aux.add(d.clone());
            }
        }
        aux.sort(new JogadorComparatorOverall());
        Medio meds[] = new Medio[n];
        for(int i = 0; i < n; i++){
            meds[i] = aux.get(i);
        }
        return meds;
    }

    public Avancado[] getMelhoresAvancados(Map<Integer,Jogador> jogadores, int n){
        List<Avancado> aux = new ArrayList<>();
        for(Jogador j: jogadores.values()){
            if (j instanceof Model.Avancado){
                Avancado d = (Avancado) j;
                aux.add(d.clone());
            }
        }
        aux.sort(new JogadorComparatorOverall());
        Avancado meds[] = new Avancado[n];
        for(int i = 0; i < n; i++){
            meds[i] = aux.get(i);
        }
        return meds;
    }

    public Lateral[] getMelhoreslaterais(Map<Integer,Jogador> jogadores, int n, Lateral[] arg){
        List<Lateral> aux = new ArrayList<>();
        for(Jogador j: jogadores.values()){
            if (j instanceof Model.Lateral){
                Lateral d = (Lateral) j;
                aux.add(d.clone());
            }
        }
        for(int i = 0; i < arg.length; i++){
            aux.remove(arg[i]);
        }

        aux.sort(new JogadorComparatorOverall());
        Lateral meds[] = new Lateral[n];
        for(int i = 0; i < n; i++){
            meds[i] = aux.get(i);
        }
        return meds;
    }

}
