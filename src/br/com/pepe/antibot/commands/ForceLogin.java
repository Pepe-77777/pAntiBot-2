package br.com.pepe.antibot.commands;

import br.com.pepe.antibot.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ForceLogin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(!(commandSender instanceof Player)) {

            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "?");
            return true;

        }

        Player p = (Player) commandSender;

        if (p.hasPermission("antibot.ForceLogin")) {

            if(args.length < 1) {

                p.sendMessage(ChatColor.RED + " ");
                p.sendMessage(ChatColor.GRAY + " /forcelogin [nick]");
                p.sendMessage(ChatColor.RED + " ");

            } else {

                Player t = Bukkit.getPlayerExact(args[0]);

                if (t == null) {

                    p.sendMessage(" ");
                    p.sendMessage(ChatColor.RED + "Player não encontrado!");
                    p.sendMessage(" ");

                    return true;

                } else {

                    Main.inAntiBot.remove(t);
                    Main.afterAntiBot.put(t, t.getName());
                    p.sendMessage(ChatColor.RED + "Você tirou " + ChatColor.GREEN + t.getName() + ChatColor.RED + " do AntiBot!");
                    t.sendMessage(ChatColor.RED + "Você foi tirado do AntiBot por " + ChatColor.GREEN + p.getName() + ChatColor.RED + "!");

                }

            }

            return true;

        } else {

            p.sendMessage(ChatColor.RED + "Você não tem permissão!");

            return true;

        }

    }

}
