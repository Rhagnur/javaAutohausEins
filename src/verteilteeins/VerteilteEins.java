package verteilteeins;


/**
 * Allgemeine Hauptklasse des Belegs, erstellt und nutzt die anderen Klassen
 * @author Jan Didschuneit <s0539714>
 * @version 1.0
 */
public class VerteilteEins {
    
    private static final int anzahlParkhaeuser = 6;    
    private static final int anzahlAuto = 100;
    private static final int anzahlParkpleatze = 10;
    private static parkhaus [] parken = new parkhaus[anzahlParkhaeuser];
    
    /**
     * Hauptfunktion der Hauptklasse
     * @param args Alternativ übergebbare Argumente als Array von Strings
     */
    public static void main(String[] args) {
        
        // Parkhäuser werden erstellt und kriegen ihre Identifikationsnummer
        for (int j = 0; j < anzahlParkhaeuser; j++)
        {
            parken[j] = new parkhaus(anzahlParkpleatze, j);
        }
        
        // Es werden die maximal benötigten Autos erstellt und jedes bekommt die Parkhäuser übergeben
        for (int i = 0; i < anzahlAuto; i++)
        {
            Thread kfz = new Thread(new auto(parken, i));
            kfz.start();
        }
        
    }
}
