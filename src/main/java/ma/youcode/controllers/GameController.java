package ma.youcode.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.youcode.models.Gamer;
import ma.youcode.services.IGameService;

import java.math.BigInteger;
import java.util.Scanner;

@AllArgsConstructor
public class GameController {

    private IGameService _gameService;


    public void start(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your solde: ");
        BigInteger solde = sc.nextBigInteger();
        Gamer gamer = Gamer.builder()
                .initialSolde(solde)
                .playedRounds(0)
                .wonRounds(0)
                .lostRounds(0)
                .build();
        _gameService.start(gamer);
        _gameService.canPlayWith().forEach(token -> System.out.println(token+"$"));
        System.out.println("Select your token: ");
        String token = sc.next();
        _gameService.newRound(token);
       // while (hitOrStand().equals("hit")){
        hitOrStand();
        //}

    }

    public String hitOrStand(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Hit or Stand?");
        String action = sc.next();
        switch (action){
            case "hit" -> _gameService.hit();
            case "stand" -> _gameService.stand();
        }
        return action;
    }
}
