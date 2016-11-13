package Ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by lluca on 07-Nov-16.
 */
public class ReadFrom {

    public int NumberOfApparences(String file, char searched, int start) throws IOException {
        File name = new File(file);
        FileReader in = new FileReader(name);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        int data = in.read();
        while (data != -1) {
            char dataChar = (char) data;
            sb.append(dataChar);
            data = in.read();
        }
        in.close();

        for (int i = start; i < sb.length(); i++) {
            if (sb.charAt(i) == searched)
                count = count + 1;
        }
        return count;
    }


    public void doarCiteste(String file) throws IOException {
        File name = new File(file);
        FileReader in = new FileReader(name);
        StringBuilder sb = new StringBuilder();
        int data = in.read();
        while (data != -1) {
            char dataChar = (char) data;
            sb.append(dataChar);
            data = in.read();
        }
        in.close();
        System.out.println(sb.toString());
    }
}
