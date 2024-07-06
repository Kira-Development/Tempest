package me.funky.hub;

import lombok.Getter;
import me.funky.hub.utils.CC;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Tempest extends JavaPlugin {

    @Getter
    private static Tempest instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        CC.pluginEnabled();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
