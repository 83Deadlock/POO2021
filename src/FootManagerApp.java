//import Files.Files.Parser;
import Controller.Interpretador;
import Files.FicheiroInvalidoException;
import Files.Parser;
import Model.*;
import View.Apresentacao;

import java.io.Serializable;

public class FootManagerApp implements Serializable{
    public static void main(String[] args) {
        GestFootManager gfm = new GestFootManager();    // Modelo
        Interpretador i;                                // Controlador
        Apresentacao a = new Apresentacao();            // Vista
        Parser parser = new Parser();                   // Parser dos Logs

        try {
            parser.parse(gfm);
        } catch (FicheiroInvalidoException eie){
            a.printMessage("Wrong Format for Input File.");
        }

        try{
            i = new Interpretador(a, gfm);
            i.interpretador();
        } catch (Exception e){
            e.printStackTrace();
        }

        //System.out.println(gfm.getEquipas().get("Mahler Athletic").getJogadores().get(36).toString());
        //System.out.println(gfm.getEquipas().get("Mahler Athletic").getJogadores().keySet());
    }
}
