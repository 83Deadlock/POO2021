//import Files.Files.Parser;
import Controller.Interpretador;
import Files.Parser;
import Model.*;
import View.Apresentacao;

import java.io.IOException;
import java.io.Serializable;

public class FootManagerApp implements Serializable{
    public static void main(String[] args) {
        GestFootManager gfm = new GestFootManager();
        Interpretador i = new Interpretador();
        Apresentacao a = new Apresentacao();
        Parser parser = new Parser();

        try {
            parser.parse(gfm);
        } catch (EquipaInvalidaException eie){
            a.printMessage("Wrong Format for Input File.");
        }

        try{
            i.interpretador(gfm, a);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
