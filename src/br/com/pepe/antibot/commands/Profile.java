package br.com.pepe.antibot.commands;

import br.com.pepe.antibot.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Profile implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player)) {

            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "");
            return true;

        }

        Player p = (Player) commandSender;

        if(p.hasPermission("antibot")) {

            if(args.length < 1) {

                p.sendMessage(ChatColor.GRAY + " /profile (nick)" + ChatColor.WHITE + " [--nogui | -nogui]");
                return true;

            } else {

                Player t = Bukkit.getPlayerExact(args[0]);

                if(t == null) {

                    p.sendMessage(ChatColor.RED + "Não foi possivel encontrar este jogador.");
                    return true;

                } else if (args[0].equals("-nogui") | args[0].equals("--nogui")){



                    return true;

                } else {

                    ItemStack IP = new ItemStack(Material.STAINED_CLAY, 1, (byte)5);
                    ItemMeta meta = IP.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "IP: " + ChatColor.GREEN + t.getAddress());
                    IP.setItemMeta(meta);

                    ItemStack UUID = new ItemStack(Material.STAINED_CLAY, 1, (byte)4);
                    ItemMeta meta2 = UUID.getItemMeta();
                    meta2.setDisplayName(ChatColor.RED + "UUID: " + ChatColor.GREEN + t.getUniqueId());
                    UUID.setItemMeta(meta2);

                    ItemStack IPHostName = new ItemStack(Material.STAINED_CLAY, 1, (byte)14);
                    ItemMeta meta3 = IPHostName.getItemMeta();
                    meta3.setDisplayName(ChatColor.RED + "X/Y/Z: " + ChatColor.GREEN + t.getLocation().getBlockX() + "/" + t.getLocation().getBlockY() + "/" + t.getLocation().getBlockZ());
                    IPHostName.setItemMeta(meta3);

                    Inventory gui = Bukkit.createInventory(null, 9, "AntiBot");

                    gui.setItem(2, IP);
                    gui.setItem(4, UUID);
                    gui.setItem(6, IPHostName);

                    p.openInventory(gui);

                    return true;

                }

            }

        }

        return true;

    }

}
