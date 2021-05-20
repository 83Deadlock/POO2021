public class Model {
    private GestorEquipas gestorEquipas;
    private GestorJogos gestorJogos;

    public Model(){
        this.gestorEquipas = new GestorEquipas();
        this.gestorJogos = new GestorJogos();
    }

    public GestorEquipas getGestorEquipas() {
        return gestorEquipas.clone();
    }

    public void setGestorEquipas(GestorEquipas gestorEquipas) {
        this.gestorEquipas = gestorEquipas.clone();
    }

    public GestorJogos getGestorJogos() {
        return gestorJogos.clone();
    }

    public void setGestorJogos(GestorJogos gestorJogos) {
        this.gestorJogos = gestorJogos;
    }
}
