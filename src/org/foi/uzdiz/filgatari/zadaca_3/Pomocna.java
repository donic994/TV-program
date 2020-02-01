package org.foi.uzdiz.filgatari.zadaca_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.osobe;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.uloge;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author filip
 */
public class Pomocna {

    public static List<String> preuzmiPodatkeDatoteke(String putanjaDatoteke) {
        File file = new File(putanjaDatoteke);
        BufferedReader br = null;
        String st;
        List<String> retciDatoteke = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR! NE mogu pronaći datoteku!");;
        }
        try {
            br.readLine();
            while ((st = br.readLine()) != null) {
                retciDatoteke.add(st);
            }
        } catch (IOException ex) {
            System.out.println("ERROR! NE mogu čitati iz datoteke");;
        }
        return retciDatoteke;
    }

    public static String pretvoriBrojUDanTjedna(Integer broj) {
        if (broj == 1) {
            return "ponedjeljak";
        } else if (broj == 2) {
            return "utorak";
        } else if (broj == 3) {
            return "srijeda";
        } else if (broj == 4) {
            return "četvrtak";
        } else if (broj == 5) {
            return "petak";
        } else if (broj == 6) {
            return "subota";
        } else if (broj == 7) {
            return "nedjelja";
        } else {
            return "";
        }
    }


    public static String ispisiOsobu(Integer o) {
        String vrati = "";
        for (Osoba osoba : osobe) {
            if (osoba.id.equals(o)) {
                vrati = osoba.imeIPrezime;
            }
        }

        return vrati;
    }
    
     public static String ispisiUlogu(Integer o) {
        String vrati = "";
        for (Uloga u : uloge) {
            if (u.id.equals(o)) {
                vrati = u.opis;
            }
        }

        return vrati;
    }
}
