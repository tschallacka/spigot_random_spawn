package de.tschallacka.spigot.randomspawn.event;

import de.tschallacka.spigot.randomspawn.config.Configuration;
import de.tschallacka.spigot.randomspawn.config.Tools;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.util.BoundingBox;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;
import sun.jvm.hotspot.asm.Operand;

import javax.swing.*;

public class RespawnEventListener implements Listener
{
    @EventHandler
    public void onJoinEvent(PlayerSpawnLocationEvent event)
    {

        Location worldspawn = event.getSpawnLocation().getWorld().getSpawnLocation();
        BoundingBox box = new BoundingBox(worldspawn.getX() - 21, 0, worldspawn.getZ() - 21, worldspawn.getX()+21, 255, worldspawn.getZ()+21);
        if(event.getPlayer().getBedSpawnLocation() == null && box.contains(event.getSpawnLocation().toVector())) {
            Tools.info("Found a fresh player without bed spawn, lets randomly teleport "+event.getPlayer().getName());
            Location baseLocation = event.getSpawnLocation();
            int rand1 = ((int)(Math.random() * (Configuration.radius * 2))) - Configuration.radius;
            int rand2 = ((int)(Math.random() * (Configuration.radius * 2))) - Configuration.radius;
            Location newLoc = new Location(baseLocation.getWorld(), baseLocation.getX() + rand1, baseLocation.getY(), baseLocation.getZ() + rand2);
            Location newEglible = baseLocation.getWorld().getHighestBlockAt(newLoc).getLocation().add(0, 1, 0);
            Tools.info("Spawning  "+event.getPlayer().getName() + " to "+newEglible.toString());
            event.setSpawnLocation(newEglible);
        }
    }
    @EventHandler
    public void onRespawnEvent(PlayerRespawnEvent event)
    {
        Tools.info("respawn");
        if(event.getPlayer().getBedSpawnLocation() == null) {
            Tools.info("That's gotta hurt for "+event.getPlayer().getName() + ", no bed... random teleport it is");
            Location baseLocation = event.getRespawnLocation().getWorld().getSpawnLocation();
            int rand1 = ((int)(Math.random() * (Configuration.radius * 2))) - Configuration.radius;
            int rand2 = ((int)(Math.random() * (Configuration.radius * 2))) - Configuration.radius;
            Location newLoc = new Location(baseLocation.getWorld(), baseLocation.getX() + rand1, baseLocation.getY(), baseLocation.getZ() + rand2);
            Location newEglible = baseLocation.getWorld().getHighestBlockAt(newLoc).getLocation().add(0, 1, 0);
            Tools.info("Spawning  "+event.getPlayer().getName() + " to "+newEglible.toString());
            event.setRespawnLocation(newEglible);
        }
    }
}
