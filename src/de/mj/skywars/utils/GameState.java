package de.mj.skywars.utils;

import de.mj.skywars.SkyWars;

public class GameState {

    private GameEnum gameEnum;

    public GameState () {}

    public void setGameState(GameEnum gameState) {
        gameEnum = gameState.LOBBY;
    }

    public GameEnum getGameState () {
        return gameEnum;
    }

    public void GameStart() {
        if (getGameState().equals(gameEnum.LOBBY)) {
            setGameState(gameEnum.START);
        } else {

        }
    }

    public void GameEnd () {

    }
}
