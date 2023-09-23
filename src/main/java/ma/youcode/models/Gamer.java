package ma.youcode.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
@Data
@Builder
public class Gamer {
    private BigInteger initialSolde;
    private int playedRounds;
    private int wonRounds;
    private int lostRounds;

    public void incrementPlayedRounds(){
        this.playedRounds++;
    }

    private void incrementWonRounds(){
        this.wonRounds++;
    }

    private void incrementLostRounds(){
        this.lostRounds++;
    }

    public void decrementSolde(BigInteger solde){
        this.initialSolde = this.initialSolde.subtract(solde);
    }


    public void incrementSolde(BigInteger solde){
        this.initialSolde =  this.initialSolde.add(solde);
    }

    public void wonRound(BigInteger solde){
        incrementWonRounds();
        incrementSolde(solde.multiply(BigInteger.valueOf(2)));
    }
    public void lostRound(){
        incrementLostRounds();
    }
}
