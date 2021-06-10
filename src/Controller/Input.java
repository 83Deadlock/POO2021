package Controller;
import View.Apresentacao;

import java.time.DateTimeException;
import java.time.LocalDate;
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

    /**
     *  Método que lê um double
     *
     * @param a         Apresentação
     * @param message   String que representa a mensagem a apresentar
     * @param min       Valor máximo do Double
     * @param max       Valor mínimo do Double
     * @return          Double lido
     */
    public double lerDouble(Apresentacao a, String message,int min,int max){
        Scanner s = new Scanner(System.in);
        double n = -1;

        do{
            a.printMessage(message);
            try {
                String line = s.nextLine();
                n = Double.parseDouble(line);
            } catch (NumberFormatException nfe) {
                a.printMessage(nfe.getMessage());
                n = -1;
            }
        } while (n < min || n > max);

        return n;
    }

    /**
     *  Método que lê uma data
     *
     * @param a         Apresentação
     * @param message   String que representa a mensagem a apresentar
     * @return          Data lida
     */
    public LocalDate lerData(Apresentacao a, String message){
        Scanner s = new Scanner(System.in);
        boolean val = true;
        LocalDate data = null;
        String[] date;

        do{
            a.printMessage(message);
            try {
                date = s.nextLine().split("-",3);
                data = LocalDate.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0]));
                val = false;
            } catch (DateTimeException dte ) {
                a.printMessage("Data inválida");
            }catch (NumberFormatException ignored){
            }
        } while (val);

        return data;
    }
}
