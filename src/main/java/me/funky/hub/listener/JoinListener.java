package me.funky.hub.listener;

import me.funky.hub.Tempest;
import me.funky.hub.utils.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        Tempest.getInstance().getLobbyManager().handleLobby(player);

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
