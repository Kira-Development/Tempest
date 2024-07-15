package me.funky.hub.listener;

import me.funky.hub.Tempest;
import me.funky.hub.utils.CC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if(Tempest.getInstance().getConfig().getBoolean("BLOCKED-COMMANDS.ENABLED")) {
            String command = event.getMessage().split(" ")[0].substring(1);
            if(!event.getPlayer().hasPermission("tempest.commandblock.bypass") && isBlockedCommand(command)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(CC.translate(Tempest.getInstance().getConfig().getString("BLOCKED-COMMANDS.NO-PERMISSION")));
            }
        }

    }
    private boolean isBlockedCommand(String command) {
        return Tempest.getInstance().getConfig().getStringList("BLOCKED-COMMANDS.LIST").contains(command.toLowerCase());

    }
}

