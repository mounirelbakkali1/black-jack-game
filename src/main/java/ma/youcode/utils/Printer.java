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
        StringBuilder symbol = new StringBuilder("");
        switch (card[1]){
            case 1 -> symbol.append("♠");
            case 2 -> symbol.append("♣");
            case 3 -> symbol.append("♥");
            case 4 -> symbol.append("♦");
        }
        switch (card[0]){
            case 1 -> symbol.append("A");
            case 11 -> symbol.append("J");
            case 12 -> symbol.append("Q");
            case 13 -> symbol.append("K");
            default -> symbol.append(card[0]);
        }
        return symbol.toString();
    }

    public static void printCards(int[][] player_cards, int[][] dealer_cards, boolean hideDealerCard){
        System.out.println("-----------Your cards-----------");
        StringBuilder cards = new StringBuilder("");
        for (int[] player_card : player_cards) {
            cards.append(Printer.cardValue(player_card));
            cards.append(" ");
        }
        System.out.println(cards);
        System.out.println("-----------Dealer cards-----------");
        cards = new StringBuilder("");
        for (int i = 0; i < dealer_cards.length; i++) {
            if (i==1 && hideDealerCard )cards.append("? ");
            else
                cards.append(Printer.cardValue(dealer_cards[i]));
                cards.append(" ");
        }
        System.out.println(cards);
    }
}
