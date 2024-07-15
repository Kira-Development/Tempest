package me.funky.hub.selector.server;

import lombok.AllArgsConstructor;
import me.clip.placeholderapi.PlaceholderAPI;
import me.funky.hub.Tempest;
import me.funky.hub.selector.subselector.SubSelectorMenu;
import me.funky.hub.utils.ItemBuilder;
import me.funky.hub.utils.bungee.BungeeUtils;
import me.funky.hub.utils.menu.Button;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class ServerButton extends Button {

    private String server;
    private final FileConfiguration config = Tempest.getInstance().getSelectorYML().getConfig();

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.valueOf(config.getString(d("ITEM"))))
                .name(config.getString(d("NAME")))
                .lore(PlaceholderAPI.setPlaceholders(player, config.getStringList(d("LORE"))))
                .data(config.getInt(d("DATA")))
                .build();
    }


    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (config.getBoolean("SERVER-SELECTOR.ITEMS." + server + ".SUB-SERVER")) {
            new SubSelectorMenu(server).openMenu(player);
        } else {
            if (config.getBoolean("SERVER-SELECTOR.ITEMS." + server + ".COMMAND.ENABLED")) {
                Bukkit.dispatchCommand(player, config.getString("SERVER-SELECTOR.ITEMS." + server + ".COMMAND.COMMAND"));
            } else {
                BungeeUtils.sendToServer(player, server);
            }
        }
    }

    private String d(String a) {
        return "SERVER-SELECTOR.ITEMS." + server + "." + a;
    }
}
