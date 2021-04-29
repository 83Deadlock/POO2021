import java.util.List;

public class Jogo {
    private Equipa equipaCasa;
    private Constituicao equipaCasaSquad;
    private int subsCasa;
    private Equipa equipaFora;
    private Constituicao equipaForaSquad;
    private int subsFora;
    private Resultado score;
    private SimulacaoJogo sj = new SimulacaoJogo(this);

    public Jogo(Equipa casa, Equipa fora) {
        this.equipaCasa = new Equipa(casa);
        this.equipaCasaSquad = this.equipaCasa.getConstituicao();
        this.subsCasa = 5;
        this.equipaFora = new Equipa(fora);
        this.equipaForaSquad = this.equipaFora.getConstituicao();
        this.subsFora = 5;
        this.score = new Resultado();
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

    public int getSubsCasa() {
        return subsCasa;
    }

    public void setSubsCasa(int subsCasa) {
        this.subsCasa = subsCasa;
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

    public int getSubsFora() {
        return subsFora;
    }

    public void setSubsFora(int subsFora) {
        this.subsFora = subsFora;
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

    public void startGame() throws InterruptedException {
        sj.start();
    }

}
