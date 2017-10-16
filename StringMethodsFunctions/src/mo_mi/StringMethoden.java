/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mo_mi;

public class StringMethoden {
    public static void main(String[] args) {
        String s1,s2,s3,s4;
        
        s1=Eingabe.readString();
        s3="ja";
        s4="ok";
        
        if(s1.equals(s3)) {
            s2=s1.toUpperCase();
            System.out.println(s2);
        }
        else if(!s1.equals(s3))
        {
            s1=s1.substring(1,4);
            System.out.println(s1);
        }
    }   
}
