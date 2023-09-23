package ma.youcode.utils;

import java.util.Objects;

public class Printer {



    public static void into(){
        System.out.println("""
                ██████╗░░█████╗░██████╗░░█████╗░██╗░░██╗███████╗██████╗░
                ██╔══██╗██╔══██╗██╔══██╗██╔══██╗██║░██╔╝██╔════╝██╔══██╗
                ██████╔╝███████║██████╔╝███████║█████═╝░█████╗░░██████╔╝
                ██╔═══╝░██╔══██║██╔══██╗██╔══██║██╔═██╗░██╔══╝░░██╔══██╗
                ██║░░░░░██║░░██║██║░░██║██║░░██║██║░╚██╗███████╗██║░░██║
                ╚═╝░░░░░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░╚═╝╚══════╝╚═╝░░╚═╝
                """);
    }

    public static String cardValue(int[] card){
        Objects.requireNonNull(card);
        String symbol = "";
        switch (card[1]){
            case 1 -> symbol = "♠";
            case 2 -> symbol = "♣";
            case 3 -> symbol = "♥";
            case 4 -> symbol = "♦";
        }
        switch (card[0]){
            case 1 -> symbol += "A";
            case 11 -> symbol += "J";
            case 12 -> symbol += "Q";
            case 13 -> symbol += "K";
            default -> symbol += card[0];
        }
        return symbol;
    }

    public static void printCards(int[][] player_cards, int[][] dealer_cards, boolean hideDealerCard){
        System.out.println("-----------Your cards-----------");
        String cards = "";
        for (int[] player_card : player_cards) {
            cards += Printer.cardValue(player_card) + " ";
        }
        System.out.println(cards);
        System.out.println("-----------Dealer cards-----------");
        cards = "";
        for (int i = 0; i < dealer_cards.length; i++) {
            if (i==1 && hideDealerCard )cards += "? ";
            else
                cards += Printer.cardValue(dealer_cards[i]) + " ";
        }
        System.out.println(cards);
    }
}
