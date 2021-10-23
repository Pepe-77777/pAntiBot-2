package br.com.pepe.antibot.events;

import br.com.pepe.antibot.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Random;

public class Events implements Listener {

    public String VerString;

    public void newVerification() {

        Random ver = new Random();

        VerString = "" + ver.nextInt(10000);

        this.VerString = VerString;

    }

    public String getVerification() {

        return VerString;

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        Main.inAntiBot.put(p, p.getName());

        newVerification();

        p.sendMessage(ChatColor.RED + "Por Favor, Digite " + ChatColor.GREEN + getVerification() + ChatColor.RED + " para nos verificarmos!");

    }

    @EventHandler
    public void onPlayerMessage(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();

        if (Main.inAntiBot.containsKey(p)) {

            if (e.getMessage().equals(getVerification())) {

                e.setCancelled(true);
                Main.inAntiBot.remove(p, p.getName());
                Main.afterAntiBot.put(p, p.getName());
                p.sendMessage(ChatColor.GREEN + "Verificado com sucesso!");

            } else {

                e.setCancelled(true);
                Main.kickedAntiBot.put(p, p.getName());
                p.sendMessage(ChatColor.RED + "Código errado!");

            }

        }

    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        Player p = e.getPlayer();

        if (Main.inAntiBot.containsKey(p)) {

            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "Por Favor, Digite " + ChatColor.GREEN + getVerification() + ChatColor.RED + " para nos verificarmos!");

        }

    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {

        Player p = e.getPlayer();

        if (Main.inAntiBot.containsKey(p)) {

            e.setCancelled(true);

        }

    }

}
