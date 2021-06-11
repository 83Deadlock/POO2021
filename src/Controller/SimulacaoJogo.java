package Controller;

import View.Apresentacao;

import static java.lang.Thread.sleep;

public class SimulacaoJogo {
    private String casa;
    private int ratingCasa;
    private int golosCasa;
    private String fora;
    private int ratingFora;
    private int golosFora;
    Apresentacao apresentacao;

    public SimulacaoJogo(String c, int rc, String f, int rf){
        this.casa = c;
        this.ratingCasa = rc;
        this.golosCasa = 0;
        this.fora = f;
        this.ratingFora = rf;
        this.golosFora = 0;
        this.apresentacao = new Apresentacao();
    }

    public void simula() throws InterruptedException {
        int total = ratingCasa + ratingFora;
        int tickCasa = 0;
        int tickFora = 0;
        double chance = 0;
        for(int tickAtual = 0; tickAtual < total; tickAtual++){
            if(tickAtual == total/2){
                apresentacao.printBreak();
            }else if(tickAtual % 2 == 0){
                if(tickAtual / 2 >= ratingCasa);
                else{
                    chance = Math.random();
                    if(chance <= 0.04){
                        golosCasa += 1;
                        apresentacao.printGoloCasa(tickAtual*90/total,casa,golosCasa,fora,golosFora);
                    } else if (chance > 0.04 && chance <= 0.045){
                        apresentacao.penalti(tickAtual*90/total,fora);
                        sleep(2000);
                        chance = Math.random();
                        if(chance >= 0.5){
                            golosCasa += 1;
                            apresentacao.printGoloCasa(tickAtual*90/total,casa,golosCasa,fora,golosFora);
                        }
                    } else if (chance > 0.045 && chance <= 0.09){
                        apresentacao.prinCanto(tickAtual*90/total,casa);
                    }
                }
            } else {
                if(tickAtual / 2 >= ratingFora);
                else{
                    chance = Math.random();
                    if(chance <= 0.04){
                        golosFora += 1;
                        apresentacao.printGoloFora(tickAtual*90/total,casa,golosCasa,fora,golosFora);
                    } else if (chance > 0.04 && chance <= 0.045){
                        apresentacao.penalti(tickAtual*90/total,fora);
                        sleep(2000);
                        chance = Math.random();
                        if(chance >= 0.5){
                            golosCasa += 1;
                            apresentacao.printGoloFora(tickAtual*90/total,casa,golosCasa,fora,golosFora);
                        }
                    } else if (chance > 0.045 && chance <= 0.09){
                        apresentacao.prinCanto(tickAtual*90/total,fora);
                    }
                }
            }
            if(tickCasa >= ratingCasa);
            if(tickFora >= ratingFora);

            sleep(200);

        }

    }

    public int getGolosCasa() {
        return golosCasa;
    }

    public void setGolosCasa(int golosCasa) {
        this.golosCasa = golosCasa;
    }

    public int getGolosFora() {
        return golosFora;
    }

    public void setGolosFora(int golosFora) {
        this.golosFora = golosFora;
    }
}
