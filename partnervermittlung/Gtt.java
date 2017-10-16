package partnervermittlung;

public class Gtt extends Mensch 
{
//_______________________________________________________________________________________________________________________
    public static void main(String[] args) 
    {           
        Mensch adam  = new Mensch("Adam","Vater",18,'m',0);
        Mensch eva   = new Mensch("Eva","Mutter",17,'f',0);
        Mensch m     = new Mensch("Max","Mu",18,'m',10F);
        Mensch m2    = new Mensch("Franz","Mustermann",19,'m',5F);
        Mensch m3    = new Mensch("Hana","Song",20,'f',15F);
        Mensch m4    = new Mensch("Maria","Marie",21,'f',3000);
        Mensch m5    = new Mensch("Hans","Peter",25,'m',6000);
        Mensch m6    = new Mensch("Heidi","Klum",50,'f',100000);
        Mensch m7    = new Mensch("Angel","Merkel",55,'f',45000);
        Mensch m8    = new Mensch("Bill","Gates",60,'m',1000000);
        
        adam.stelltSichVor();
        eva.stelltSichVor();
        m.stelltSichVor();
        m2.stelltSichVor();
        m3.stelltSichVor();
        m4.stelltSichVor();
        m5.stelltSichVor();
        m6.stelltSichVor();
        m7.stelltSichVor();
        m8.stelltSichVor();
//_______________________________________________________________________________________________________________________        
        Mensch[] liste4;
        liste4 = new Mensch[10];
        liste4[0]       = adam;
        liste4[1]       = eva;
        liste4[2]       = m;
        liste4[3]       = m2;
        liste4[4]       = m3;
        liste4[5]       = m4;
        liste4[6]       = m5;
        liste4[7]       = m6;
        liste4[8]       = m7;
        liste4[9]       = m8;
//_______________________________________________________________________________________________________________________        
/*        adam.vname      = "Adam";
        adam.nname      = "Vater";
        adam.setAlter(18);
        adam.geschlecht = 'm';
        adam.setGehalt(0);
        adam.setStatus("vergeben");
//_______________________________________________________________________________________________________________________        
        eva.vname       = "Eva";
        eva.nname       = "Mutter";
        eva.setAlter(17);
        eva.geschlecht  = 'w';
        eva.setGehalt(0);
        eva.setStatus("vergeben");
//_______________________________________________________________________________________________________________________ 
        m.vname         = "Maria";
        m.nname         = "Marie";
        m.setAlter(19);
        m.geschlecht    = 'f';
        m.setGehalt(20);
        m.setStatus("ledig");*/
//_______________________________________________________________________________________________________________________ 

        
        
//_______________________________________________________________________________________________________________________        
    /*    System.out.printf("Liste der Eigenschaften von ADAM:\n|Vorname:\t%s\n|Nachname:\t%s\n|Alter:\t%d\n|Geschlecht:\t%c\n|Gehalt:\t%f\n|Status:\t%s\n"
                            ,adam.vname,adam.nname,adam.alter,adam.geschlecht,adam.gehalt,adam.status);
        
        System.out.printf("\nListe der Eigenschaften von EVA:\n|Vorname:\t%s\n|Nachname:\t%s\n|Alter:\t%d\n|Geschlecht:\t%c\n|Gehalt:\t%f\n|Status:\t%s\n"
                            ,eva.vname,eva.nname,eva.alter,eva.geschlecht,eva.gehalt,eva.status);
        
        System.out.printf("\nListe der Eigenschaften vom KANDIDAT:\n|Vorname:\t%s\n|Nachname:\t%s\n|Alter:\t%d\n|Geschlecht:\t%c\n|Gehalt:\t%f\n|Status:\t%s\n"
                            ,kandidat.vname,kandidat.nname,kandidat.alter,kandidat.geschlecht,kandidat.gehalt,kandidat.status);*/
//_______________________________________________________________________________________________________________________        
/*      m2.gibtNamenAn();
        m2.gibtDatenAn();
        
        m3.gibtNamenAn();
        m3.gibtDatenAn();
        
        m4.gibtNamenAn();
        m4.gibtDatenAn();
        
        m5.gibtNamenAn();
        m5.gibtDatenAn();
        
        m6.gibtNamenAn();
        m6.gibtDatenAn();
        
        m7.gibtNamenAn();
        m7.gibtDatenAn();
        
        m8.gibtNamenAn();
        m8.gibtDatenAn();

        for (int i = 0; i < 10; i++) 
        {
            System.out.println(liste4[i]);
        }
*/        
/*       int alter       = adam.getAlter();
        int alter2      = eva.getAlter();
        int alter3      = m.getAlter();
        
        char geschlecht = adam.getGeschlecht();
        char geschlecht2 = eva.getGeschlecht();
        char geschlecht3 = m.getGeschlecht();
        
        float gehalt    = adam.getGehalt();
        float gehalt2    = eva.getGehalt();
        float gehalt3    = m.getGehalt();
        
        String status   = adam.getStatus();
        String status2  = eva.getStatus();
        String status3  = m.getStatus();
        
        System.out.println("\nAdams Alter:\t"+alter);
        System.out.println("Evas Alter:\t"+alter2);
        System.out.println("M's Alter:\t"+alter3);
        
        System.out.println("\nAdam:\t"+adam.getAlter());
        System.out.println("Eva:\t"+eva.getAlter());
        System.out.println("M's:\t"+m.getAlter());
        
        System.out.println("\nAdams Geschlecht:\t"+geschlecht);
        System.out.println("Evas Geschlecht:\t"+geschlecht2);
        System.out.println("M's Geschlecht:\t"+geschlecht3);
        
        System.out.println("\nAdam:\t"+adam.getGeschlecht());
        System.out.println("Eva:\t"+eva.getGeschlecht());
        System.out.println("M's:\t"+m.getGeschlecht());
        
        System.out.println("\nAdams Gehalt:\t"+gehalt);
        System.out.println("Evas Gehalt:\t"+gehalt2);
        System.out.println("M's Gehalt:\t"+gehalt3);
        
        System.out.println("\nAdam:\t"+adam.getGehalt());
        System.out.println("Eva:\t"+eva.getGehalt());
        System.out.println("M's:\t"+m.getGehalt());
        
        System.out.println("\nAdams Status:\t"+status);
        System.out.println("Evas Status:\t"+status2);
        System.out.println("M's Status:\t"+status3);
        
        System.out.println("\nAdam:\t"+adam.getStatus());
        System.out.println("Eva:\t"+eva.getStatus());
        System.out.println("M's:\t"+m.getStatus());
        //System.out.println(liste4[].getAlter()) */
    }  
}