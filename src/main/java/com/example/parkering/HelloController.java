package com.example.parkering;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;


class Bil{
    public String bilNummer;
    public Date startTid;
    public int plass;
    public boolean kortTid; // true = korttids parkering

    public Bil(String bilNummer, Date startTid, boolean kortTid) {
        this.bilNummer = bilNummer;
        this.startTid = startTid;
        this.kortTid = kortTid;
    }

    public String formaterKvittering(){
        // formater kvitteringen etter oppgitt format
        String ut = "";
        Date nå = new Date();
        String startDatoTid = new SimpleDateFormat("dd.MM.YYYY HH:mm").format(nå);
        ut = "Kvittering for bilnr " + bilNummer + "\n" + "Start tid: " + startDatoTid + " til " + startDatoTid + "\n"
                + "Betalt " + avgift();
        return ut;
    }

    public double getPris(){
        // returner 10 eller 20 kroner avhengig av kortTid eller ikke
        if(this.kortTid = true){
            return 20;
        }
        else{
            return 10;
        }

    }
    public double avgift(){
        // regner ut tiden som er gått i timer og ganger med prisen
        Date nå = new Date();
        long varighet = nå.getTime() - startTid.getTime();
        int antallTimer = (int)varighet / 3600000;
        double sum = antallTimer * getPris();
        return sum;
    }
}

class Parkeringshus{
    public Bil [] biler;

    public void reserverPlass(Bil enBil){
        // legg bilen i arrayet
        int i = 0;
        while(i < biler.length && enBil != null){
            biler[i] = enBil;
            i++;
        }
    }

    public String frigjørPlass(String bilNummeret){
        /*
         ** må finne bilen i arrayet
         ** når den er funnet slett den fra arrayet
         ** og formater kvitteringen som returneres
         ** dersom bilen ikke finnes skal man returnere en passenede tekst
         */
        String resultat = "";
        boolean inputGyldighet = false;
        for(Bil enBil : biler){
            if(bilNummeret == enBil.bilNummer){
                inputGyldighet = true;
                enBil.bilNummer = null;
            }
        }
        if(!inputGyldighet){
            resultat = "Bilen finnes ikke";
        }
        return resultat;
    }
}

public class HelloController {
    // opprett parkeringshuset
    @FXML
    private Label lblAvgift;

    @FXML
    private TextField txtBilnummer;

    @FXML
    void kjørut(ActionEvent event) {
        // kall frigjør plass og legg ut kvitteringen i lblAvgift
    }

    @FXML
    void regKorttid(ActionEvent event) {
        // opprett en bil
        // og kall på reserver plass
    }

    @FXML
    void regLangtid(ActionEvent event) {
        // opprett en bil
        // og kall på reserver plass
    }

}
