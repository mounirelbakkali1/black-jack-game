package ma.youcode.services;

public interface ICardService {
    public int[][] creer_cartes();
    public int[][] melanger_cartes(int[][] cartes);
    public int[][][] piocher_n_cartes(int[][] cartes, int n);
    public int[][] defausser_cartes(int[][] pioche, int[][] cartes_a_defausses);

    default int getCardValue(int[] card){
        switch (card[1]){
            case 1 -> {
                return 11;
            }
            case 11,12,13 -> {
                return 10;
            }
            default -> {
                return card[1];
            }
        }
    }
}
