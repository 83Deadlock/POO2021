package Controller;
import View.Apresentacao;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class Input {

    public int lerInt(){
        Scanner s = new Scanner(System.in);
        int r = s.nextInt();
        s.nextLine();
        return r;
    }

    public String lerLinha() {
        Scanner s = new Scanner(System.in);

        return s.nextLine();
    }
}
