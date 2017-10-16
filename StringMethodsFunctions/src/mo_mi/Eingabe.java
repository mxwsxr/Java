/*
 * Eingabe.java
 *
 * Created on 20. Juni 2007, 21:17
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package mo_mi;


/**
 *
 * @author es
 */

import java.io.*;
import java.util.*;

public class Eingabe {
//Hilfsvariablen
    
    static InputStreamReader input =new InputStreamReader( System.in);
    static BufferedReader	eingabe=new BufferedReader(input);
    
    
/**liest von der Standardeingabe eine Zeichenkette ein*/
    public static String readString()
    
    {
        String wort="xxxx";
        try {
            wort=eingabe.readLine();
            
        } catch(IOException e1) {
            System.out.println("Eingabefehler,");
            
        }
        return wort;
    }
	/**liest von der Standardeingabe eine ganzeZahl ein*/
    public static int readInt()
    
    {
        int i=0;
        String wort="xxxx";
        try {
            wort=eingabe.readLine();
            i=Integer.parseInt(wort) ;
        } catch(IOException e1) {
            System.out.println("Eingabefehler, bitte noch einmal!");
            i=readInt();
        } catch(NumberFormatException e2) {
            if(wort.equals(""))
                System.out.println("Bitte etwas eingeben, noch einmal:");
            else {
                System.out.print("Fehler beim Format, ");
                System.out.println("bitte noch einmal!");}
            i=readInt();
        }
        return i;
    }
    
/**liefert eine rationale Zahl mit doppelter Pr�zision */
    public static double  readDouble()
    
    {
        double d=0.0;
        String wort="xxxx";
        try {
            wort=eingabe.readLine();
            d=Double.valueOf(wort).doubleValue() ;
        } catch(IOException e1) {
            System.out.println("Eingabefehler, bitte noch einmal!");
            d=readDouble();
        } catch(NumberFormatException e2) {
            if(wort.equals(""))
                System.out.println("Bitte etwas eingeben, noch einmal:");
            else {
                System.out.print("Fehler beim Format, ");
                System.out.println("bitte noch einmal!");}
            d=readDouble();
        }
        return d;
    }
    
    
/**liefert eine rationale Zahl */
    public static float readFloat()
    
    {
        float f=0.0f;
        String wort="xxxx";
        try {
            wort=eingabe.readLine();
            f=Float.valueOf(wort).floatValue() ;
        } catch(IOException e1) {
            System.out.println("Eingabefehler, bitte noch einmal!");
            f=readFloat();
        } catch(NumberFormatException e2) {
            if(wort.equals(""))
                System.out.println("Bitte etwas eingeben, noch einmal:");
            else {
                System.out.print("Fehler beim Format, ");
                System.out.println("bitte noch einmal!");}
            f=readFloat();
        }
        return f;
    }
    
    
/**liefert das erste eingetippte Zeichen, ignoriert den Rest */
    public static char readChar()
    
    {
        char c='0';
        String wort="xxxx";
        try {
            wort=eingabe.readLine();
            c=wort.charAt(0);
        } catch(IOException e1) {
            System.out.println("Eingabefehler, bitte noch einmal!");
            c=readChar();
        } catch(NumberFormatException e2) {
            if(wort.equals(""))
                System.out.println("Bitte etwas eingeben, noch einmal:");
            else {
                System.out.print("Fehler beim Format, ");
                System.out.println("bitte noch einmal!");}
            c=readChar();
        }
        return c;
    }
    
    
/**liefert einen Wahrheitswert */
    public static  boolean readBool()
    
    {
        boolean b=true;
        String wort="xxxx";
        try {
            wort=eingabe.readLine();
            //b=Boolean.valueOf(wort).booleanValue() ;
            //b=Boolean.getBoolean(wort);
            if (wort.equals("true")) b=true;
            else if (wort.equals("false")) b=false;
            else {
                System.out.print("Fehler beim Format, ");
                System.out.println("bitte noch einmal!");
                b=readBool();
            }
        } catch(IOException e1) {
            System.out.println("Eingabefehler, bitte noch einmal!");
            b=readBool();
        }
        return b;
    }
    
	/*public static String readDatum()
    
    {
        String wort="xxxx";
        Calendar datum=new GregorianCalendar(0,1,1);
        try {
            wort=eingabe.readLine();
            // datum=parseWort(eingabe);
            
            
        } catch(IOException e1) {
            System.out.println("Eingabefehler,");
            
        }
        return wort;
    }
    public static Calendar parseDatum(String s){
        
        int i=0,i1=0,i2=0,i3=0;
       
        String s1="",s2="",s3="";
        
        if(s.matches(".*\\..*") ){
            i=s.indexOf(".");
            s1=s.substring(0,i);
            
            s=s.substring(i+1);
            if(s.matches(".*\\..*") ){
                i=s.indexOf(".");
                s2=s.substring(0,i);
                s3=s.substring(i+1);
            }}
                
            try {
                i2=Integer.parseInt(s2);
            }catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            try {
                    i3=Integer.parseInt(s.substring(i+1));
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                } 
        GregorianCalendar dat= new GregorianCalendar(i3,i2,i1);
        System.out.println(dat);
        System.out.println(dat.getTime());
        return dat;
            }
            */
            
            
           /** Funktion f�r das Hauptprogramm, nur zu Testzwecken*/ 
            public static void main(String args[]) {
                int i=readInt();
                System.out.println(i);
                float f=readFloat();
                System.out.println(f);
                double d=readDouble();
                System.out.println(d);
                char c=readChar();
                
                System.out.println(c);
                boolean b=readBool();
                System.out.println(b);
            }
        }
        
        
        
