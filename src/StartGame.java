import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartGame {
    public static void main(String[] args) throws IOException, InterruptedException, EquipaInvalidaException {
        Model model = new Model();

        Parser p = new Parser();
        p.parse();
        model.setGestorEquipas(p.getGe());
        model.setGestorJogos(p.getGj());


        Jogo jogo = model.getGestorJogos().getJogos().get(0);

        System.out.println(jogo.toString());

    }
}