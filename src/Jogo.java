import java.time.LocalDate;
import java.util.*;

public class Jogo {
    private String equipaCasa;
    private String equipaFora;
    private int scoreCasa;
    private int scoreFora;
    private LocalDate data;
    private List<Integer> jogadoresCasa;
    private Map<Integer,Integer> subsCasa;
    private List<Integer> jogadoresFora;
    private Map<Integer,Integer> subsFora;

    public Jogo(){
        this.equipaCasa = "";
        this.equipaFora = "";
        this.scoreCasa = 0;
        this.scoreFora = 0;
        this.data = LocalDate.now();
        this.jogadoresCasa = new ArrayList<>();
        this.subsCasa = new HashMap<>();
        this.jogadoresFora = new ArrayList<>();
        this.subsFora = new HashMap<>();
    }

    public Jogo(String equipaCasa, String equipaFora, int scoreCasa, int scoreFora, LocalDate data, List<Integer> jc, Map<Integer,Integer> subsC, List<Integer> jf, Map<Integer,Integer> subsF) {
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

    public String getEquipaCasa() {
        return equipaCasa;
    }

    public void setEquipaCasa(String equipaCasa) {
        this.equipaCasa = equipaCasa;
    }

    public String getEquipaFora() {
        return equipaFora;
    }

    public void setEquipaFora(String equipaFora) {
        this.equipaFora = equipaFora;
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
        sb.append(this.equipaCasa + " " + scoreCasa + "-");
        sb.append(this.scoreFora + " " + this.equipaFora + "\n");
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

    /*SimulacaoJogo sj = new SimulacaoJogo(this);

    public void startGame() throws InterruptedException {
        sj.start();
    }*/

    public static Jogo fromLine(String input){
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

        return new Jogo(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                jc, subsC, jf, subsF);
    }

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
}
