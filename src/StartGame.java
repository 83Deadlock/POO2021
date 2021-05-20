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

        GuardaRedes j = (GuardaRedes) model.getGestorEquipas().getEquipas().get("Mahler Athletic").getJogadores().get(36);

        System.out.println(j.toString());

    }
}