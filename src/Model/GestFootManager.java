package Model;

import Model.Equipa;
import Model.Jogo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GestFootManager implements Serializable, IGestFootManager {
    // Map que contém as equipas registadas no sistema
    private Map<String,Equipa> equipas;
    // Map que contém os jogos registados no sistema
    private Map<LocalDate, List<Jogo>> jogos;
    // String com o nome da Equipa que o user controla
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

    public void setJogos(Map<LocalDate, List<Jogo>> jogos){
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

    public List<Jogo> filterJogosEquipa(List<Jogo> jogos, String team) {
        List<Jogo> res = new ArrayList<>();
        for (Jogo j : jogos) {
            if (j.getEquipaCasa().getNome().equals(team) || j.getEquipaFora().getNome().equals(team)) {
                res.add(j.clone());
            }
        }
        return res;
    }

    public String getJogosWith(String team){
        List<Jogo> jogosTeam = new ArrayList<>();
        List<Jogo> jogosTotal = new ArrayList<>();
        for(LocalDate d: jogos.keySet()){
            jogosTotal.addAll(jogos.get(d));
        }
        jogosTeam = filterJogosEquipa(jogosTotal,team);
        jogosTeam.sort(Comparator.comparing(Jogo::getData));

        StringBuilder sb = new StringBuilder();
        for(Jogo j: jogosTeam){
            sb.append(j.toStringSimples() + "\n");
        }

        return sb.toString();
    }

    public String printConstituicao(String minhaEquipa) {
        return this.equipas.get(minhaEquipa).getConstituicao().toStringPrintable();
        //for(int i : equipas.get(minhaEquipa).getJogadores().keySet()){
        //    System.out.println(i + " -> "+equipas.get(minhaEquipa).getJogadores().get(i).getClass().getName());
        //}
        //return "";
    }

    public Jogo fromLineJogo(String input){
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");
        List<Integer> jc = new ArrayList<>();
        List<Integer> jf = new ArrayList<>();
        Map<Integer, Integer> subsC = new HashMap<>();
        Map<Integer, Integer> subsF = new HashMap<>();
        for (int i = 5; i < 16; i++){
            jc.add(Integer.parseInt(campos[i]));
        }
        for (int i = 16; i < 19; i++){
            String[] sub = campos[i].split("->");
            if(jc.contains(Integer.parseInt(sub[0]))){
                subsC.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
            }
        }
        for (int i = 19; i < 30; i++){
            jf.add(Integer.parseInt(campos[i]));
        }
        for (int i = 30; i < 33; i++){
            String[] sub = campos[i].split("->");
            if(jf.contains(Integer.parseInt(sub[0]))){
                subsF.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
            }
        }

        return new Jogo(this.getEquipasCloned().get(campos[0]), this.getEquipasCloned().get(campos[1]), Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                jc, subsC, jf, subsF);
    }
}
