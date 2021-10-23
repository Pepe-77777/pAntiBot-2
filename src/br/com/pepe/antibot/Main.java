package br.com.pepe.antibot;

import br.com.pepe.antibot.commands.AntiBot;
import br.com.pepe.antibot.commands.ForceLogin;
import br.com.pepe.antibot.commands.Profile;
import br.com.pepe.antibot.events.Events;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {

    public static Main plugin = null;
    public static Map<Player, String> fly = new HashMap<>();
    public static Map<Player, String> afterAntiBot = new HashMap<>();
    public static Map<Player, String> inAntiBot = new HashMap<>();
    public static Map<Player, String> kickedAntiBot = new HashMap<>();
    public String prefix = ChatColor.RED + "" + ChatColor.BOLD + "pAntiBot 2 " + ChatColor.GRAY + "" + ChatColor.BOLD + "-> ";
    PluginManager pm = Bukkit.getPluginManager();

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "Plugin On!");
        plugin = this;

        registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "Plugin Off!");


    }

    private void registerCommands() {

        getCommand("pAntiBot").setExecutor(new AntiBot());
        getCommand("ForceLogin").setExecutor(new ForceLogin());
        getCommand("Profile").setExecutor(new Profile());

    }

    private void registerEvents() {

        pm.registerEvents(new Events(), this);

    }

}
