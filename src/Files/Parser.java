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

    public void parse(GestFootManager gfm) throws FicheiroInvalidoException {
        List<String> linhas = lerFicheiro("input_files/logs.txt");

        Equipa ultima = null;
        Jogador j = null;

        String[] linhaPartida;

        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]) {
                case "Equipa":
                    Equipa e = Model.Equipa.fromLine(linhaPartida[1]);
                    if(ultima != null){
                        ultima.calcOverall();
                        ultima.fazConstituicao();
                        gfm.adicionaEquipa(ultima);
                    }
                    ultima = e;

                    break;

                case "Guarda-Redes":
                    j = GuardaRedes.fromLine(linhaPartida[1],ultima.getNome());
                    if (ultima == null)
                        throw new FicheiroInvalidoException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Defesa":
                    j = Defesa.fromLine(linhaPartida[1],ultima.getNome());
                    if (ultima == null)
                        throw new FicheiroInvalidoException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Medio":
                    j = Medio.fromLine(linhaPartida[1],ultima.getNome());
                    if (ultima == null)
                        throw new FicheiroInvalidoException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Lateral":
                    j = Lateral.fromLine(linhaPartida[1],ultima.getNome());
                    if (ultima == null)
                        throw new FicheiroInvalidoException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Avancado":
                    j = Avancado.fromLine(linhaPartida[1],ultima.getNome());
                    if (ultima == null)
                        throw new FicheiroInvalidoException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Jogo":
                    if(ultima != null){
                        ultima.calcOverall();
                        gfm.adicionaEquipa(ultima);
                    }
                    Jogo jo = gfm.fromLineJogo(linhaPartida[1]);

                    gfm.adicionaJogo(jo);
                    break;
                default:
                    throw new FicheiroInvalidoException();

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
