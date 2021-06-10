package Files;

import Model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    public void parse(GestFootManager gfm) throws EquipaInvalidaException {
        List<String> linhas = lerFicheiro("input_files/logs.txt");


        //Map<Integer, Jogador> jogadores = new HashMap<>(); //numero, jogador

        Equipa ultima = null;
        Jogador j = null;

        String[] linhaPartida;

        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]) {
                case "Equipa":
                    Equipa e = Equipa.fromLine(linhaPartida[1]);
                    if(ultima != null){
                        ultima.calcOverall();
                        ultima.fazConstituicao();
                        gfm.adicionaEquipa(ultima);
                    }
                    ultima = e;

                    break;

                case "Guarda-Redes":
                    j = GuardaRedes.fromLine(linhaPartida[1],ultima.getNome());
                    //jogadores.put(j.getNumeroCamisola(), j);
                    if (ultima == null)
                        throw new EquipaInvalidaException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Defesa":
                    j = Defesa.fromLine(linhaPartida[1],ultima.getNome());
                    //jogadores.put(j.getNumeroCamisola(), j);
                    if (ultima == null)
                        throw new EquipaInvalidaException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Medio":
                    j = Medio.fromLine(linhaPartida[1],ultima.getNome());
                    //jogadores.put(j.getNumeroCamisola(), j);
                    if (ultima == null)
                        throw new EquipaInvalidaException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Lateral":
                    j = Lateral.fromLine(linhaPartida[1],ultima.getNome());
                    //jogadores.put(j.getNumeroCamisola(), j);
                    if (ultima == null)
                        throw new EquipaInvalidaException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Avancado":
                    j = Avancado.fromLine(linhaPartida[1],ultima.getNome());
                    //jogadores.put(j.getNumeroCamisola(), j);
                    if (ultima == null)
                        throw new EquipaInvalidaException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Jogo":
                    if(ultima != null){
                        ultima.calcOverall();
                        gfm.adicionaEquipa(ultima);
                    }
                    Jogo jo = fromLineJogo(linhaPartida[1],gfm);

                    gfm.adicionaJogo(jo);
                    break;
                default:
                    throw new EquipaInvalidaException();

            }
        }
    }

    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8);
        }
        catch(IOException exc) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public Jogo fromLineJogo(String input, GestFootManager gfm){
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");
        List<Integer> jc = new ArrayList<>();
        List<Integer> jf = new ArrayList<>();
        Map<Integer, Integer> subsC = new HashMap<>();
        Map<Integer, Integer> subsF = new HashMap<>();
        for (int i = 5; i < 16; i++){
            jc.add(Integer.parseInt(campos[i]));
        }
        for (int i = 16; i < 19; i++){
            String[] sub = campos[i].split("->");
            if(jc.contains(Integer.parseInt(sub[0]))){
                subsC.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
            }
        }
        for (int i = 19; i < 30; i++){
            jf.add(Integer.parseInt(campos[i]));
        }
        for (int i = 30; i < 33; i++){
            String[] sub = campos[i].split("->");
            if(jf.contains(Integer.parseInt(sub[0]))){
                subsF.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
            }
        }

        return new Jogo(gfm.getEquipasCloned().get(campos[0]), gfm.getEquipasCloned().get(campos[1]), Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                jc, subsC, jf, subsF);
    }
}
