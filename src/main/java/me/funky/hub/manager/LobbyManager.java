package me.funky.hub.manager;

import me.funky.hub.utils.PlayerUtil;
import org.bukkit.entity.Player;

public class LobbyManager {

    public void handleLobby(Player player) {
        PlayerUtil.resetPlayer(player);
        PlayerUtil.teleportToSpawn(player);
    }

}
