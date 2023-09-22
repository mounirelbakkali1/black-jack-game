package ma.youcode.services;

import junit.framework.TestCase;

public class CardServiceTest extends TestCase {

    public void testCreer_cartes() {
    }

    public void testMelanger_cartes() {
        CardService service = new CardService();
        service.tirer_carte(new int[][]{{1,2},{1,3},{1,4}});
    }

    public void testPiocher_n_cartes() {
    }

    public void testDefausser_cartes() {
    }

    public void testTirer_carte() {
    }

    public void testExtraire_ieme_carte() {
    }
}