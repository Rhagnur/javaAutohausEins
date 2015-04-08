package verteilteeins;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.*;

/**
 * Beschreibung und Definieren eines Parkhauses und jeder gewünschten Funktion
 * @author Jan Didschuneit <s0539714>
 * @version 1.0
 */
public class parkhaus {
    private int parkplatzMax;
    private int parkplatzFrei;
    private int parkhausNummer;
    private Object _lock = new Object();
    
    /**
     * Konstruktor für das Objekt Parkhaus
     * @param anzahl Anzahl der gewünschten Parkplätze als Integer
     * @param nummer Die eigene ID des Parkhauses als Integer
     */
    parkhaus(int anzahl, int nummer)
    {
        parkplatzFrei = anzahl;
        parkplatzMax = anzahl;
        parkhausNummer = nummer+1;
    }
    
    /**
     * Methode zum Prüfen ob das Parkhaus voll ist
     * @return Wahr wenn Parkhaus voll, ansonsten falsch
     */
    public boolean parkhausVoll()
    {
        synchronized(_lock)
        {
            return parkplatzFrei == 0;
        }
    }
    
    /**
     * Methode welche die Anzahl der freien Parkplätze zurück gibt
     * @return Anzahl der freien Parkplätze als Integerwert
     */
    public int plaetzeFrei()
    {
        return parkplatzFrei;
    }
    
    /**
     * Methode um in das Parkhaus zu fahren
     * @param kennzeichen Das übergebene Kennzeichen des Auto als String welches in das Parkhaus fährt
     */
    public void autoReinfahren(String kennzeichen) throws IllegalStateException
    {
        synchronized(_lock)
        {
            if (parkplatzFrei != 0)
            {
                parkplatzFrei -= 1;
                System.out.println (getDatum() + " " + kennzeichen + " fährt in Parkhaus "+ parkhausNummer +", noch " + parkplatzFrei + " Plaetze frei");
            }
            else
            {
                throw new IllegalStateException("Fehler: Parkhaus " + parkhausNummer + " / " + kennzeichen + " reinfahren. Parkhaus bereits voll");
            }
        }
        
        
    }
    
    /**
     * Methode zum Rausfahren aus dem Parkhaus
     * @param kennzeichen Das übergebene Kennzeichen des Auto als String welches aus dem Parkhaus fährt
     */
    public void autoRausfahren(String kennzeichen) throws IllegalStateException
    {
        synchronized(_lock)
        {
            if (parkplatzFrei != parkplatzMax)
            {
                parkplatzFrei += 1;
                System.out.println (getDatum() + " " + kennzeichen + " fährt aus Parkhaus " + parkhausNummer + ", noch " + parkplatzFrei + " Plaetze frei");
            }
            else
            {
                throw new IllegalStateException("Fehler: Parkhaus " + parkhausNummer + " / " + kennzeichen + " rausfahren. Parkhaus bereits leer");
            }
        }
        
    }
    
    /**
     * Methode um die freien Parkplätze zu verändern, wird nur im Test benutzt
     * @param parkplaetze Integerwert auf welchen man die freien Parkplätze setzen will
     */
    public void setFreiParkplaetze(int parkplaetze)
    {
        parkplatzFrei = parkplaetze;
    }
    
    /**
     * Methode um personalisierten Datum- und Zeitstempel zu erhalten
     * @return Gewünschter Zeitstempel als String
     */
    private String getDatum()
    {
        Calendar calendar = new GregorianCalendar();
        // DecimalFormat nötig um gewisse Elemente des Zeitstempels zu "füllen" und auf zwei Stellen zu bringen
        DecimalFormat df = new DecimalFormat("00");
        
        return (
                df.format(calendar.get(Calendar.DAY_OF_MONTH)) + "-" +
                df.format(calendar.get(Calendar.MONTH)) + "-" +
                calendar.get(Calendar.YEAR) + " " +
                df.format(calendar.get(Calendar.HOUR_OF_DAY)) + ":" +
                df.format(calendar.get(Calendar.MINUTE)) + ":" +
                df.format(calendar.get(Calendar.SECOND)) + "(" +
                calendar.get(Calendar.MILLISECOND) + ")"
                );
    }
}
