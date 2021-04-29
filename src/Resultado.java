public class Resultado {
    private int golosCasa;
    private int golosFora;

    public Resultado (){
        this.golosCasa = 0;
        this.golosFora = 0;
    }

    public Resultado (int c, int f){
        this.golosCasa = c;
        this.golosFora = f;
    }

    public Resultado (Resultado r){
        this.golosCasa = r.getGolosCasa();
        this.golosFora = r.getGolosFora();
    }

    public int getGolosCasa() {
        return golosCasa;
    }

    public int getGolosFora() {
        return golosFora;
    }

    public String toString() {
        return this.golosCasa + "-" + this.golosFora;
    }
}
