import java.util.List;

public class Jogo {
    private Equipa equipaCasa;
    private Constituicao equipaCasaSquad;
    private int subsCasa;
    private Equipa equipaFora;
    private Constituicao equipaForaSquad;
    private int subsFora;
    private Resultado score;
    private int estado; // 1- Por iniciar || 2- A decorrer || 0- Terminado

    public Jogo(Equipa casa, Equipa fora) {
        this.equipaCasa = new Equipa(casa);
        this.equipaCasaSquad = this.equipaCasa.getConstituicao();
        this.subsCasa = 5;
        this.equipaFora = new Equipa(fora);
        this.equipaForaSquad = this.equipaFora.getConstituicao();
        this.subsFora = 5;
        this.score = new Resultado();
        this.estado = 1;
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

    public int getEstado() {
        return estado;
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

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void goloCasa(){
        this.score.goloEqCasa();
    }

    public void goloFora(){
        this.score.goloEqFora();
    }

    public void startGame() throws InterruptedException {
        setEstado(2);
        int time = 90;
        System.out.println("O jogo entre "+this.getEquipaCasa().getNome()+" e "+this.getEquipaFora().getNome()+" começará dentro de poucos segundos!");
        Thread.sleep(2500);
        for(int i=0; i<=time; i++){
            System.out.print("Minuto"+i+": ");
            double r = Math.random()*100.0;
            if(r>=97){
                this.goloCasa();
                System.out.println("GOLOOOOO do "+this.getEquipaCasa().getNome()+" *"+this.getScore().toString()+"*");
            }
            else if(r>=92 && r<97){
                this.goloFora();
                System.out.println("GOLO do "+this.getEquipaFora().getNome()+" *"+this.getScore().toString()+"*");
            }
            else if(r>=85 && r<92){
                System.out.println("Grande oportunidade da equipa da casa, mas não foi golo");
            }
            else if(r>=80 && r<85){
                System.out.println("E a equipa visitante manda um tiro à trave");
            }
            else if(r>=79 && r<80){
                System.out.println("O árbitro marca penalti para o "+this.getEquipaCasa().getNome()+"!!!");
                Thread.sleep(2500);
                System.out.println("Será golo?");
                Thread.sleep(2500);
                System.out.println("O jogador parte para a bola, remata e...");
                Thread.sleep(2000);
                double pen = Math.random()*100.0;
                if(pen<90) {
                    this.goloCasa();
                    System.out.println("GOOOOOOOOOOOOOOLOOOOOOOOOOO DO "+this.getEquipaCasa().getNome()+" *"+this.getScore().toString()+"*");
                }
                else{
                    System.out.println("Que oportunidade perdida... O jogador manda a bola ao poste!");
                }
            }
            else if(r>=78 && r<79){
                System.out.println("O árbitro marca penalti para o "+this.getEquipaCasa().getNome()+"!!!");
                Thread.sleep(2500);
                System.out.println("Será golo?");
                Thread.sleep(2500);
                System.out.println("O jogador parte para a bola, remata e...");
                Thread.sleep(2000);
                double pen = Math.random()*100.0;
                if(pen<90) {
                    this.goloFora();
                    System.out.println("GOOOOOOOOOOOOOOLOOOOOOOOOOO DO "+this.getEquipaCasa().getNome()+" *"+this.getScore().toString()+"*");
                }
                else{
                    System.out.println("Que oportunidade perdida... O jogador manda a bola ao poste!");
                }
            }
            else{
                System.out.println("-------------------");
            }
            if(i==45){
                System.out.println("INTERVALO! O resultado está "+this.getScore().toString()+".");
                Thread.sleep(2500);
            }
            Thread.sleep(500);
        }
        setEstado(0);
    }
}
