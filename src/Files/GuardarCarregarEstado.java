package Files;

import Model.GestFootManager;

import java.io.*;

public class GuardarCarregarEstado {

    /**
     * Método que guarda os dados num ficheiro
     *
     * @param fileName  nome ficheiro
     * @param gfm       GestFootManager
     * @return          0 se guardou sem erros
     */
    public static int guardaDados(String fileName, GestFootManager gfm) {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(gfm);
            oos.flush();
            oos.close();
        }
        catch (FileNotFoundException e) {
            return 1;
        }
        catch (IOException e) {
            return 2;
        }

        return 0;
    }

    /**
     * Método que carrega os dados guardados num ficheiro
     *
     * @param fileName                  nome ficheiro
     * @return                          estrutura carregada
     * @throws IOException              controlo erros
     * @throws ClassNotFoundException   controlo erros
     */
    public static GestFootManager carregaDados(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(file);
        GestFootManager gfm = (GestFootManager) ois.readObject();
        ois.close();
        return gfm;
    }
}
