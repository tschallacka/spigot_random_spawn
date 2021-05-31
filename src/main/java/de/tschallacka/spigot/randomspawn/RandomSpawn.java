package de.tschallacka.spigot.randomspawn;

import de.tschallacka.spigot.randomspawn.config.Tools;
import de.tschallacka.spigot.randomspawn.event.RespawnEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomSpawn extends JavaPlugin {

    @Override
    public void onEnable() {
        Tools.setup(this);
        getServer().getPluginManager().registerEvents(new RespawnEventListener(), Tools.plugin);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
