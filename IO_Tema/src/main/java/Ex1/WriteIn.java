package Ex1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by lluca on 07-Nov-16.
 */
public class WriteIn {
    public void Scrie(String numeFis, String text) throws IOException {
        File file = new File(numeFis);
        FileWriter out = new FileWriter(file);
        out.write(text);
        out.close();


    }
}
