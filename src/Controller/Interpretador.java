package Controller;

import Model.GestFootManager;
import View.Apresentacao;

import java.io.IOException;
import java.util.Scanner;

public class Interpretador {
    private final Input in;

    public Interpretador(){
        in = new Input();
    }


    /**
     * Controlador geral do programa
     *
     * @param gfm                      GestFootManager
     * @param a                        Apresentação
     * @throws ClassNotFoundException  Erro
     * @throws IOException             Erro
     */
    public void interpretador(GestFootManager gfm, Apresentacao a) throws ClassNotFoundException, IOException{
        Scanner s = new Scanner(System.in);

        a.welcome();
        s.nextLine();
    }
}
