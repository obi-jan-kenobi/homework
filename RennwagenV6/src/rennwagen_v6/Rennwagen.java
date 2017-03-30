package rennwagen_v6;

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
        this.id = "RW_" + Rennwagen.anzahlrennwagen;
        Rennwagen.anzahlrennwagen++;
        this.maxtankinhalt = 5;
        this.isttankinhalt = 5;
    }

    /**
     * @param modell
     * @param hersteller
     */
    public Rennwagen(String modell,
                     String hersteller) {
        this();
        this.modell = modell;
        this.hersteller = hersteller;
    }

    /**
     * @param modell
     * @param hersteller
     * @param maxtankinhalt
     */
    public Rennwagen(String modell,
                     String hersteller,
                     float maxtankinhalt) {
        this(modell, hersteller);

        this.isttankinhalt = 5;
        this.maxtankinhalt = maxtankinhalt;
    }

    /**
     *
     * @param id
     * @param modell
     * @param hersteller
     * @param leistung
     * @param hubraum
     * @param isttankinhalt
     * @param maxtankinhalt
     */
    public Rennwagen(String id, String modell, String hersteller, int leistung, int hubraum, float isttankinhalt, float maxtankinhalt) {
        this(modell, hersteller, maxtankinhalt);

        this.id = id;
        this.leistung = leistung;
        this.hubraum = hubraum;
        this.isttankinhalt = isttankinhalt;
    }

    /**
     * @return
     */
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return
     */
    public String getModell() {
        return this.modell;
    }

    /**
     * @param modell
     */
    public void setModell(String modell) {
        this.modell = modell;
    }

    /**
     * @return
     */
    public String getHersteller() {
        return this.hersteller;
    }

    /**
     * @param hersteller
     */
    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    /**
     * @return
     */
    public int getLeistung() {
        return this.leistung;
    }

    /**
     * @param leistung
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
     * @param hubraum
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
     * @param isttankinhalt
     */
    public void setIsttankinhalt(float isttankinhalt) {
        if (isttankinhalt <= this.getMaxtankinhalt()) {
            this.isttankinhalt = isttankinhalt;
        } else {
            this.isttankinhalt = this.getMaxtankinhalt();
            System.out.println("Tank läuft über!");
        }
    }

    /**
     * @return
     */
    public float getMaxtankinhalt() {
        return this.maxtankinhalt;
    }

    /**
     * @param maxtankinhalt
     */
    public void setMaxtankinhalt(float maxtankinhalt) {
        this.maxtankinhalt = maxtankinhalt;
    }

    /**
     * @param gefahrenekilometer
     * @param durchschnittsverbrauch
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
     * @param liter
     */
    public void tanken(float liter) {
        this.setIsttankinhalt(this.getIsttankinhalt() + liter);
    }

    /**
     * ergänzte Methoden gegen�über Rennwagen_v1
     */

    /**
     * @param einfahrer
     */
    public void einfuegenFahrer(Fahrer einfahrer) {
        meinefahrer.add(einfahrer);
    }

    /**
     * @param einfahrer
     * @param index
     */
    public void einfuegenFahrer(Fahrer einfahrer, int index) {
        meinefahrer.add(index, einfahrer);
    }

    /**
     * @param index
     */
    public void loescheFahrer(int index) {
        meinefahrer.remove(index);
    }

    /**
     *
     */
    public void druckeFahrerliste() {
        System.out.println("Mögliche Fahrer des Wagens " + this.id + ":\n\n");

        for (int i = 0; i < this.meinefahrer.size(); i++) {
            ((Fahrer) this.meinefahrer.get(i)).getInfo();
            System.out.println();
        }
    }

    public String toString() {

        return this.getId()             + "," +
               this.getModell()         + "," +
               this.getHersteller()     + "," +
               this.getLeistung()       + "," +
               this.getHubraum()        + "," +
               this.getMaxtankinhalt()  + "," +
               this.getIsttankinhalt()  + ",";
    }
}
