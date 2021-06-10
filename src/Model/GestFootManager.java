package Model;

import Model.Equipa;
import Model.Jogo;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GestFootManager {
    private Map<String,Equipa> equipas;
    private Map<LocalDate, List<Jogo>> jogos;
    private String minhaEquipa;

    public GestFootManager(){
        this.equipas = new HashMap<>();
        this.jogos = new HashMap<>();
    }

    public Map<String,Equipa> getEquipas(){
        return this.equipas.entrySet().stream().collect(Collectors.toMap(par -> par.getKey(), par -> par.getValue().clone()));
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

    public Map<String, Integer> getNomesEquipas() {
        Map<String, Integer> equipaToOverall = new HashMap<>();
        Set<String> todasEquipas = this.equipas.keySet();
        for (String eq : todasEquipas) {
            equipaToOverall.put(eq, this.equipas.get(eq).getOverall());
        }
        return equipaToOverall;
    }
}
