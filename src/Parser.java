import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    GestorEquipas ge;
    GestorJogos gj;

    public Parser(){
        this.ge = new GestorEquipas();
        this.gj = new GestorJogos();
    }

    public Parser(GestorEquipas ge, GestorJogos gj){
        this.ge = new GestorEquipas(ge);
        this.gj = new GestorJogos(gj);
    }

    public Parser(Parser p){
        this.ge = p.getGe();
        this.gj = p.getGj();
    }

    public GestorEquipas getGe() {
        return ge.clone();
    }

    public void setGe(GestorEquipas ge) {
        this.ge = ge.clone();
    }

    public GestorJogos getGj() {
        return gj.clone();
    }

    public void setGj(GestorJogos gj) {
        this.gj = gj.clone();
    }

    public void parse() throws EquipaInvalidaException {
        List<String> linhas = lerFicheiro("input_files/logs.txt");


        Map<Integer, Jogador> jogadores = new HashMap<>(); //numero, jogador
        List<Jogo> jogos = new ArrayList<>();

        Equipa ultima = null;
        Jogador j = null;

        String[] linhaPartida;

        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]) {
                case "Equipa":
                    Equipa e = Equipa.fromLine(linhaPartida[1]);
                    if(ultima != null){
                        this.ge.adicionaEquipa(ultima);
                    }
                    ultima = e;

                    break;

                case "Guarda-Redes":
                    j = GuardaRedes.fromLine(linhaPartida[1],ultima.getNome());
                    jogadores.put(j.getNumeroCamisola(), j);
                    if (ultima == null)
                        throw new EquipaInvalidaException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Defesa":
                    j = Defesa.fromLine(linhaPartida[1],ultima.getNome());
                    jogadores.put(j.getNumeroCamisola(), j);
                    if (ultima == null)
                        throw new EquipaInvalidaException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Medio":
                    j = Medio.fromLine(linhaPartida[1],ultima.getNome());
                    jogadores.put(j.getNumeroCamisola(), j);
                    if (ultima == null)
                        throw new EquipaInvalidaException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Lateral":
                    j = Lateral.fromLine(linhaPartida[1],ultima.getNome());
                    jogadores.put(j.getNumeroCamisola(), j);
                    if (ultima == null)
                        throw new EquipaInvalidaException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Avancado":
                    j = Avancado.fromLine(linhaPartida[1],ultima.getNome());
                    jogadores.put(j.getNumeroCamisola(), j);
                    if (ultima == null)
                        throw new EquipaInvalidaException(); //we need to insert the player into the team
                    ultima.adicionaJogador(j); //if no team was parsed previously, file is not well-formed
                    break;

                case "Jogo":
                    Jogo jo = Jogo.fromLine(linhaPartida[1]);
                    this.gj.adicionaJogo(jo);
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
