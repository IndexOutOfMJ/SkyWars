/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package de.mj.skywars.utils;

import de.mj.skywars.SkyWars;

public class GameState {

    private final SkyWars skyWars;
    private GameEnum gameEnum;

    public GameState (SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public void setGameState(GameEnum gameState) {
        gameEnum = GameEnum.LOBBY;
    }

    public GameEnum getGameState () {
        return gameEnum;
    }

    public void GameStart() {
        if (getGameState().equals(GameEnum.LOBBY)) {
            setGameState(GameEnum.START);
            skyWars.getStartScheduler().GameStartScheduler();
        } else {

        }
    }

    public void GameEnd () {

    }
}
