package Ex2;

import java.io.*;

/**
 * Created by lluca on 07-Nov-16.
 */
public class Utils {

    public String citesteDinFisier(String numeFis) throws IOException {
        File name = new File(numeFis);
        FileReader in = new FileReader(name);
        StringBuilder sb = new StringBuilder();
        int data = in.read();
        while (data != -1) {
            char dataChar = (char) data;
            sb.append(dataChar);
            data = in.read();
        }
        in.close();
        return sb.toString();
    }

    public void calculeazaScrie(String numeFis, String citit) throws IOException {
        String[] split = citit.split("\n");
        for (int i = 0; i < split.length; i++) {
            String z = split[i];
            String rez = intoarce(z);
            ScrieInFisier(numeFis, rez);
        }
    }

    public String intoarce(String bun) {
        System.out.println(bun);
        StringBuilder sbe = new StringBuilder();
        for (int i = (bun.length() - 1); i >= 0; i--) {
            sbe.append(bun.charAt(i));
        }
        return sbe.toString();
    }

    public void ScrieInFisier(String numeFis, String text) throws IOException {
        File file = new File(numeFis);
        FileWriter out = new FileWriter(file, true);
        out.append(text);
        out.flush();
    }
}
