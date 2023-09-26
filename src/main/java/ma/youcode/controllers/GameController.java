package ma.youcode.controllers;

import lombok.AllArgsConstructor;
import ma.youcode.models.Gamer;
import ma.youcode.services.IGameService;
import ma.youcode.utils.ConsoleUtils;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class GameController {

    private IGameService _gameService;


    public void start(){
        Scanner sc = new Scanner(System.in);
        int sold = ConsoleUtils.readInt("Enter your solde: ", false);
        Gamer gamer = Gamer.builder()
                .playedRounds(0)
                .wonRounds(0)
                .lostRounds(0)
                .build();
        gamer.setInitialSold(BigInteger.valueOf(sold));
        _gameService.start(gamer);
        playNewRound(true);
    }

    public void playNewRound(boolean firstRound){
        Scanner sc = new Scanner(System.in);
        if (!firstRound) {
            String action = ConsoleUtils.readString("New Round? (y/n) : ", false, new String[]{"y", "n"});
            if (action.equals("n")) {
                System.out.println("Bye!");
                return;
            }
        }
        List<String> canPlayWith = _gameService.canPlayWith();
        canPlayWith.forEach(token -> System.out.println(token+"$"));
        String token = ConsoleUtils.readString("Select your token: ", false, canPlayWith.toArray(new String[0]));
        _gameService.newRound(Long.valueOf(token));
        hitOrStand();
    }

    public String hitOrStand(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Hit or Stand?");
        String action = sc.next();
        switch (action){
            case "hit" -> {
                boolean ended = _gameService.hit();
                if (ended) playNewRound(false);
                else hitOrStand();
            }
            case "stand" -> {
                _gameService.stand();
                playNewRound(false);
            }
        }
        return action;
    }
}
