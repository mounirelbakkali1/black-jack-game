package ma.youcode.services;

import ma.youcode.utils.Collector;

import java.util.Random;


public class CardService implements ICardService {

    @Override
    public int[][] creer_cartes() {
        System.out.println("[CARDS CREATION...]");
        int CARDS_NUMBER = 13;
        int COLORS_NUMBER = 4;
        int[][] table = new int[CARDS_NUMBER*COLORS_NUMBER][2];
        int index = 0;
        for (int i=0;i<CARDS_NUMBER;i++){
            for(int j=0;j<COLORS_NUMBER;j++){
                table[index] = new int[]{i+1, j+1};
                index++;
            }
        }
        return table;
    }

    @Override
    public int[][] melanger_cartes(int[][] cartes) {
        System.out.println("[CARDS SHUFFLING...]");
        int CARDS_NUMBER = cartes.length;
        int[][] shuffledCards = new int[CARDS_NUMBER][];
        int index = 0;
        while (index<CARDS_NUMBER){
            int[][][] ints = tirer_carte(cartes);
            shuffledCards[index] = ints[0][0];
            cartes = ints[1];
            index++;
        }
        return shuffledCards;

    }

    @Override
    public int[][][] piocher_n_cartes(int[][] cartes, int n) {
        int[][][] groups = new int[2][][];
        groups[0] = new int[n][];
        groups[1] = new int[cartes.length - n][];
        System.arraycopy(cartes, 0, groups[0], 0, n);
        System.arraycopy(cartes, n, groups[1], 0, cartes.length-n);
        return groups;
    }

    @Override
    public int[][] defausser_cartes(int[][]... cards) {
        System.out.println("[CARDS DISCARDING...]");
        return Collector.collect(cards);
    }


    public  int[][][] tirer_carte(int[][] cartes){
        Random random = new Random();
        int i = random.nextInt(cartes.length);
        return extraire_ieme_carte(cartes, i);

    }

    public int[][][] extraire_ieme_carte(int[][] cards , int n){
        int[][][] ints = new int[2][][];
        ints[0] = new int[][] {cards[n]};
        ints[1] = new int[cards.length - 1][];
        int j = 0;
        for (int i = 0; i < cards.length; i++) {
            if (i!=n)  ints[1][j++] = cards[i];
        }
        return ints;
    }


}
