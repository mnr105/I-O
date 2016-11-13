package Ex3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by lluca on 07-Nov-16.
 */
public class Ex3 {

    Constate c1 = new Constate();
    int count = 0;

    public void afiseazaListaDinFisier(String calea) throws IOException {
        //Aflam lista de chestii din prima cale
        count += 1;
        // System.out.println("Primu: " + count);
        File file = new File(calea);
        String[] fileNames = file.list();
        File[] filePath = file.listFiles();
        //avem un string de nume ale chestiilor si verificam daca fiecare chestie e directory
        for (int i = 0; i <= (fileNames.length - 1); i++) {
            // trecem in fisier prima componenta a vectorului de cai
            File caleaNoua = new File(filePath[i].toString());
            // daca e director il trecem in fis.text si intram in el
            if (caleaNoua.isDirectory()) {
                ScrieInFisier(c1.inAstaScriu, "\n");
                StringBuilder deTrimis = new StringBuilder();
                deTrimis.append(AddTabs(count) + fileNames[i].toString() + "\\");
                ScrieInFisier(c1.inAstaScriu, deTrimis.toString());
                afiseazaListaDinFisier(caleaNoua.toString());
                count--;
                // System.out.println("al 2-lea: " + count);
            } else {
                ScrieInFisier(c1.inAstaScriu, "\n");
                StringBuilder deTrimis = new StringBuilder();
                deTrimis.append(AddTabs(count) + fileNames[i].toString() + "\\");
                ScrieInFisier(c1.inAstaScriu, deTrimis.toString());
            }
        }
    }

    public StringBuilder AddTabs(int nrTabs) {
        StringBuilder sbTabs = new StringBuilder();
        for (int i = 0; i <= nrTabs; i++) {
            sbTabs.append("\t\t");
        }
        return sbTabs;
    }

    public void ScrieInFisier(File numeFis, String text) throws IOException {
        FileWriter out = new FileWriter(numeFis, true);
        out.append(text);
        out.flush();
    }
}
