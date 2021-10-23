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

import java.util.Arrays;

public class AntiBot implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player)) {

            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Passados pelo AntiBot: " + ChatColor.GREEN + Main.afterAntiBot.size());
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "No AntiBot: " + ChatColor.GREEN + Main.inAntiBot.size());
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Barrados pelo AntiBot: " + ChatColor.GREEN + Main.kickedAntiBot.size());

            return true;

        }

        Player p = (Player) commandSender;

        if (p.hasPermission("antibot")) {

            if (args.length < 1) {

                Inventory gui = Bukkit.createInventory(null, 9, "AntiBot");

                ItemStack PassadosAntiBot = new ItemStack(Material.STAINED_CLAY, 1, (byte) 5);
                ItemMeta meta = PassadosAntiBot.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "Passados pelo AntiBot: " + ChatColor.GREEN + Main.afterAntiBot.size());
                PassadosAntiBot.setItemMeta(meta);

                ItemStack noAntiBot = new ItemStack(Material.STAINED_CLAY, 1, (byte) 4);
                ItemMeta meta2 = noAntiBot.getItemMeta();
                meta2.setDisplayName(ChatColor.RED + "No AntiBot: " + ChatColor.GREEN + Main.inAntiBot.size());
                noAntiBot.setItemMeta(meta2);

                ItemStack BarradosAntiBot = new ItemStack(Material.STAINED_CLAY, 1, (byte) 14);
                ItemMeta meta3 = PassadosAntiBot.getItemMeta();
                meta3.setDisplayName(ChatColor.RED + "Barrados pelo AntiBot: " + ChatColor.GREEN + Main.kickedAntiBot.size());
                BarradosAntiBot.setItemMeta(meta3);

                gui.setItem(2, PassadosAntiBot);
                gui.setItem(4, noAntiBot);
                gui.setItem(6, BarradosAntiBot);

                //This opens the inventory
                p.openInventory(gui);

                return true;

            } else if (args[0].equals("--nogui") || args[0].equals("-nogui")) {

                p.sendMessage(ChatColor.RED + "Passados pelo AntiBot: " + ChatColor.GREEN + Main.afterAntiBot.size());
                p.sendMessage(ChatColor.RED + "No AntiBot: " + ChatColor.GREEN + Main.inAntiBot.size());
                p.sendMessage(ChatColor.RED + "Barrados pelo AntiBot: " + ChatColor.GREEN + Main.kickedAntiBot.size());

                return true;

            } else {

                p.sendMessage(ChatColor.GRAY + " /antibot " + ChatColor.WHITE + "[--nogui | -nogui]");

                return true;

            }

        }

        return true;

    }
}
