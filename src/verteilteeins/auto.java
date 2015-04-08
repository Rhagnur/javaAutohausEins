package verteilteeins;

import java.util.Random;

/**
 * Beschreibung und Definieren eines Autos und jeder gewünschten Funktion
 * @author Jan Didschuneit <s0539714>
 * @version 1.0
 */

public class auto implements Runnable {
    
    private String kennzeichen;
    private int wartezeit;
    private int parkzeit;
    private parkhaus[] parkhaeuser;
    
    /**
     * Der Konstruktor des Objekt Auto
     * @param parken Array aller möglichen Parkhäuser
     * @param nummer Die eigene Nummer des Fahrzeugs als Integer
     * @param anzahlParkhaeuser Die maximale Anzahl der bekannten Parkhäuser
     */
    auto(parkhaus[] parken, int nummer)
    {
        kennzeichen = ("B HTW " + nummer);
        wartezeit = zufallszahl(20,50);
        parkzeit = zufallszahl(20,50);
        parkhaeuser = parken;
    }
    
    @Override public void run()
    {
        int parkhausNummer = 0;
        try
        {     
            // Auto wartet die vorgebene Zeit
            Thread.sleep(wartezeit);
            
            // Auto prüft solange bis es freies Parkhaus findet
            // wenn so nicht gefordert dann in while Körper (parkhaeuser[parkhausNummer].plaetzeFrei() == 0)
            while (parkhaeuser[parkhausNummer].parkhausVoll())
            {
                parkhausNummer += 1;
                // Sobald die maximale Anzahl der bekannten Parkhäuser erreicht ist, fängt es wieder von vorn an
                if (parkhausNummer == parkhaeuser.length)
                {
                    parkhausNummer = 0;
                }
            }

            parkhaeuser[parkhausNummer].autoReinfahren(kennzeichen);

            // Auto "parkt" die vorgebene Zeit
            Thread.sleep(parkzeit);
            
            parkhaeuser[parkhausNummer].autoRausfahren(kennzeichen);                        
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (IllegalStateException e)
        {
            System.err.println(e.getMessage());
        }
        
    }
    
    /**
     * Methode für den Erhalt des Kennzeichens
     * @return Kennzeichen vom Typ String wird zurückgegeben
     */
    public String getKennzeichen()
    {
        return kennzeichen;
    }
    
    /**
     * Methode für den Erhalt der Wartezeit
     * @return Wartezeit vom Typ Integer wird zurückgegeben
     */
    public int getWartezeit()
    {
        return wartezeit;
    }
    
    /**
     * Methode für den Erhalt der Parkzeit
     * @return Parkzeit vom Typ Integer wird zurückgegeben
     */
    public int getParkzeit()
    {
        return parkzeit;
    }
    
    /**
     * Methode zum Generieren einer Zufallszahl
     * Gefunden im Internet (http://blog.mynotiz.de/programmieren/java-zufallszahl-von-bis-703/)
     * @param min Die Untergrenze der Zufallszahl als Integer
     * @param max Die Obergrenze der Zufallszahl als Integer
     * @return Eine Zufallszahl im vorher gewählten Intervall
     */
    private int zufallszahl(int min, int max)
    {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    
    
    
}
