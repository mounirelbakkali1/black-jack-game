package ma.youcode.services;

import lombok.Getter;
import lombok.Setter;
import ma.youcode.models.Gamer;
import ma.youcode.utils.ListUtils;
import ma.youcode.utils.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Getter
@Setter
public class GameService implements IGameService{
    List<Long> tokens = new ArrayList<>();
    {
        tokens.add(50L);
        tokens.add(100L);
        tokens.add(200L);
        tokens.add(500L);
        tokens.add(1000L);
        tokens.add(2000L);
    }

    private static int _currentRound = 0;
    private static int _piocheIndex = 0;
    private static Gamer _gamer;
    private static int[][] _cards;
    private static int[][] _shuffledCards;
    private static int[][] _bankCards;
    private static int[][] _pioche;
    private static int[][] player_cards ;
    private static int[][] dealer_cards ;


    private ICardService _cardService;

    public GameService() {
        _cardService = new CardService();
    }

    @Override
    public void start(Gamer gamer){
        Objects.requireNonNull(gamer, "Gamer can't be null");
        _gamer = gamer;
        _cards = _cardService.creer_cartes();
        _shuffledCards = _cardService.melanger_cartes(_cards);
        Random random = new Random();
        _piocheIndex = random.nextInt(52 + 1 - 23) + 23;
        _pioche = _cardService.piocher_n_cartes(_shuffledCards, _piocheIndex)[0];
    }

    @Override
    public void newRound(String token) {
        Objects.requireNonNull(token,"can't start round without token!");
        player_cards = new int[2][];
        dealer_cards = new int[2][];
        for (int i = 0; i < 2; i++) {
            player_cards[i] = popCard();
            dealer_cards[i] = popCard();
        }
        Printer.printCards(player_cards,dealer_cards,true);
    }

    @Override
    public void hit() {
        if (_pioche.length == 0) {
            stand();
        }
        player_cards = ListUtils.addToList(player_cards,popCard());
        Printer.printCards(player_cards,dealer_cards,true);
    }



    @Override
    public void stand() {
        Printer.printCards(player_cards,dealer_cards,false);
        int playerScore = getScore(player_cards);
        int dealerScore = getScore(dealer_cards);
        if (playerScore > dealerScore) {
            System.out.println("You win");
        } else if (playerScore < dealerScore) {
            System.out.println("You lose");
        } else {
            System.out.println("Draw");
        }


    }

    private int getScore(int[][] _cards) {
        int score = 0;
        for (int[] card : _cards) {
            score += _cardService.getCardValue(card);
        }
        return score;
    }

    private int[] popCard(){
        int[] last = _pioche[_pioche.length - 1];
        _pioche = ListUtils.removeLast(_pioche);
        return last;
    }

    @Override
    public  List<String> canPlayWith(){
        return tokens.stream()
                .filter(token -> token <= _gamer.getInitialSolde().longValue())
                .map(String::valueOf)
                .toList();
    }



}
