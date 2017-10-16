package partnervermittlung;

public class Mensch {

    String vname, nname;
    protected int alter = -1;
    protected char geschlecht = 'm';
    protected float gehalt;
    protected String status = "ledig";
    protected int geburt;
//___________________________________________________________________________________________________________________________

    public Mensch() {
        System.out.println("Konstruktor ohne Parameter.\n");
    }

    public Mensch(String v, String n, int a, char g, float ge) {
        System.out.println("Konstruktor mit 5 Parametern.\n");
        vname = v;
        nname = n;
        this.setAlter(a);
        this.setGeschlecht(g);
        this.setGehalt(ge);
    }
//___________________________________________________________________________________________________________________________   

    public void stelltSichVor() {
        System.out.printf("\n|Hallo, ich bin %s %s.\n|Ich bin %d Jahre alt und %c.\n|Mein Gehalt ist %f und bin %s.\n", vname, nname, alter, geschlecht, gehalt, status);
    }
//___________________________________________________________________________________________________________________________

    @Override
    public String toString() {
        String result;
        result = vname + "," + nname + "," + alter + "," + geschlecht + "," + gehalt + "," + status;
        return result;
    }
//_______________________________________________________________________________________________________________________

    public void gibtNamenAn() {
        System.out.println("\nBitten geben Sie Ihren Vornamen und Nachnamen an: ");
        vname = Eingabe.readString();
        nname = Eingabe.readString();
    }
//_______________________________________________________________________________________________________________________

    public void gibtDatenAn() {
        System.out.println("\nBitte geben Sie Ihr Alter an: ");
        alter = Eingabe.readInt();
        System.out.println("\nBitte geben Sie Ihr Geschlecht an: ");
        geschlecht = Eingabe.readChar();
        System.out.println("\nBitte geben Sie Ihr Gehalt an: ");
        setGehalt(Eingabe.readFloat());
        System.out.println("\nBitte geben Sie Ihren derzeitigen Status an: ");
        setStatus(Eingabe.readString());
    }
//___________________________________________________________________________________________________________________________

    String getVName() {
        return vname;
    }
    
    String getNName() {
        return nname;
    }
    
    int getAlter() {
        int rueckgabe;
        if (alter < 23) {
            rueckgabe = alter;
        } else {
            rueckgabe = alter - 5;
        }
        return rueckgabe;
    }

    char getGeschlecht() {
        return geschlecht;
    }

    float getGehalt() {
        return gehalt;
    }

    String getStatus() {
        return status;
    }

    int getGeburt() {
        return geburt;
    }
//___________________________________________________________________________________________________________________________   

    public void setVName(String vname) {
        this.vname = vname;
    }
    
    public void setNName(String nname) {
        this.nname = nname;
    }
    
    public void setAlter(int a) {
        alter = a;
        geburt = 2017 - a;
    }

    public void setGeschlecht(char g) {
        geschlecht = g;
    }

    public void setGeburt(int geb) {
        geburt = geb;
        alter = 2017 - geb;
    }

    public void setGehalt(float gehalt) {
        this.gehalt = gehalt;
    }

    public void setStatus(String status) {
        this.status = status;
    }
//___________________________________________________________________________________________________________________________    
}
