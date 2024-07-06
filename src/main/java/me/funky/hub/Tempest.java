package me.funky.hub;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import lombok.SneakyThrows;
import me.funky.hub.command.TempestCommand;
import me.funky.hub.manager.BuildManager;
import me.funky.hub.utils.CC;
import me.funky.hub.utils.YamlDoc;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Arrays;

@Getter
public class Tempest extends JavaPlugin {

    @Getter
    private static Tempest instance;
    private PaperCommandManager paperCommandManager;
    private BuildManager buildManager;
    private YamlDoc messagesYML;

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getConfigs();
        getManagers();
        getCommands();

        CC.pluginEnabled();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void getManagers() {
        this.paperCommandManager = new PaperCommandManager(this);
        this.buildManager = new BuildManager();
    }
    public void getCommands() {
        Arrays.asList(
                new TempestCommand()
        ).forEach(command -> paperCommandManager.registerCommand(command));
    }
    public void getConfigs() throws IOException {
        messagesYML = new YamlDoc(getDataFolder(), "messages.yml");
        messagesYML.init();
    }
}
