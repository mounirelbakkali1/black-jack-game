package ma.youcode.utils;


import lombok.extern.slf4j.Slf4j;
import ma.youcode.models.Gamer;
import ma.youcode.services.GameService;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Logger {

    public void saveLogs(){
        try {
            System.out.println("Saving logs...");
            Gamer gamer = GameService._gamer;
            BigInteger sold = Gamer.INIT;
            if (gamer == null) return;
            String logs = String.format("""
                    Gamer x has start  with %s DH and has played %s rounds and won %s rounds and lost %s rounds quit with %s DH  difference
                    """, sold, gamer.getPlayedRounds(), gamer.getWonRounds(), gamer.getLostRounds(), gamer.getInitialSold().subtract(sold));
            FileWriter writer = new FileWriter("history.txt", true);
            writer.write(logs);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving logs");
        }
    }
}
