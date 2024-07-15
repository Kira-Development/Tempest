package me.funky.hub.listener;

import me.funky.hub.manager.LobbyManager;
import me.funky.hub.selector.server.ServerSelectorMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemListener implements Listener {

    @EventHandler
    public void itemInteraction(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() == null) {
            return;
        }
        if (event.getAction().toString().contains("RIGHT")) {
            if (event.getItem().equals(LobbyManager.SERVER_SELECTOR)) {
                new ServerSelectorMenu().openMenu(player);
            }
        }
    }
}
