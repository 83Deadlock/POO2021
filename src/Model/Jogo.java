package Model;

import Controller.SimulacaoJogo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Jogo implements Serializable {
    private Equipa equipaCasa;
    private Equipa equipaFora;
    private int scoreCasa;
    private int scoreFora;
    private LocalDate data;
    private List<Integer> jogadoresCasa;
    private Map<Integer,Integer> subsCasa; // Jogador que sai - key, jogador que entra - value
    private List<Integer> jogadoresFora;
    private Map<Integer,Integer> subsFora; // Jogador que sai - key, jogador que entra - value

    public Jogo(Equipa equipaCasa, Equipa equipaFora){
        this.equipaCasa = equipaCasa;
        this.equipaFora = equipaFora;
        this.scoreCasa = 0;
        this.scoreFora = 0;
        this.data = LocalDate.now();
        this.jogadoresCasa = equipaCasa.getListTitulares();
        this.subsCasa = equipaCasa.getMapSubs(this.jogadoresCasa);
        this.jogadoresFora = equipaFora.getListTitulares();
        this.subsFora = equipaFora.getMapSubs(this.jogadoresCasa);
    }

    public Jogo(Equipa equipaCasa, Equipa equipaFora, int scoreCasa, int scoreFora, LocalDate data, List<Integer> jc, Map<Integer,Integer> subsC, List<Integer> jf, Map<Integer,Integer> subsF) {
        this.equipaCasa = equipaCasa;
        this.equipaFora = equipaFora;
        this.scoreCasa = scoreCasa;
        this.scoreFora = scoreFora;
        this.data = data;
        this.jogadoresCasa = new ArrayList<>(jc);
        this.subsCasa = new HashMap<>(subsC);
        this.jogadoresFora = new ArrayList<>(jf);
        this.subsFora = new HashMap<>(subsF);
    }

    public Jogo(Jogo j){
        this.equipaCasa = j.getEquipaCasa();
        this.equipaFora = j.getEquipaFora();
        this.scoreCasa = j.getScoreCasa();
        this.scoreFora = j.getScoreFora();
        this.data = j.getData();
        this.jogadoresCasa = j.getJogadoresCasa();
        this.subsCasa = j.getSubsCasa();
        this.jogadoresFora = j.getJogadoresFora();
        this.subsFora = j.getSubsFora();
    }

    public Equipa getEquipaCasa() {
        return this.equipaCasa.clone();
    }

    public void setEquipaCasa(Equipa equipaCasa) {
        this.equipaCasa = equipaCasa.clone();
    }

    public Equipa getEquipaFora() {
        return this.equipaFora.clone();
    }

    public void setEquipaFora(Equipa equipaFora) {
        this.equipaFora = equipaFora.clone();
    }

    public int getScoreCasa() {
        return scoreCasa;
    }

    public void setScoreCasa(int scoreCasa) {
        this.scoreCasa = scoreCasa;
    }

    public int getScoreFora() {
        return scoreFora;
    }

    public void setScoreFora(int scoreFora) {
        this.scoreFora = scoreFora;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Integer> getJogadoresCasa(){
        return new ArrayList<>(this.jogadoresCasa);
    }

    public void setJogadoresCasa(List<Integer> jc){
        this.jogadoresCasa = new ArrayList<>(jc);
    }

    public Map<Integer, Integer> getSubsCasa() {
        return new HashMap<>(this.subsCasa);
    }

    public void setSubsCasa(Map<Integer, Integer> subsCasa) {
        this.subsCasa = new HashMap<>(subsCasa);
    }

    public List<Integer> getJogadoresFora(){
        return new ArrayList<>(this.jogadoresFora);
    }

    public void setJogadoresFora(List<Integer> jf){
        this.jogadoresFora = new ArrayList<>(jf);
    }

    public Map<Integer, Integer> getSubsFora() {
        return new HashMap<>(subsFora);
    }

    public void setSubsFora(Map<Integer, Integer> subsFora) {
        this.subsFora = new HashMap<>(subsFora);
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Data = " + this.data.toString() + "\n");
        sb.append(this.equipaCasa.getNome() + " " + scoreCasa + "-");
        sb.append(this.scoreFora + " " + this.equipaFora.getNome() + "\n");
        sb.append("Titulares Casa = " + this.listTitulares(this.jogadoresCasa) + "\n");
        sb.append("Substituições Casa = " + this.listSubs(this.subsCasa) + "\n");
        sb.append("Titulares Fora = " + this.listTitulares(this.jogadoresFora) + "\n");
        sb.append("Substituições Fora = " + this.listSubs(this.subsFora) + "\n");

        return sb.toString();
    }

    public Jogo clone(){
        return new Jogo(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogo jogo = (Jogo) o;
        return scoreCasa == jogo.scoreCasa && scoreFora == jogo.scoreFora && Objects.equals(equipaCasa, jogo.equipaCasa) && Objects.equals(equipaFora, jogo.equipaFora) && Objects.equals(data, jogo.data) && Objects.equals(jogadoresCasa, jogo.jogadoresCasa) && Objects.equals(subsCasa, jogo.subsCasa) && Objects.equals(jogadoresFora, jogo.jogadoresFora) && Objects.equals(subsFora, jogo.subsFora);
    }

    /*Controller.SimulacaoJogo sj = new Controller.SimulacaoJogo(this);

    public void startGame() throws InterruptedException {
        sj.start();
    }*/

    public String listTitulares(List<Integer> jogadores){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 10; i++){
            sb.append(jogadores.get(i) + ",");
        }
        sb.append(jogadores.get(10));
        return sb.toString();
    }

    public String listSubs(Map<Integer,Integer> subs){
        StringBuilder sb = new StringBuilder();
        int size = subs.keySet().size();
        int qnts = 0;
        for(Integer i : subs.keySet()){
            qnts++;
            StringBuilder aux = new StringBuilder();
            aux.append(i);
            aux.append("->");
            aux.append(subs.get(i));
            sb.append(aux.toString());
            if(qnts != size){
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public String toStringSimples() {
        StringBuilder sb = new StringBuilder();
        sb.append("Data = " + this.data.toString() + "\n");
        sb.append(this.equipaCasa.getNome() + " " + scoreCasa + "-");
        sb.append(this.scoreFora + " " + this.equipaFora.getNome() + "\n");
        return sb.toString();
    }

    public void simulacaoRapida(){
        int ratCasa = equipaCasa.calcOverallGame(jogadoresCasa,subsCasa);
        int golosCasa = 0;
        int ratFora = equipaFora.calcOverallGame(jogadoresFora,subsFora);
        int golosFora = 0;

        int winner = 0; //1 casa, 0 empate, -1 fora
        double chance = 0;
        for(int i = 0; i < ratCasa; i++){
            chance = Math.random();
            if(chance <= 0.04){
                golosCasa += 1;
            }
        }
        for(int i = 0; i < ratFora; i++){
            chance = Math.random();
            if(chance <= 0.04){
                golosFora += 1;
            }
        }
        this.setScoreCasa(golosCasa);
        this.setScoreFora(golosFora);
    }

    public SimulacaoJogo simulacaoCompleta(){
        int ratCasa = equipaCasa.calcOverallGame(jogadoresCasa,subsCasa);
        int golosCasa = 0;
        int ratFora = equipaFora.calcOverallGame(jogadoresFora,subsFora);
        int golosFora = 0;
        return new SimulacaoJogo(equipaCasa.getNome(), ratCasa, equipaFora.getNome(), ratFora);
    }
}
