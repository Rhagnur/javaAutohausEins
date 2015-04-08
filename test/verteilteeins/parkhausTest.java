package verteilteeins;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jan
 */
public class parkhausTest 
{

    private static int maxParken = 10;                      // Muss selben Wert haben wie in der Hauptklasse
    private static String testKennzeichen = "B TEST 1";
    
    @Test
    public void testParkhausVoll() 
    {
        parkhaus parken = new parkhaus(maxParken, 0);
        System.out.println("parkhausVoll Test");
        assertEquals(false, parken.parkhausVoll());
        
        for (int i = 0; i < maxParken; i++)
        {
            try
            {
                parken.autoReinfahren(testKennzeichen);
            }
            catch (IllegalStateException e)
            {
                System.out.println(e.getMessage());
            }
            
        }
        
        assertEquals(true, parken.parkhausVoll());
    }
    
    @Test
    public void testParkplaetzeFrei()
    {
        parkhaus parken = new parkhaus(maxParken, 0);
        System.out.println("Test parkplaetzeFrei wird gestartet...");
        parken.setFreiParkplaetze(maxParken);
        assertEquals(maxParken, parken.plaetzeFrei());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testAutoRausfahrenMitFehler()
    {
        parkhaus parken = new parkhaus(maxParken, 0);
        System.out.println("Test autoRausfahren wird gestartet, Fehler erwartet...");
        parken.autoRausfahren(testKennzeichen);
    }
    
    @Test
    public void testAutoRausfahren()
    {
        parkhaus parken = new parkhaus(maxParken, 0);
        System.out.println("Test autoRausfahren wird gestartet, kein Fehler erwartet...");
        parken.setFreiParkplaetze(5);
        parken.autoRausfahren(testKennzeichen);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testAutoReinfahrenMitFehler()
    {
        parkhaus parken = new parkhaus(maxParken, 0);
        System.out.println("Test autoReinfahren wird gestartet, Fehler erwartet...");
        for (int i = 0; i < maxParken+1; i++)
        {
            parken.autoReinfahren(testKennzeichen);
        }
    }
    
    @Test
    public void testAutoReinfahren()
    {
        parkhaus parken = new parkhaus(maxParken, 1);

        System.out.println("Test autoReinfahren wird gestartet, kein Fehler erwartet...");
        for (int i = 0; i < maxParken; i++)
        {
            parken.autoReinfahren(testKennzeichen);
        }
    }
}
