package partnervermittlung;

public class Kunden extends Mensch {

    int nr;

    public Kunden() {
        System.out.println("Konstruktor von Kunde ohne Parameter.");
    }

    public Kunden(String v, String n, int a, char g, float ge) {
        super(v, n, a, g, ge);
        System.out.println("Konstruktor von Kunde mit 5 Parametern.");

    }

    public Kunden(String v, String n, int a, char g, float ge, int no) {
        super(v, n, a, g, ge);
        nr = no;
        System.out.println("Konstruktor von Kunde mit 5 Parametern.");
    }

    protected boolean wirdGeprueft(int min, int max) {
        boolean rueckgabe;

        if ((alter > min) && (alter < max)) {
            rueckgabe = true;
        } else {
            rueckgabe = false;
        }
        return rueckgabe;
    }
}
