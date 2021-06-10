package Model;

import java.util.Comparator;

public class JogadorComparatorOverall implements Comparator<Jogador> {

    public int compare(Jogador o1, Jogador o2) {
        return o1.getOverall()-o2.getOverall();
    }
}
