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
}
