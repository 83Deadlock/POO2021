package Geral;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StartGame {
    public static void main(String[] args) throws IOException {
        File jogadores_file = new File("input_files/teste.txt");
        BufferedReader reader = new BufferedReader(new FileReader(jogadores_file));
        String currentLine = reader.readLine();

        List<Jogador> jogadores = new ArrayList<>();
        while (currentLine != null) {
            switch (currentLine.charAt(0)) {
                case 'A':

                    break;
                case 'D':
                    break;
                case 'G':
                    break;
                case 'M':
                    break;
                case 'L':

                    break;
            }
        }
    }
}