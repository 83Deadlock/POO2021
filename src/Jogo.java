import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jogo {
    private Equipa equipaCasa;
    private Constituicao equipaCasaSquad;
    //private int subsCasa;
    private Equipa equipaFora;
    private Constituicao equipaForaSquad;
    //private int subsFora;
    private Resultado score;
    //private int estado; // 1- Por iniciar || 2- A decorrer || 0- Terminado
    private int scoreCasa;
    private int scoreFora;
    private LocalDate data;
    private List<Integer> jogadoresCasa;
    private Map<Integer,Integer> subsCasa;
    private List<Integer> jogadoresFora;
    private Map<Integer,Integer> subsFora;


    public Jogo(Equipa casa, Equipa fora) {
        this.equipaCasa = new Equipa(casa);
        this.equipaCasaSquad = this.equipaCasa.getConstituicao();
        //this.subsCasa = 5;
        this.equipaFora = new Equipa(fora);
        this.equipaForaSquad = this.equipaFora.getConstituicao();
        //this.subsFora = 5;
        this.score = new Resultado();
        //this.estado = 1;
    }

    public Jogo(String equipaCasa, String equipaFora, int scoreCasa, int scoreFora, LocalDate data, List<Integer> jc, Map<Integer,Integer> subsC, List<Integer> jf, Map<Integer,Integer> subsF) {
        this.equipaCasa = new Equipa(equipaCasa);
        //this.equipaCasaSquad = this.equipaCasa.getConstituicao();
        //this.subsCasa = 5;
        this.equipaFora = new Equipa(equipaFora);
        //this.equipaForaSquad = this.equipaFora.getConstituicao();
        //this.subsFora = 5;
        this.score = new Resultado();
        //this.estado = 1;
        this.scoreCasa = scoreCasa;
        this.scoreFora = scoreFora;
        this.data = data;
        this.jogadoresCasa = jc;
        this.subsCasa = subsC;
        this.jogadoresFora = jf;
        this.subsFora = subsF;
    }

    public Equipa getEquipaCasa() {
        return equipaCasa;
    }

    public void setEquipaCasa(Equipa equipaCasa) {
        this.equipaCasa = equipaCasa;
    }

    public Constituicao getEquipaCasaSquad() {
        return equipaCasaSquad;
    }

    public void setEquipaCasaSquad(Constituicao equipaCasaSquad) {
        this.equipaCasaSquad = equipaCasaSquad;
    }

    public Map<Integer, Integer> getSubsCasa() {
        return subsCasa;
    }

    public void setSubsCasa(Map<Integer, Integer> subsCasa) {
        this.subsCasa = subsCasa;
    }

    public Map<Integer, Integer> getSubsFora() {
        return subsFora;
    }

    public void setSubsFora(Map<Integer, Integer> subsFora) {
        this.subsFora = subsFora;
    }

    public Equipa getEquipaFora() {
        return equipaFora;
    }

    public void setEquipaFora(Equipa equipaFora) {
        this.equipaFora = equipaFora;
    }

    public Constituicao getEquipaForaSquad() {
        return equipaForaSquad;
    }

    public void setEquipaForaSquad(Constituicao equipaForaSquad) {
        this.equipaForaSquad = equipaForaSquad;
    }


    public Resultado getScore() {
        return score;
    }

    public void setScore(Resultado score) {
        this.score = score;
    }


    public void goloCasa(){
        this.score.goloEqCasa();
    }

    public void goloFora(){
        this.score.goloEqFora();
    }


    SimulacaoJogo sj = new SimulacaoJogo(this);

    public void startGame() throws InterruptedException {
        sj.start();
    }



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
            subsC.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        for (int i = 19; i < 30; i++){
            jf.add(Integer.parseInt(campos[i]));
        }
        for (int i = 30; i < 33; i++){
            String[] sub = campos[i].split("->");
            subsF.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        return new Jogo(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                jc, subsC, jf, subsF);
    }
}
