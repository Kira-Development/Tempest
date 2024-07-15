package me.funky.hub.selector.subselector;

import lombok.AllArgsConstructor;
import me.funky.hub.Tempest;
import me.funky.hub.utils.CC;
import me.funky.hub.utils.menu.Button;
import me.funky.hub.utils.menu.Menu;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class SubSelectorMenu extends Menu {

    private String server;
    private final FileConfiguration config = Tempest.getInstance().getSubselectorYML().getConfig();

    @Override
    public String getTitle(Player player) {
        return CC.translate(config.getString("SUB-SELECTOR.TITLE").replace("%server%", server));
    }

    @Override
    public int getSize() {
        return config.getInt("SUB-SELECTOR.ROWS") * 9;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        for(String s : config.getConfigurationSection("SUB-SELECTOR." + server).getKeys(false)) {
            buttons.put(config.getInt("SUB-SELECTOR." + server + "." + s + ".SLOT"), new SubServerButton(server, s));
        }
        return buttons;
    }
}
