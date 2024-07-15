package me.funky.hub.selector.server;

import me.funky.hub.Tempest;
import me.funky.hub.utils.CC;
import me.funky.hub.utils.menu.Button;
import me.funky.hub.utils.menu.Menu;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ServerSelectorMenu extends Menu {

    private final FileConfiguration config = Tempest.getInstance().getSelectorYML().getConfig();

    @Override
    public String getTitle(Player player) {
        return CC.translate(config.getString("SERVER-SELECTOR.TITLE"));
    }

    @Override
    public int getSize() {
        return config.getInt("SERVER-SELECTOR.ROWS") * 9;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        for(String s : config.getConfigurationSection("SERVER-SELECTOR.ITEMS").getKeys(false)) {
            buttons.put(config.getInt("SERVER-SELECTOR.ITEMS." + s + ".SLOT"), new ServerButton(s));
        }
        return buttons;
    }
}
