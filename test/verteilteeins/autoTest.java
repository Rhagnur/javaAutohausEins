package verteilteeins;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jan
 */
public class autoTest {
    
    @Test
    public void testGetKennzeichen() 
    {
        System.out.println("Kennzeichen des Autos wird geprüft...");
        auto kfz = new auto(null, 0);
        assertNotNull(kfz.getKennzeichen());
    }
    
    @Test
    public void testWartezeit() 
    {
        System.out.println("Wartezeit des Autos wird geprüft...");
        for (int i = 0; i < 1000; i++)
        {
            auto kfz = new auto(null, 0);
            assertTrue(kfz.getWartezeit() >= 20 && kfz.getWartezeit() <= 50);
        }
    }
    
    @Test
    public void testGetParkzeit() 
    {
        System.out.println("Parkzeit des Autos wird geprüft...");
        for (int i = 0; i < 1000; i++)
        {
            auto kfz = new auto(null, 0);
            assertTrue(kfz.getParkzeit() >= 20 && kfz.getParkzeit() <= 50);
        }
    }
}
