package ma.youcode.services;

import ma.youcode.models.Gamer;

import java.util.List;

public interface IGameService {
    public void start(Gamer gamer);
    public void newRound(String token);

    public void hit();
    public void stand();
    public List<String> canPlayWith();
}
