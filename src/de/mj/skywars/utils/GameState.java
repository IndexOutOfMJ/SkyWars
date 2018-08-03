package de.mj.skywars.utils;

import de.mj.skywars.SkyWars;

public class GameState {

    private final SkyWars skyWars;
    private GameEnum gameEnum;

    public GameState (SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public void setGameState(GameEnum gameState) {
        gameEnum = gameState.LOBBY;
    }

    public GameEnum getGameState () {
        return gameEnum;
    }

    public void GameStart() {
        if (getGameState().equals(gameEnum.LOBBY)) {
            setGameState(gameEnum.START);
            skyWars.getStartScheduler().GameStartScheduler();
        } else {

        }
    }

    public void GameEnd () {

    }
}
