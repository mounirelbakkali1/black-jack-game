package ma.youcode.services;

import lombok.Getter;
import lombok.Setter;
import ma.youcode.models.Gamer;

import java.util.Objects;

@Getter
@Setter
public class GameService implements IGameService{

    private static int _currentRound = 0;
    private static Gamer _gamer;

    private ICardService _cardService;

    public GameService() {
        _cardService = new CardService();
    }

    @Override
    public void start(Gamer gamer, String token){
        Objects.requireNonNull(gamer, "Gamer can't be null");
        _gamer = gamer;
        newRound(token);
    }

    @Override
    public void newRound(String token) {
    }

    @Override
    public void hit() {

    }

    @Override
    public void stand() {

    }
}
