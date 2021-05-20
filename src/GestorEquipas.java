import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class GestorEquipas {
    private Map<String,Equipa> equipas;

    public GestorEquipas(){
        this.equipas = new HashMap<>();
    }

    public GestorEquipas (Map<String,Equipa> map){
        Map<String,Equipa> novo = map.entrySet().stream().collect(Collectors.toMap(par-> par.getKey(), par-> par.getValue().clone()));
    }

    public GestorEquipas (GestorEquipas ge){
        setEquipas(ge.getEquipas());
    }

    public Map<String,Equipa> getEquipas(){
        return this.equipas.entrySet().stream().collect(Collectors.toMap(par -> par.getKey(), par -> par.getValue().clone()));
    }

    public void setEquipas(Map<String,Equipa> equipas) {
        this.equipas = new HashMap<>();
        equipas.entrySet().forEach(e -> this.equipas.put(e.getKey(),e.getValue().clone()));
    }

    public String toString() {
        return "GestorEquipas{" +
                "equipas=" + equipas +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GestorEquipas that = (GestorEquipas) o;
        return Objects.equals(equipas, that.equipas);
    }

    public GestorEquipas clone(){
        return new GestorEquipas(this);
    }

    public void adicionaEquipa(Equipa e){
        equipas.put(e.getNome(),e.clone());
    }

    public void removeEquipa(Equipa e){
        equipas.remove(e.getNome(),e);
    }
}
