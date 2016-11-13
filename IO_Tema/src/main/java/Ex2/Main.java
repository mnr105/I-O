package Ex2;

import java.io.IOException;

/**
 * Created by lluca on 07-Nov-16.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String numeFisier = "C:\\Users\\lluca\\Documents\\Graduates\\iO\\IO_Tema\\src\\main\\java\\Ex2\\text2.txt";
        String numeFisier2 = "C:\\Users\\lluca\\Documents\\Graduates\\iO\\IO_Tema\\src\\main\\java\\Ex2\\text1.txt";
        Utils ut = new Utils();
        String ceva = ut.citesteDinFisier(numeFisier2);
        ut.calculeazaScrie(numeFisier, ceva);
    }
}