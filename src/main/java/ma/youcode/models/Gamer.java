package ma.youcode.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
@Data
@Builder
public class Gamer {
    public static BigInteger INIT = BigInteger.valueOf(0);
    private BigInteger initialSold;
    private int playedRounds;
    private int wonRounds;
    private int lostRounds;

    public void incrementPlayedRounds(){
        this.playedRounds++;
    }

    private void incrementWonRounds(){
        this.wonRounds++;
    }

    public void setInitialSold(BigInteger initialSold) {
        this.initialSold = initialSold;
        INIT = initialSold;
    }
    private void incrementLostRounds(){
        this.lostRounds++;
    }

    public void decrementSolde(BigInteger solde){
        this.initialSold = this.initialSold.subtract(solde);
    }


    public void incrementSolde(BigInteger solde){
        this.initialSold =  this.initialSold.add(solde);
    }

    public void wonRound(BigInteger solde){
        incrementWonRounds();
        incrementSolde(solde.multiply(BigInteger.valueOf(2)));
    }
    public void lostRound(){
        incrementLostRounds();
    }
}
