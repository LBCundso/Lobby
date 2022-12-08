package de.lbc.lobby.commands;

import de.lbc.lobby.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.metadata.FixedMetadataValue;

public class VillagerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player plr = (Player) sender;
            Villager villager = (Villager) plr.getWorld().spawnEntity(plr.getLocation(), EntityType.VILLAGER);
            if (args.length == 1) {
                villager.setCustomName(args[0].replaceAll("&", "§"));
                villager.setCustomNameVisible(true);
            }
            if (villager.getCustomName() != null && villager.getCustomName().equals("Teleport") && plr.hasPermission("test.villagermeta")) {
                villager.setMetadata("villagertype", new FixedMetadataValue(Main.getPlugin(), "teleport"));
            }
            villager.setAI(false);
            villager.setVillagerType(Villager.Type.SNOW);
            villager.setAgeLock(true);

        }

        return false;
    }
}
