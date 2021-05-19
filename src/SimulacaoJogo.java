public class SimulacaoJogo {

    private int estado;
    private Jogo j;

    public SimulacaoJogo(Jogo j){
        this.j = j;
        this.estado = 1; // 1- Por iniciar || 2- A decorrer || 0- Terminado
    }
    public void start() throws InterruptedException {
        setEstado(2);
        int time = 90;
        System.out.println("O jogo entre "+j.getEquipaCasa().getNome()+" e "+j.getEquipaFora().getNome()+" começará dentro de poucos segundos!");
        Thread.sleep(2500);
        for(int i=0; i<=time; i++){
            System.out.print("Minuto"+i+": ");
            double r = Math.random()*100.0;
            if(r>=97){
                j.goloCasa();
                System.out.println("GOLOOOOO do "+j.getEquipaCasa().getNome()+" *"+j.getScore().toString()+"*");
            }
            else if(r>=92 && r<97){
                j.goloFora();
                System.out.println("GOLO do "+j.getEquipaFora().getNome()+" *"+j.getScore().toString()+"*");
            }
            else if(r>=85 && r<92){
                System.out.println("Grande oportunidade da equipa da casa, mas não foi golo");
            }
            else if(r>=80 && r<85){
                System.out.println("E a equipa visitante manda um tiro à trave");
            }
            else if(r>=79 && r<80){
                System.out.println("O árbitro marca penalti para o "+j.getEquipaCasa().getNome()+"!!!");
                Thread.sleep(2500);
                System.out.println("Será golo?");
                Thread.sleep(2500);
                System.out.println("O jogador parte para a bola, remata e...");
                Thread.sleep(2000);
                double pen = Math.random()*100.0;
                if(pen<90) {
                    j.goloCasa();
                    System.out.println("GOOOOOOOOOOOOOOLOOOOOOOOOOO DO "+j.getEquipaCasa().getNome()+" *"+j.getScore().toString()+"*");
                }
                else{
                    System.out.println("Que oportunidade perdida... O jogador manda a bola ao poste!");
                }
            }
            else if(r>=78 && r<79){
                System.out.println("O árbitro marca penalti para o "+j.getEquipaCasa().getNome()+"!!!");
                Thread.sleep(2500);
                System.out.println("Será golo?");
                Thread.sleep(2500);
                System.out.println("O jogador parte para a bola, remata e...");
                Thread.sleep(2000);
                double pen = Math.random()*100.0;
                if(pen<90) {
                    j.goloFora();
                    System.out.println("GOOOOOOOOOOOOOOLOOOOOOOOOOO DO "+j.getEquipaCasa().getNome()+" *"+j.getScore().toString()+"*");
                }
                else{
                    System.out.println("Que oportunidade perdida... O jogador manda a bola ao poste!");
                }
            }
            else{
                System.out.println("-------------------");
            }
            if(i==45){
                System.out.println("INTERVALO! O resultado está "+j.getScore().toString()+".");
                Thread.sleep(2500);
            }
            Thread.sleep(500);
        }
        setEstado(0);
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
