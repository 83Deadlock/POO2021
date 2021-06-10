package Controller;
import View.Apresentacao;

import java.util.Scanner;

public class Input {
    /**
     *  Método que lê sim ou não
     *
     * @param a         Apresentação
     * @param message   String que representa a mensagem a apresentar
     * @return          true ou false dependendo do imput
     */
    public boolean lerSN(Apresentacao a, String message){
        Scanner s = new Scanner(System.in);
        String line;

        do{
            a.printMessage(message);
            line = s.nextLine();
        } while (!line.toUpperCase().equals("S") && !line.toUpperCase().equals("N"));

        return line.toUpperCase().equals("S");
    }
}
