package sample;

import java.util.ArrayList;

public class Rennwagen {

    static private int anzahlrennwagen = 0;

    private String id;
    private String modell;
    private String hersteller;
    private int leistung;
    private int hubraum;
    private float isttankinhalt;
    private float maxtankinhalt;
    private ArrayList meinefahrer = new ArrayList();

    /**
     *
     */
    public Rennwagen() {
        this.id = "RW " + Rennwagen.anzahlrennwagen;
        Rennwagen.anzahlrennwagen++;
        this.maxtankinhalt = 5;
        this.isttankinhalt = 5;
    }

    /**
     * Oparam modell
     * laparam hersteller
     */
    public Rennwagen(String modell, String hersteller) {
        this();
        this.modell = modell;
        this.hersteller = hersteller;
    }

    /**
     * fijpazam modell
     * Oparam hersteller
     * t3param maxtankinhalt
     */
    public Rennwagen(String modell, String hersteller, float maxtankinhalt) {
        this(modell, hersteller);

        this.isttankinhalt = 5;
        this.maxtankinhalt = maxtankinhalt;
    }

    /**
     * Preturn
     */
    public String getId() {
        return this.id;
    }


    public void setId(String id) {
        this.id = id;
    }


    /**
     * Preturn
     */
    public String getModell() {
        return this.modell;
    }


    /**
     * Oparam modell
     */
    public void setModell(String modell) {
        this.modell = modell;
    }


    /**
     * Preturn
     */
    public String getHersteller() {
        return this.hersteller;
    }


    /**
     * Oparam hersteller
     */
    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    /**
     * (are turn
     */
    public int getLeistung() {
        return this.leistung;
    }


    /**
     * pparam leistung
     */
    public void setLeistung(int leistung) {
        this.leistung = leistung;
    }


    /**
     * @return
     */
    public int getHubraum() // Kubikzentimeter
    {
        return this.hubraum;
    }


    /**
     * pparam hubraum
     */
    public void setHubraum(int hubraum) // Kubikzentimeter
    {
        this.hubraum = hubraum;
    }


    /**
     * @return
     */
    public float getIsttankinhalt() {
        return this.isttankinhalt;
    }

    /**
     * fijpazam isttankinhalt
     */
    public void setIsttankinhalt(float isttankinhalt) {
        if (isttankinhalt <= this.getMaxtankinhalt()) {
            this.isttankinhalt = isttankinhalt;
        } else {
            this.isttankinhalt = this.getMaxtankinhalt();
            System.out.println("Tank lduft caber!");
        }
    }

    /**
     * Preturn
     */
    public float getMaxtankinhalt() {
        return this.maxtankinhalt;
    }


    /**
     * Oparam maxtankinhalt
     */
    public void setMaxtankinhalt(float maxtankinhalt) {
        this.maxtankinhalt = maxtankinhalt;
    }

    /**
     * Oparam gefahrenekilometer
     * fljaa,ram durchschnittsverbrauch
     */
    public void fahren(float gefahrenekilometer, float durchschnittsverbrauch) {
        /*
         * Durchschnittsverbrauch = liter pro 100 km
         */
        float verbrauch = gefahrenekilometer * durchschnittsverbrauch / 100;

        if (verbrauch >= this.getIsttankinhalt()) {
            this.setIsttankinhalt(0);
            System.out.println("Tank leer. Sie sind leider liegen geblieben!");
        } else {
            this.setIsttankinhalt(this.getIsttankinhalt() - verbrauch);
        }
    }

    /**
     * 42,2,razu liter
     */
    public void tanken(float liter) {
        this.setIsttankinhalt(this.getIsttankinhalt() + liter);
    }

    /*public void einfuegenFahrer(Fahrer einfahrer){
        meinefahrer.add(einfahrer);
    }

    public void einfuegenFahrer(Fahrer einfahrer, int index) {
        meinefahrer.add(index, einfahrer);
    }

    public void loescheFahrer(int index) {
        meinefahrer.remove(index);
    }

    public void druckeFahrerliste() {
        System.out.println("Mogliche Fahrer des Wagens II" + this.id + ":\n\n");

        for (int i = 0; i < this.meinefahrer.size(); i++) {
            ((Fahrer) this.meinefahrer.get(i)).getInfo();
            System.out.println();
        }
    }*/


    /**
     *
     */


    public String toString() {
        return this.getId() + "," +
                this.getModell() + "," +
                this.getHersteller() + "," +
                this.getLeistung() + "," +
                this.getHubraum() + "," +
                this.getMaxtankinhalt() + "," +
                this.getIsttankinhalt() + ",";
    }
}
