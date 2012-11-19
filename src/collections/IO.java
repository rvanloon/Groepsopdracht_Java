package collections ;

import java.io.*;
import javax.swing.JOptionPane;
import java.awt.Font;
/*

 Wanneer een methode met venster gebruikt is moet het programma afgesloten worden met System.exit(0)


 */
public class IO {


   static private BufferedReader lezer = new BufferedReader(new InputStreamReader(System.in));


/// Methodes voor tekst-interfaces/////////////////////////////////////////////

   /**
    *
    */
   public static void setFontCourier(int fontgrootte){
    if (fontgrootte > 26) fontgrootte = 26;
	if (fontgrootte < 8) fontgrootte = 8;
	Font font = new Font("Courier",Font.BOLD,fontgrootte);
	javax.swing.UIManager.put("OptionPane.messageFont",font);
   }
 	
   public static void toonString (String s) {
     System.out.print(s);
   }


   /**
    *
    */
   public static String leesString (String boodschap) {
     System.out.print(boodschap + " ");
     String s = "";
     try {
       s = lezer.readLine();
     }
     catch (IOException ioe) {
       System.out.println("Er is een invoer/uitvoer fout opgetreden: + ioe.getClass().toString()");
       System.out.println(ioe.getMessage());
     }
     return s;
   }


   /**
    *
    */
   public static char leesChar (String boodschap) {
     String s = leesString(boodschap);
     char c = s.charAt(0);
     return c;
   }

   /**
    *
    */
   public static byte leesByte (String boodschap) {
     String s = leesString(boodschap);
     byte b = 0;
     try {
       b = Byte.parseByte(s);
     }
     catch (NumberFormatException nfe) {
       System.out.println("Gelieve een geldig byte waarde in te voeren.");
     }
     return b;
   }

   /**
    *
    */
   public static short leesShort (String boodschap) {
     String s = leesString(boodschap);
     short sh = 0;
     try {
       sh = Short.parseShort(s);
     }
     catch (NumberFormatException nfe) {
       System.out.println("Gelieve een geldig short waarde in te voeren.");
     }
     return sh;
   }


   /**
    *
    */
   public static int leesInt (String boodschap) {
     String s = leesString(boodschap);
     int i = 0;
     try {
       i = Integer.parseInt(s);
     }
     catch (NumberFormatException nfe) {
       System.out.println("Gelieve een geldig int waarde in te voeren.");
     }
     return i;
   }


   /**
    *
    */
   public static long leesLong (String boodschap) {
     String s = leesString(boodschap);
     long l = 0;
     try {
       l = Long.parseLong(s);
     }
     catch (NumberFormatException nfe) {
       System.out.println("Gelieve een geldig long waarde in te voeren.");
     }
     return l;
   }

   /**
    *
    */
   public static float leesFloat (String boodschap) {
     String s = leesString(boodschap);
     float f = 0.0f;
     try {
       f = Float.parseFloat(s);
     }
     catch (NumberFormatException nfe) {
       System.out.println("Gelieve een geldige float waarde in te voeren.");
     }
     return f;
   }


   /**
    *
    */
   public static double leesDouble (String boodschap) {
     String s = leesString(boodschap);
     double d = 0.0;
     try {
       d = Double.parseDouble(s);
     }
     catch (NumberFormatException nfe) {
       System.out.println("Gelieve een geldige double waarde in te voeren.");
     }
     return d;
   }


/// Methodes voor grafische interface /////////////////////////////////////////


   /**
    *
    */
   public static void toonStringMetVenster (String s) {
     JOptionPane.showMessageDialog(null, s);
   }


   /**
    *
    */
   public static String leesStringMetVenster () {
     String s = JOptionPane.showInputDialog(null,"");
     return s;
   }

   /**
    *
    */
   public static String leesStringMetVenster (String str) {
     String s = JOptionPane.showInputDialog(null,str);
     return s;
   }

   public static String leesStringMetVenster (String str,String titel) {
     String s = JOptionPane.showInputDialog(null,str,titel,1);
     return s;
   }

   public static String leesStringCorrectieMetVenster(String str, String teCorrigeren) {
	 String s = JOptionPane.showInputDialog(null, str, teCorrigeren);
	 return s;
   }

   /**
    *
    */
   public static char leesCharMetVenster () {
     String s = leesStringMetVenster();
     char c = s.charAt(0);
     return c;
   }


   /**
    *
    */
   public static char leesCharMetVenster (String str) {
     String s = leesStringMetVenster(str);
     char c = s.charAt(0);
     return c;
   }


   /**
    *
    */
   public static byte leesByteMetVenster () {
     String s = leesStringMetVenster();
     byte b = 0;
     try {
       b = Byte.parseByte(s);
     }
     catch (NumberFormatException nfe) {
       JOptionPane.showMessageDialog(null, "Gelieve een geldig byte waarde in te voeren.", "Fout", JOptionPane.ERROR_MESSAGE);
     }
     return b;
   }



   /**
    *
    */
   public static byte leesByteMetVenster (String str) {
     String s = leesStringMetVenster(str);
     byte b = 0;
     try {
       b = Byte.parseByte(s);
     }
     catch (NumberFormatException nfe) {
       JOptionPane.showMessageDialog(null, "Gelieve een geldig byte waarde in te voeren.", "Fout", JOptionPane.ERROR_MESSAGE);
     }
     return b;
   }


   /**
    *
    */
   public static short leesShortMetVenster () {
     String s = leesStringMetVenster();
     short sh = 0;
     try {
       sh = Short.parseShort(s);
     }
     catch (NumberFormatException nfe) {
       JOptionPane.showMessageDialog(null, "Gelieve een geldig short waarde in te voeren.", "Fout", JOptionPane.ERROR_MESSAGE);
     }
     return sh;
   }


   /**
    *
    */
   public static short leesShortMetVenster (String str) {
     String s = leesStringMetVenster(str);
     short sh = 0;
     try {
       sh = Short.parseShort(s);
     }
     catch (NumberFormatException nfe) {
       JOptionPane.showMessageDialog(null, "Gelieve een geldig short waarde in te voeren.", "Fout", JOptionPane.ERROR_MESSAGE);
     }
     return sh;
   }


   /**
    *
    */
   public static int leesIntMetVenster () {
     String s = leesStringMetVenster();
     int i = 0;
     try {
       i = Integer.parseInt(s);
     }
     catch (NumberFormatException nfe) {
       JOptionPane.showMessageDialog(null, "Gelieve een geldig int waarde in te voeren.", "Fout", JOptionPane.ERROR_MESSAGE);
     }
     return i;
   }


   /**
    *
    */
   public static int leesIntMetVenster (String str) {
     String s = leesStringMetVenster(str);
     int i = 0;
     try {
       i = Integer.parseInt(s);
     }
     catch (NumberFormatException nfe) {
       JOptionPane.showMessageDialog(null, "Gelieve een geldig int waarde in te voeren.", "Fout", JOptionPane.ERROR_MESSAGE);
     }
     return i;
   }

	 public static int leesIntMetVenster (String str,String titel) {
     String s = leesStringMetVenster(str,titel);
     int i = 0;
     try {
       i = Integer.parseInt(s);
     }
     catch (NumberFormatException nfe) {
       JOptionPane.showMessageDialog(null, "Gelieve een geldig int waarde in te voeren.", "Fout", JOptionPane.ERROR_MESSAGE);
     }
     return i;
   }


   /**
    *
    */
   public static long leesLongMetVenster () {
     String s = leesStringMetVenster();
     long l = 0;
     try {
       l = Long.parseLong(s);
     }
     catch (NumberFormatException nfe) {
       JOptionPane.showMessageDialog(null, "Gelieve een geldig long waarde in te voeren.", "Fout", JOptionPane.ERROR_MESSAGE);
     }
     return l;
   }


   /**
    *
    */
   public static long leesLongMetVenster (String str) {
     String s = leesStringMetVenster(str);
     long l = 0;
     try {
       l = Long.parseLong(s);
     }
     catch (NumberFormatException nfe) {
       JOptionPane.showMessageDialog(null, "Gelieve een geldig long waarde in te voeren.", "Fout", JOptionPane.ERROR_MESSAGE);
     }
     return l;
   }


   /**
    *
    */
   public static float leesFloatMetVenster () {
     String s = leesStringMetVenster();
     float f = 0.0f;
     try {
       f = Float.parseFloat(s);
     }
     catch (NumberFormatException nfe) {
       JOptionPane.showMessageDialog(null, "Gelieve een geldige float waarde in te voeren.", "Fout", JOptionPane.ERROR_MESSAGE);
     }
     return f;
   }


   /**
    *
    */
   public static float leesFloatMetVenster (String str) {
     String s = leesStringMetVenster(str);
     float f = 0.0f;
     try {
       f = Float.parseFloat(s);
     }
     catch (NumberFormatException nfe) {
       JOptionPane.showMessageDialog(null, "Gelieve een geldige float waarde in te voeren.", "Fout", JOptionPane.ERROR_MESSAGE);
     }
     return f;
   }


   /**
    *
    */
   public static double leesDoubleMetVenster () {
     String s = leesStringMetVenster();
     double d = 0.0;
     try {
       d = Double.parseDouble(s);
     }
     catch (NumberFormatException nfe) {
       JOptionPane.showMessageDialog(null, "Gelieve een geldige double waarde in te voeren.", "Fout", JOptionPane.ERROR_MESSAGE);
     }
     return d;
   }


   /**
    *
    */
   public static double leesDoubleMetVenster (String str) {
     String s = leesStringMetVenster(str);
     double d = 0.0;
     try {
       d = Double.parseDouble(s);
     }
     catch (NumberFormatException nfe) {
       JOptionPane.showMessageDialog(null, "Gelieve een geldige double waarde in te voeren.", "Fout", JOptionPane.ERROR_MESSAGE);
     }
     return d;
   }

	public static double leesDoubleMetVenster (String str,String titel) {
     String s = leesStringMetVenster(str,titel);
     double d = 0.0;
     try {
       d = Double.parseDouble(s);
     }
     catch (NumberFormatException nfe) {
       JOptionPane.showMessageDialog(null, "Gelieve een geldige double waarde in te voeren.", "Fout", JOptionPane.ERROR_MESSAGE);
     }
     return d;
   }

}