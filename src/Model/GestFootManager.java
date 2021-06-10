package Model;

import Model.Equipa;
import Model.Jogo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GestFootManager implements Serializable {
    private Map<String,Equipa> equipas;
    private Map<LocalDate, List<Jogo>> jogos;
    private String minhaEquipa;

    public GestFootManager(){
        this.equipas = new HashMap<>();
        this.jogos = new HashMap<>();
    }

    public Map<String,Equipa> getEquipasCloned(){
        return this.equipas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, par -> par.getValue().clone()));
    }

    // Não cloneia as Equipas, usar com cuidado
    public Map<String,Equipa> getEquipas(){
        return this.equipas.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setEquipas(Map<String, Equipa> equipas) {
        this.equipas = new HashMap<>();
        equipas.entrySet().forEach(e -> this.equipas.put(e.getKey(),e.getValue().clone()));
    }

    public Map<LocalDate,List<Jogo>> getJogos(){
        Map<LocalDate,List<Jogo>> mapAux = new HashMap<>();
        for(LocalDate ld : this.jogos.keySet()){
            mapAux.put(ld,new ArrayList<>());
            for(Jogo j : this.jogos.get(ld)){
                mapAux.get(ld).add(j.clone());
            }
        }
        return mapAux;
    }

    public void setJogos(Map<LocalDate,List<Jogo>> jogos){
        this.jogos = new HashMap<>();
        jogos.entrySet().forEach(e -> this.jogos.put(e.getKey(),e.getValue().stream().map(j -> j.clone()).collect(Collectors.toList())));
    }

    public void adicionaEquipa(Equipa e){
        equipas.put(e.getNome(),e.clone());
    }

    public void adicionaJogo(Jogo j){
        if(!jogos.containsKey(j.getData())){
           jogos.put(j.getData(),new ArrayList<>());
        }
        jogos.get(j.getData()).add(j.clone());
    }

    public void setMinhaEquipa(String minhaEquipa) {
        this.minhaEquipa = minhaEquipa;
    }

    public String getMinhaEquipa(){
        return this.minhaEquipa;
    }

    public Map<String, Integer> getNomesEquipas() {
        Map<String, Integer> equipaToOverall = new HashMap<>();
        Set<String> todasEquipas = this.equipas.keySet();
        for (String eq : todasEquipas) {
            equipaToOverall.put(eq, this.equipas.get(eq).getOverall());
        }
        return equipaToOverall;
    }

    public String getJogosWith(String team){
        Map<LocalDate,List<Jogo>> jogosTeam = new HashMap<>();
        for(LocalDate d: jogos.keySet()){
            jogosTeam.put(d,filterJogosEquipa(jogos.get(d),team));
        }
        StringBuilder sb = new StringBuilder();
        for(LocalDate d: jogosTeam.keySet()){
            for(Jogo j: jogosTeam.get(d)){
                sb.append(j.toString() + "\n");
            }
        }

        return sb.toString();
    }

    private List<Jogo> filterJogosEquipa(List<Jogo> jogos, String team) {
        List<Jogo> res = new ArrayList<>();
        for(Jogo j: jogos){
            if(j.getEquipaCasa().getNome().equals(team) || j.getEquipaFora().getNome().equals(team)){
                res.add(j.clone());
            }
        }
        return res;
    }

    public String printConstituicao(String minhaEquipa) {
        return this.equipas.get(minhaEquipa).getConstituicao().toStringPrintable();
        //for(int i : equipas.get(minhaEquipa).getJogadores().keySet()){
        //    System.out.println(i + " -> "+equipas.get(minhaEquipa).getJogadores().get(i).getClass().getName());
        //}
        //return "";
    }
}
