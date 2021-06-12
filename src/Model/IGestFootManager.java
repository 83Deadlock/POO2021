package Model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IGestFootManager {
    Map<String, Equipa> getEquipasCloned();

    // Não cloneia as Equipas, usar com cuidado
    Map<String, Equipa> getEquipas();

    void setEquipas(Map<String, Equipa> equipas);

    Map<LocalDate, List<Jogo>> getJogos();

    void setJogos(Map<LocalDate, List<Jogo>> jogos);

    void adicionaEquipa(Equipa e);

    void adicionaJogo(Jogo j);

    void setMinhaEquipa(String minhaEquipa);

    String getMinhaEquipa();

    Map<String, Integer> getNomesEquipas();

    List<Jogo> filterJogosEquipa(List<Jogo> jogos, String team);

    String getJogosWith(String team);

    String printConstituicao(String minhaEquipa);
}
