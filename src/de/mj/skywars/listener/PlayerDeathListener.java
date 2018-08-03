package de.mj.skywars.listener;

import de.mj.skywars.SkyWars;
import de.mj.skywars.utils.GameEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private final SkyWars skyWars;

    public PlayerDeathListener (SkyWars skyWars) {
        this.skyWars = skyWars;
        skyWars.setListener(this);
    }

    public void onDeath(PlayerDeathEvent deathEvent) {
        Player player = deathEvent.getEntity();
    }
}
