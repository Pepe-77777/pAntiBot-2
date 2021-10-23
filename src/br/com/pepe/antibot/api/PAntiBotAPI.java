package br.com.pepe.antibot.api;

import br.com.pepe.antibot.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PAntiBotAPI {

    public static void setInVerification(String Name) {

        Player t = Bukkit.getPlayerExact(Name);

        Main.inAntiBot.put(t, t.getName());
        Main.kickedAntiBot.remove(t);
        Main.afterAntiBot.remove(t);

    }

    public static void setAfterVerification(String Name) {

        Player t = Bukkit.getPlayerExact(Name);

        Main.inAntiBot.remove(t);
        Main.afterAntiBot.put(t, t.getName());
        Main.kickedAntiBot.remove(t);

    }

}
