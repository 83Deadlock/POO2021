import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartGame {
    public static void main(String[] args) throws IOException, InterruptedException, EquipaInvalidaException {
        /*
        File jogadores_file = new File("input_files/teste.txt");
        BufferedReader reader = new BufferedReader(new FileReader(jogadores_file));
        String currentLine = reader.readLine();
         */
        Parser.parse();


        /*
        List<Jogador> jogadores = new ArrayList<>();
        Map<String,Equipa> equipas = new HashMap<>();
        while (currentLine != null) {
            switch (currentLine.charAt(0)) {
                case 'E':
                    Equipa equip = Equipa.fromLine(currentLine);
                    equipas.put(equip.getNome(),equip);
                    break;
                case 'A':
                    Jogador avan = Avancado.fromLine(currentLine);
                    jogadores.add(avan);
                    equipas.get(avan.getEquipaAtual()).getJogadores().add(avan);
                    break;
                case 'D':
                    Jogador def = Defesa.fromLine(currentLine);
                    jogadores.add(def);
                    equipas.get(def.getEquipaAtual()).getJogadores().add(def);
                    break;
                case 'G':
                    Jogador redes = GuardaRedes.fromLine(currentLine);
                    jogadores.add(redes);
                    equipas.get(redes.getEquipaAtual()).getJogadores().add(redes);
                    break;
                case 'M':
                    Jogador med = Medio.fromLine(currentLine);
                    jogadores.add(med);
                    equipas.get(med.getEquipaAtual()).getJogadores().add(med);
                    break;
                case 'L':
                    Jogador lat = Lateral.fromLine(currentLine);
                    jogadores.add(lat);
                    equipas.get(lat.getEquipaAtual()).getJogadores().add(lat);
                    break;
            }
            currentLine = reader.readLine();
        }

         */

        /*
        for(Equipa e: equipas.values()){
            System.out.println(e.toString());
        }

        Jogo j1 = new Jogo(equipas.values().stream().filter(e -> e.getNome().equals("F.C. Porto")).findAny().get(),
                equipas.values().stream().filter(e -> e.getNome().equals("S.L. Benfica")).findAny().get());

        j1.startGame();

         */
    }
}