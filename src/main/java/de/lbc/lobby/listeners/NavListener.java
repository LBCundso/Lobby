package de.lbc.lobby.listeners;

import de.lbc.lobby.Main;
import de.lbc.lobby.utils.ItemBuilder;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class NavListener implements Listener {
    private static Inventory navInv;

    private static HashMap<String, Location> navPos = new HashMap<>();

    public NavListener() {
        navInv = Bukkit.createInventory(null, 9, Main.navigatorName);
        updateNav();
    }

    public static void updateNav(){
        navInv.clear();
        FileConfiguration cfg = Main.getPlugin().getConfig();
        for(int i = 1; i <= 9; i++){
            Location loc = Main.getCfgLocation("navigator." + i + ".spawn");
            if(loc == null) continue;
            String name = cfg.getString("navigator." + i + ".name");
            String material = cfg.getString("navigator." + i + ".material");
            ItemStack navItem = new ItemBuilder(Material.getMaterial(material)).setDisplayName("§r" + name).build();

            navInv.setItem(i-1, navItem);
            navPos.put(name, loc);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player) || e.getCurrentItem() == null) return;
        Player plr = (Player) e.getWhoClicked();
        if (JoinQuitListener.getSpawn() == null) return;
        if (!JoinQuitListener.getSpawn().getWorld().equals(plr.getWorld())) return;
        if (plr.getGameMode().equals(GameMode.CREATIVE) && plr.hasPermission("lobby.build")) return;
        Location tpLoc = navPos.get(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
        if(tpLoc != null){
            plr.teleport(tpLoc);
            plr.playSound(plr.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        }

        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (JoinQuitListener.getSpawn() == null) return;
        if (!JoinQuitListener.getSpawn().getWorld().equals(e.getPlayer().getWorld())) return;
        if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE) && e.getPlayer().hasPermission("lobby.drop")) return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onNavClick(PlayerInteractEvent e) {
        if (JoinQuitListener.getSpawn() == null) return;
        if (!JoinQuitListener.getSpawn().getWorld().equals(e.getPlayer().getWorld())) return;
        if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
        if (e.getItem() == null) return;

        if (e.getItem().getType().equals(Material.COMPASS) && e.getItem().getItemMeta().getDisplayName().equals(ChatColor.stripColor(Main.navigatorName))) {
            e.getPlayer().openInventory(navInv);
        }
    }
}
