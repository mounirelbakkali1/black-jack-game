package ma.youcode.services;

import ma.youcode.models.Gamer;

public interface IGameService {
    public void start(Gamer gamer, String token);
    public void newRound(String token);

    public void hit();
    public void stand();
}
