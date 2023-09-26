package ma.youcode.services;

import lombok.Getter;
import lombok.Setter;
import ma.youcode.models.Gamer;
import ma.youcode.utils.Collector;
import ma.youcode.utils.ConsoleUtils;
import ma.youcode.utils.ListUtils;
import ma.youcode.utils.Printer;

import java.math.BigInteger;
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

    private static Long _token;
    private static int _piocheIndex = 0;
    public static Gamer _gamer;
    private static int[][] _cards;
    private static int[][] _shuffledCards;
    private static int[][] _bankCards=new int[][]{};
    private static int[][] _piocheTop=new int[][]{};
    private static int[][] _piocheBottom;
    private static int[][] player_cards =new int[][]{};
    private static int[][] dealer_cards =new int[][]{};


    private ICardService _cardService;

    public GameService() {
        _cardService = new CardService();
    }

    @Override
    public void start(Gamer gamer){
        Objects.requireNonNull(gamer, "Gamer can't be null");
        _gamer = gamer;
        prepareCards(_cardService.creer_cartes());
    }

    @Override
    public void restart() {
        System.out.println("[PIOCHE REACHED END] Restarting game...");
        int[][] cartesDefausse = _cardService.defausser_cartes(_piocheBottom, player_cards, dealer_cards, _bankCards);
        prepareCards(cartesDefausse);
    }

    @Override
    public void newRound(Long token) {
        ConsoleUtils.clear();
        System.out.println(String.format("""
                *******************************
                        New Round started
                *******************************
                Current sold : %s
                Played rounds : %s
                Won rounds : %s
                Lost rounds : %s
                Current token : %s
                ******************************* 
                """,_gamer.getInitialSold(),_gamer.getPlayedRounds(),_gamer.getWonRounds(),_gamer.getLostRounds(),token));
        if (player_cards != null || dealer_cards != null) {
            moveToBankCards(player_cards, dealer_cards);
        }
        // TODO : token shouldn't be null
        Objects.requireNonNull(token,"can't start round without token!");
        // TODO : player should have enough money to play with this token
        if(_gamer.getInitialSold().longValue() < token){
            throw new IllegalArgumentException("You don't have enough money to play with this token");
        }
        _gamer.incrementPlayedRounds();
        _token = token;
        // TODO : initially subtract token value from solde
        _gamer.decrementSolde(BigInteger.valueOf(_token));
        player_cards = new int[2][];
        dealer_cards = new int[2][];
        // TODO : pop 2 cards for player and dealer
        try{
            for (int i = 0; i < 2; i++) {
                player_cards[i] = popCard();
                dealer_cards[i] = popCard();
            }
        }catch (Exception e){
            // TODO : restart game
            restart();
            newRound(token);
            return;
        }
        check();
    }

    private void moveToBankCards(int[][] playerCards, int[][] dealerCards) {
        _bankCards = Collector.collect(_bankCards, playerCards, dealerCards);
    }

    @Override
    public boolean hit() {
        try {
        player_cards = ListUtils.addToList(player_cards,popCard());
        }catch (Exception e){
            // TODO : restart game
             restart();
             return true;
        }
        return check();
    }

    private void prepareCards(int[][] cards){
        _shuffledCards = _cardService.melanger_cartes(cards);
        Random random = new Random();
        _piocheIndex = random.nextInt(52 + 1 - 23) + 23;
        //_piocheIndex= 5;
        _piocheTop = _cardService.piocher_n_cartes(_shuffledCards, _piocheIndex)[0];
        _piocheBottom = _cardService.piocher_n_cartes(_shuffledCards, _piocheIndex)[1];
    }

    private boolean check() {
        boolean isEnd = false;
        int playerScore = getScore(player_cards);
        if (playerScore > 21 || playerScore == 21) {
            stand();
            isEnd = true;
        }else{
            Printer.printCards(player_cards,dealer_cards,true);
        }
        return isEnd;
    }


    @Override
    public void stand() {
        Printer.printCards(player_cards,dealer_cards,false);
        int playerScore = getScore(player_cards);
        if(playerScore> 21){
            System.out.println("You lose :(");
            _gamer.lostRound();
            return;
        }
        while (getScore(dealer_cards) < 17) {
            try {
                int[] popped = popCard();
                dealer_cards = ListUtils.addToList(dealer_cards, popped);
                System.out.println(String.format("dealer hits -> %s",Printer.cardValue(popped)));
            } catch (Exception e) {
                // TODO : restart game
                System.out.println("No more cards to pop for dealer!");
                restart();
                return;
            }
        }
        int dealerScore = getScore(dealer_cards);
        //Printer.printCards(player_cards,dealer_cards,false);
        if(dealerScore > 21){
            System.out.println("You win :)");
            return ;
        }
        if (playerScore > dealerScore) {
            _gamer.wonRound(BigInteger.valueOf(_token));
            System.out.println("You win :)");
        } else if (playerScore < dealerScore) {
            System.out.println("You lose :(");
            _gamer.lostRound();
        } else {
            // TODO : return token to sold
            _gamer.incrementSolde(BigInteger.valueOf(_token));
            System.out.println("Draw :|");
        }

    }

    private int getScore(int[][] _cards) {
        Objects.requireNonNull(_cards, "cards can't be null");
        int score = 0;
        for (int[] card : _cards) {
            score += _cardService.getCardValue(card);
        }
        return score;
    }

    private int[] popCard() throws Exception{
        if (_piocheTop.length == 0) {
           throw new Exception("No more cards to pop");
        }
        int[] last = _piocheTop[_piocheTop.length - 1];
        _piocheTop = ListUtils.removeLast(_piocheTop);
        return last;
    }

    @Override
    public  List<String> canPlayWith(){
        return tokens.stream()
                .filter(token -> token <= _gamer.getInitialSold().longValue())
                .map(String::valueOf)
                .toList();
    }



}
