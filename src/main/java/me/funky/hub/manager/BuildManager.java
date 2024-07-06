package me.funky.hub.manager;

import lombok.Getter;
import me.funky.hub.Tempest;
import me.funky.hub.utils.CC;
import org.bukkit.entity.Player;

import java.util.ArrayList;

@Getter
public class BuildManager {

    private ArrayList<Player> builders = new ArrayList<Player>();

    public void enableBuildmode(Player player) {
        if(!builders.contains(player)) {
            builders.add(player);
            player.sendMessage(CC.translate(Tempest.getInstance().getMessagesYML().getConfig().getString("BUILDMODE.ENABLED")));
        }
    }
    public void disableBuildmode(Player player) {
        if (builders.contains(player)) {
            builders.remove(player);
            player.sendMessage(CC.translate(Tempest.getInstance().getMessagesYML().getConfig().getString("BUILDMODE.DISABLED")));
        }
    }
}
