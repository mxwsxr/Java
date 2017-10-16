package mo_mi;

/**
 *
 * @author uservhs
 */
public class AStringtest {
    
    public static void main(String[] args) {
       String s1,s2,s3,s4;
       s1="Haus";
       s2=s1;
       s3=Eingabe.readString();
       
       System.out.println("\n");
       
       boolean inhaltGleich = s1.equals(s3);
       
       if(s1!=s2) {
           System.out.println(false+"\n");
       }
       else if(s1!=s3) {
           System.out.println(false+"\n");
       }
       else {
           System.out.println(true+"\n");
       }
       
       if(!s1.equals(s3))
       {
           System.out.println("Die Gleichheit des Inhalts ist: "+inhaltGleich);
       }
       else
       {
           System.out.println("Die Gleichheit des Inhalts ist: "+inhaltGleich);
       }
    }   
}
