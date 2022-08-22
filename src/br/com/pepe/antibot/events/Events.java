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
import org.bukkit.scheduler.BukkitRunnable;

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
        Main.attempts.put(p, 0);
        Main.kick.put(p, false);

        p.sendMessage(ChatColor.RED + "Você tem " + ChatColor.GREEN + "3" + ChatColor.RED + " tentativas.");

        new BukkitRunnable(){
            @Override
            public void run(){

                if (Main.attempts.get(p) >= 3) {

                    p.kickPlayer(ChatColor.RED + "Você tentou muitas vezes.");
                    this.cancel();

                }

            }
        }.runTaskTimer(Main.plugin, 0L, 1L);

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
                Main.attempts.put(p, Main.attempts.get(p)+1);
                p.sendMessage(ChatColor.RED + "Você perdeu " + ChatColor.GREEN + "1" + ChatColor.RED + " tentativa.");

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
            p.sendMessage(ChatColor.RED + "Código errado!");
            Main.attempts.put(p, Main.attempts.get(p)+1);
            p.sendMessage(ChatColor.RED + "Você perdeu " + ChatColor.GREEN + "1" + ChatColor.RED + " tentativa.");

        }

    }

}
