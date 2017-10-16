package partnervermittlung;

public class PV {

    public static void main(String[] args) {

        Kunden k = new Kunden();
        Kunden k2 = new Kunden("Franz", "Mustermann", 19, 'm', 2000F, 10);

        k.stelltSichVor();
        k2.stelltSichVor();

        System.out.println("|Die Kundennummer ist: " + k2.nr);
        System.out.println("\n"+k2.wirdGeprueft(14, 15));
    }
}
