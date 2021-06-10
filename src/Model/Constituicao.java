package Model;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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
                jogadores.remove(guardaRedes.getNumeroCamisola());

                this.defesas = new Defesa[2];
                setDefesas(getMelhoresDefesas(jogadores,2));
                for(int i = 0; i < defesas.length; i++){
                    jogadores.remove(defesas[i].getNumeroCamisola());
                }

                this.laterais = new Lateral[2];
                setLaterais(getMelhoreslaterais(jogadores,2));
                for(int i = 0; i < laterais.length; i++){
                    jogadores.remove(laterais[i].getNumeroCamisola());
                }

                this.medios = new Medio[2];
                setMedios(getMelhoresMedios(jogadores,2));
                for(int i = 0; i < medios.length; i++){
                    jogadores.remove(medios[i].getNumeroCamisola());
                }

                this.extremos = new Lateral[2];
                setExtremos(getMelhoreslaterais(jogadores,2));
                for(int i = 0; i < extremos.length; i++){
                    jogadores.remove(extremos[i].getNumeroCamisola());
                }

                this.avançados = new Avancado[2];
                setAvançados(getMelhoresAvancados(jogadores,2));
                for(int i = 0; i < avançados.length; i++){
                    jogadores.remove(avançados[i].getNumeroCamisola());
                }

                break;
            case 2:
                this.tatica = Tatica.QTT;
                setGuardaRedes(getMelhorGuardaRedes(jogadores));
                jogadores.remove(guardaRedes.getNumeroCamisola());

                this.defesas = new Defesa[2];
                setDefesas(getMelhoresDefesas(jogadores,2));
                for(int i = 0; i < defesas.length; i++){
                    jogadores.remove(defesas[i].getNumeroCamisola());
                }

                this.laterais = new Lateral[2];
                setLaterais(getMelhoreslaterais(jogadores,2));
                for(int i = 0; i < laterais.length; i++){
                    jogadores.remove(laterais[i].getNumeroCamisola());
                }

                this.medios = new Medio[3];
                setMedios(getMelhoresMedios(jogadores,3));
                for(int i = 0; i < medios.length; i++){
                    jogadores.remove(medios[i].getNumeroCamisola());
                }

                this.extremos = new Lateral[2];
                setExtremos(getMelhoreslaterais(jogadores,2));
                for(int i = 0; i < extremos.length; i++){
                    jogadores.remove(extremos[i].getNumeroCamisola());
                }

                this.avançados = new Avancado[1];
                setAvançados(getMelhoresAvancados(jogadores,1));
                for(int i = 0; i < avançados.length; i++){
                    jogadores.remove(avançados[i].getNumeroCamisola());
                }

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

    public GuardaRedes getGuardaRedes(){
        return this.guardaRedes.clone();
    }

    public void setGuardaRedes(GuardaRedes j){
        this.guardaRedes = j;
    }

    public Defesa[] getDefesas() {
        Defesa ret[] = new Defesa[this.defesas.length];
        for(int i = 0; i < this.defesas.length; i++) ret[i] = this.defesas[i].clone();
        return ret;
    }

    public void setDefesas(Defesa[] defesas) {
        this.defesas = defesas;
    }

    public Medio[] getMedios() {
        Medio ret[] = new Medio[this.medios.length];
        for(int i = 0; i < this.medios.length; i++) ret[i] = this.medios[i].clone();
        return ret;
    }

    public void setMedios(Medio[] medios) {
        this.medios = medios;
    }

    public Avancado[] getAvançados() {
        Avancado ret[] = new Avancado[this.avançados.length];
        for(int i = 0; i < this.avançados.length; i++) ret[i] = this.avançados[i].clone();
        return ret;
    }

    public void setAvançados(Avancado[] avançados) {
        this.avançados = avançados;
    }

    public Lateral[] getLaterais() {
        Lateral ret[] = new Lateral[this.laterais.length];
        for(int i = 0; i < this.laterais.length; i++) ret[i] = this.laterais[i].clone();
        return ret;
    }

    public void setLaterais(Lateral[] laterais) {
        this.laterais = laterais;
    }

    public Lateral[] getExtremos() {
        Lateral ret[] = new Lateral[this.extremos.length];
        for(int i = 0; i < this.extremos.length; i++) ret[i] = this.extremos[i].clone();
        return ret;
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

        if(aux.size() < n){
            List<Jogador> newAux = jogadores.values().stream().collect(Collectors.toList());
            newAux.sort(new JogadorComparatorOverall());
            int indexLast = newAux.size() - 1;
            while(aux.size() < n){
                aux.add(new Defesa(newAux.get(indexLast)));
                newAux.remove(indexLast--);
            }
        }

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

        if(aux.size() < n){
            List<Jogador> newAux = jogadores.values().stream().collect(Collectors.toList());
            newAux.sort(new JogadorComparatorOverall());
            int indexLast = newAux.size() - 1;
            while(aux.size() < n){
                aux.add(new Medio(newAux.get(indexLast)));
                newAux.remove(indexLast--);
            }
        }

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

        if(aux.size() < n){
            List<Jogador> newAux = jogadores.values().stream().collect(Collectors.toList());
            newAux.sort(new JogadorComparatorOverall());
            int indexLast = newAux.size() - 1;
            while(aux.size() < n && newAux.size() > 0){
                aux.add(new Avancado(newAux.get(indexLast)));
                newAux.remove(indexLast--);
            }
        }

        for(int i = 0; i < n; i++){
            meds[i] = aux.get(i);
        }
        return meds;
    }

    public Lateral[] getMelhoreslaterais(Map<Integer,Jogador> jogadores, int n){
        List<Lateral> aux = new ArrayList<>();
        for(Jogador j: jogadores.values()){
            if (j instanceof Model.Lateral){
                Lateral d = (Lateral) j;
                aux.add(d.clone());
            }
        }

        aux.sort(new JogadorComparatorOverall());
        Lateral meds[] = new Lateral[n];

        if(aux.size() < n){
           List<Jogador> newAux = jogadores.values().stream().collect(Collectors.toList());
           newAux.sort(new JogadorComparatorOverall());
           int indexLast = newAux.size() - 1;
           while(aux.size() < n){
               aux.add(new Lateral(newAux.get(indexLast)));
               newAux.remove(indexLast--);
           }
        }

        for(int i = 0; i < n; i++){
            meds[i] = aux.get(i);
        }
        return meds;
    }

    public void trocaGuardaRedes(GuardaRedes entra) {
        setGuardaRedes(entra);
    }

    public void trocaDefesa(Defesa sai, Defesa entra) {
        int saiIndex = -1;
        for(int i = 0; i < defesas.length && saiIndex == -1; i++){
            if(defesas[i].getNumeroCamisola() == sai.getNumeroCamisola()){
                saiIndex = i;
            }
        }
        defesas[saiIndex] = entra.clone();

    }

    public void trocaLateral(Lateral sai, Lateral entra) {
        boolean entraExtremo = false;
        int saiIndex = -1;
        int entraIndex = -1;
        for(int i = 0; i < laterais.length && saiIndex == -1; i++){
            if(laterais[i].getNumeroCamisola() == sai.getNumeroCamisola()){
                saiIndex = i;
            }
        }
        for(int i = 0; i < extremos.length && !entraExtremo; i++){
            if(extremos[i].getNumeroCamisola() == entra.getNumeroCamisola()){
                entraIndex = i;
                entraExtremo = true;
            }
        }
        if(entraExtremo){
            extremos[entraIndex] = sai.clone();
        }
        laterais[saiIndex] = entra.clone();

    }

    public void trocaExtremo(Lateral sai, Lateral entra){
        boolean entraLateral = false;
        int saiIndex = -1;
        int entraIndex = -1;
        for(int i = 0; i < extremos.length && saiIndex == -1; i++){
            if(extremos[i].getNumeroCamisola() == sai.getNumeroCamisola()){
                saiIndex = i;
            }
        }
        for(int i = 0; i < laterais.length && !entraLateral; i++){
            if(laterais[i].getNumeroCamisola() == entra.getNumeroCamisola()){
                entraIndex = i;
                entraLateral = true;
            }
        }
        if(entraLateral){
            laterais[entraIndex] = sai.clone();
        }
        extremos[saiIndex] = entra.clone();
    }

    public void trocaMedio(Medio sai, Medio entra) {
        int saiIndex = -1;
        for(int i = 0; i < medios.length && saiIndex == -1; i++){
            if(medios[i].getNumeroCamisola() == sai.getNumeroCamisola()){
                saiIndex = i;
            }
        }
        medios[saiIndex] = entra.clone();

    }

    public void trocaAvancado(Avancado sai, Avancado entra) {
        int saiIndex = -1;
        for(int i = 0; i < avançados.length && saiIndex == -1; i++){
            if(avançados[i].getNumeroCamisola() == sai.getNumeroCamisola()){
                saiIndex = i;
            }
        }
        avançados[saiIndex] = entra.clone();

    }


}

