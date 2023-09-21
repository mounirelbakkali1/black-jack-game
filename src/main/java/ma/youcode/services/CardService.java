package ma.youcode.services;

public class CardService implements ICardService {

    private static int[][][] _cards;
    private static int[][][] _shuffledCards;
    private static int[][][] _bankCards;
    private static int[][][] _pioche;



    @Override
    public int[][][] creer_cartes() {
        return new int[0][][];
    }

    @Override
    public int[][][] melanger_cartes(int[][][] cartes) {
        return new int[0][][];
    }

    @Override
    public int[][][] piocher_n_cartes(int[][][] cartes, int n) {
        return new int[0][][];
    }

    @Override
    public void defausser_cartes(int[][][] pioche, int[][][] cartes_a_defausses) {

    }
}
