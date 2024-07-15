package me.funky.hub.manager;

import me.funky.hub.Tempest;
import me.funky.hub.utils.ItemBuilder;
import me.funky.hub.utils.PlayerUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LobbyManager {

    public static final ItemStack SERVER_SELECTOR = new ItemBuilder(Material.valueOf(Tempest.getInstance().getHotbarYML().getConfig().getString("SERVER-SELECTOR.MATERIAL")))
            .name(Tempest.getInstance().getHotbarYML().getConfig().getString("SERVER-SELECTOR.NAME"))
            .data(Tempest.getInstance().getHotbarYML().getConfig().getInt("SERVER-SELECTOR.DATA")).build();

    public void handleLobby(Player player) {
        PlayerUtil.resetPlayer(player);
        PlayerUtil.teleportToSpawn(player);
        handleHotbar(player);
    }
    public void handleHotbar(Player player) {
        if(Tempest.getInstance().getHotbarYML().getConfig().getBoolean("SERVER-SELECTOR.ENABLED")) {
            player.getInventory().setItem(Tempest.getInstance().getHotbarYML().getConfig().getInt("SERVER-SELECTOR.SLOT"), SERVER_SELECTOR);
        }
    }
}
