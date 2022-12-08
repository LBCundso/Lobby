package de.lbc.lobby.listeners;

import de.lbc.lobby.Main;
import de.lbc.lobby.utils.ActionBarMSG;
import de.lbc.lobby.utils.ItemBuilder;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class JoinQuitListener implements Listener {
    private static Location spawn;

    public static Location getSpawn() {
        return spawn;
    }

    public JoinQuitListener() {
        loadConfig();
    }

    public void loadConfig() {
        FileConfiguration cfg = Main.getPlugin().getConfig();
        String wldName = cfg.getString("spawn.world");
        if (wldName != null) {
            World world = Bukkit.getWorld(wldName);
            if (world != null) {
                world.setDifficulty(Difficulty.PEACEFUL);
                world.setPVP(false);
            }
            spawn = Main.getCfgLocation("spawn");
        }
    }

    public static void setSpawn(Location location) {
        spawn = location;
        //config
        Main.setCfgLocation("spawn", spawn);
    }

    @EventHandler
    public void onPlrJoin(PlayerJoinEvent e) {
        if(spawn == null) return;
        e.setJoinMessage("");
        plrReset(e.getPlayer());
        e.getPlayer().performCommand("spawn");

        for (Player plr : spawn.getWorld().getPlayers()) {
            plr.sendMessage("[§a+§r] " + e.getPlayer().getName());
        }
    }

    @EventHandler
    public void onPlrQuit(PlayerQuitEvent e) {
        e.setQuitMessage("");
    }

    @EventHandler
    public void onPlrTP(PlayerTeleportEvent e){
        if(spawn == null) return;
        if(e.getFrom().getWorld() != e.getTo().getWorld() && e.getTo().getWorld().equals(spawn.getWorld())){
            plrReset(e.getPlayer());
        }
    }

    private void plrReset(Player plr){
        plr.getPlayer().setGameMode(GameMode.SURVIVAL);
        plr.setLevel(0);
        plr.setExp(0);
        for (PotionEffect effect: plr.getActivePotionEffects()) plr.removePotionEffect(effect.getType());
        ActionBarMSG.sendMessage(plr, "Willkommen auf der Lobby!", 5);
        plr.getInventory().clear();
        ItemStack nav = new ItemBuilder(Material.COMPASS).setAmount(1).setDisplayName(Main.navigatorName).build();
        plr.getInventory().setItem(4, nav);
    }

}
