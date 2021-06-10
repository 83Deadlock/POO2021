package Files;

import Model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public void parse(GestFootManager gfm) throws EquipaInvalidaException {
        List<String> linhas = lerFicheiro("input_files/logs.txt");


        //Map<Integer, Model.Jogador> jogadores = new HashMap<>(); //numero, jogador

        Equipa ultima = null;
        Jogador j = null;

        String[] linhaPartida;

        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]) {
                case "Model.Equipa":
                    Equipa e = Equipa.fromLine(linhaPartida[1]);
                    if(ultima != null){
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

                case "Model.Defesa":
                    j = Defesa.fromLine(linhaPartida[1],ultima.getNome());
                    //jogadores.put(j.getNumeroCamisola(), j);
                    if (ultima == null)
                        throw new EquipaInvalidaException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Model.Medio":
                    j = Medio.fromLine(linhaPartida[1],ultima.getNome());
                    //jogadores.put(j.getNumeroCamisola(), j);
                    if (ultima == null)
                        throw new EquipaInvalidaException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Model.Lateral":
                    j = Lateral.fromLine(linhaPartida[1],ultima.getNome());
                    //jogadores.put(j.getNumeroCamisola(), j);
                    if (ultima == null)
                        throw new EquipaInvalidaException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Model.Avancado":
                    j = Avancado.fromLine(linhaPartida[1],ultima.getNome());
                    //jogadores.put(j.getNumeroCamisola(), j);
                    if (ultima == null)
                        throw new EquipaInvalidaException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Model.Jogo":
                    Jogo jo = Jogo.fromLine(linhaPartida[1]);
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
}
