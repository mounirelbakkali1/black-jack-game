package ma.youcode.services;

import ma.youcode.models.Gamer;

import java.util.List;

public interface IGameService {
    public void start(Gamer gamer);
    public void restart();
    public void newRound(Long token);

    public boolean hit();
    public void stand();
    public List<String> canPlayWith();
}
