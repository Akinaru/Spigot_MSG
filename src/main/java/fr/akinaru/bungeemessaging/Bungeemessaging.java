package fr.akinaru.bungeemessaging;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.util.*;

public final class Bungeemessaging extends Plugin {

    public static Map<ProxiedPlayer, ProxiedPlayer> message = new HashMap<ProxiedPlayer, ProxiedPlayer>();

    @Override
    public void onEnable() {
        this.getProxy().getPluginManager().registerCommand(this, new MsgCommand());
        this.getProxy().getPluginManager().registerCommand(this, new MsgResponseCommand());
        this.getProxy().getPluginManager().registerListener(this, new Listeners());
        this.getLogger().info("Plugin enable.");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static List<String> getAllPlayers() {
        List<String> list = new ArrayList<String>();
        for(ProxiedPlayer mat : ProxyServer.getInstance().getPlayers()) {
            list.add(mat.getDisplayName());
        }

        return list;
    }

    public static List<String> getContainedPlayers(String player) {
        List<String> list = new ArrayList<String>();
        for(String mat : getAllPlayers()) {
            if(mat.toLowerCase().startsWith(player.toLowerCase())) {
                list.add(mat);
            }
        }
        return list;
    }

}
