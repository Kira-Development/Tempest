package me.funky.hub.listener;

import me.funky.hub.Tempest;
import me.funky.hub.utils.CC;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        Tempest.getInstance().getLobbyManager().handleLobby(player);

        if(Tempest.getInstance().getConfig().getBoolean("JOIN-SOUND.ENABLED")) {
            player.playSound(player.getLocation(), Sound.valueOf(Tempest.getInstance().getConfig().getString("JOIN-SOUND.SOUND").toUpperCase()), 1.0F, 1.0F);
        }
        if(Tempest.getInstance().getMessagesYML().getConfig().getBoolean("JOIN.CLEAR-CHAT")) {
            for(int i = 0; i < 1000; i++) {
                player.sendMessage("");
            }
        }
        if(Tempest.getInstance().getMessagesYML().getConfig().getBoolean("JOIN.MOTD.ENABLED")) {
            for(String motd : Tempest.getInstance().getMessagesYML().getConfig().getStringList("JOIN.MOTD.LINES")) {
                player.sendMessage(CC.translate(motd));
            }
        }
        if(Tempest.getInstance().getMessagesYML().getConfig().getBoolean("JOIN.ANNOUNCE.ENABLED")) {
            player.sendMessage(CC.translate(Tempest.getInstance().getMessagesYML().getConfig().getString("JOIN.ANNOUNCE.MESSAGE").replaceAll("<player>", player.getDisplayName())));
        }
    }

}
