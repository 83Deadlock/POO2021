import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StartGame {
    public static void main(String[] args) throws IOException {
        File jogadores_file = new File("input_files/teste.txt");
        BufferedReader reader = new BufferedReader(new FileReader(jogadores_file));
        String currentLine = reader.readLine();

        List<Jogador> jogadores = new ArrayList<>();
        while (currentLine != null) {
            switch (currentLine.charAt(0)) {
                case 'A':
                    Jogador avan = Avancado.fromLine(currentLine);
                    jogadores.add(avan);
                    break;
                case 'D':
                    Jogador def = Defesa.fromLine(currentLine);
                    jogadores.add(def);
                    break;
                case 'G':
                    Jogador redes = GuardaRedes.fromLine(currentLine);
                    jogadores.add(redes);
                    break;
                case 'M':
                    Jogador med = Medio.fromLine(currentLine);
                    jogadores.add(med);
                    break;
                case 'L':
                    Jogador lat = Lateral.fromLine(currentLine);
                    jogadores.add(lat);
                    break;
            }
            currentLine = reader.readLine();
        }
        System.out.println(jogadores);
    }
}