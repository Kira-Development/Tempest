package me.funky.hub.listener;

import me.funky.hub.Tempest;
import me.funky.hub.utils.CC;
import me.funky.hub.utils.PlayerUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void antiVoid(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        if(Tempest.getInstance().getConfig().getBoolean("ANTI-VOID.ENABLED")) {
            if(loc.getBlockY() <= Tempest.getInstance().getConfig().getInt("ANTI-VOID.LIMIT")) {
                PlayerUtil.teleportToSpawn(player);
                if(Tempest.getInstance().getMessagesYML().getConfig().getBoolean("ANTI-VOID.ENABLED")) {
                    player.sendMessage(CC.translate(Tempest.getInstance().getMessagesYML().getConfig().getString("ANTI-VOID.MESSAGE")));
                }
            }
        }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Player player = (Player) event.getEntity();
        event.setCancelled(true);
    }
}
