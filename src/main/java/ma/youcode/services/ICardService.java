package ma.youcode.services;

public interface ICardService {
    public int[][][] creer_cartes();
    public int[][][] melanger_cartes(int[][][] cartes);
    public int[][][] piocher_n_cartes(int[][][] cartes, int n);
    public void defausser_cartes(int[][][] pioche, int[][][] cartes_a_defausses);
}
