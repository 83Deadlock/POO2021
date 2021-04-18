package Geral;

import java.time.LocalDate;

public class Helper {
    public static LocalDate dateFromString(String date) {
        String[] argumentos = date.split("-");
        int ano = Integer.parseInt(argumentos[0]);
        int mes = Integer.parseInt(argumentos[1]);
        int dia = Integer.parseInt(argumentos[2]);
        return LocalDate.of(ano, mes, dia);
    }
}
