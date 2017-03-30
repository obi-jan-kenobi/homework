package rennwagen_v6;

/**
 * @author Ingrid
 */
public class Fahrer {
    private int fahrerid;
    private String vorname;
    private String nachname;
    private int alter;
    private double gehalt;

    /**
     * @param fahrerid
     * @param vorname
     * @param nachname
     * @param alter
     * @param gehalt
     */
    public Fahrer(int fahrerid,
                  String vorname,
                  String nachname,
                  int alter,
                  double gehalt) {
        this(fahrerid, vorname, nachname);

        this.alter = alter;
        this.gehalt = gehalt;
    }

    /**
     * @param fahrerid
     * @param vorname
     * @param nachname
     */
    public Fahrer(int fahrerid,
                  String vorname,
                  String nachname) {
        this.fahrerid = fahrerid;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    /**
     * @return
     */
    public int getFahrerid() {
        return this.fahrerid;
    }

    /**
     * @return
     */
    public String getVorname() {
        return this.vorname;
    }

    /**
     * @return
     */
    public String getNachname() {
        return this.nachname;
    }

    /**
     * @return
     */
    public int getAlter() {
        return this.alter;
    }

    /**
     * @return
     */
    public double getGehalt() {
        return this.gehalt;
    }

    /**
     * @param vorname
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * @param nachname
     */
    public void setName(String nachname) {
        this.nachname = nachname;
    }

    /**
     * @param alter
     */
    public void setAlter(int alter) {
        this.alter = alter;
    }

    /**
     * @param gehalt
     */
    public void setGehalt(int gehalt) {
        this.gehalt = gehalt;
    }

    public void getInfo() {
        /**
         * Die Methode getInfo() protokolliert 
         * alle wichtigen Merkmale eines Fahrerobjekts. 
         */
        System.out.println("Fahrerid: " +
                this.getFahrerid());

        System.out.println("Vorname: " +
                this.getVorname());

        System.out.println("Nachname: " +
                this.getNachname());

        System.out.println("Alter: " +
                this.getAlter());

        System.out.println("Gehalt: " +
                this.getGehalt());
    }
}
