package Ex3;

import java.io.IOException;

/**
 * Created by lluca on 08-Nov-16.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Ex3 ex = new Ex3();
        Constate cst = new Constate();
        ex.ScrieInFisier(cst.inAstaScriu, "Calea initiala: \n");
        ex.ScrieInFisier(cst.inAstaScriu, cst.caleaInvestigata.toString());
        ex.afiseazaListaDinFisier(cst.caleaInvestigata.toString());
    }
}
