package de.tschallacka.spigot.randomspawn.config;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public final class Configuration extends Config
{
    public static int radius;
    private static Configuration instance;

    private Configuration()
    {
        super("config.yml");
    }

    protected int loadInteger(String name, int default_value)
    {
        int value = getConfig().getInt(name, default_value);
        getConfig().set(name, value);
        return value;
    }

    @Override
    public void load()
    {
        this.radius = this.loadInteger("random-spawn-radius-in-blocks", 200);
    }

    public static FileConfiguration get()
    {
        return getInstance().config;
    }

    public static Configuration getInstance()
    {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    @Override
    public void update()
    {
        return;
    }

}