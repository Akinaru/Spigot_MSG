package fr.akinaru.bungeemessaging;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Listeners implements Listener {

    @EventHandler
    public void PlayerDisconnect(PlayerDisconnectEvent e){
        ProxiedPlayer player = (ProxiedPlayer) e.getPlayer();
        if(Bungeemessaging.message.containsKey(player)){ //SI LE JOUEUR FAIT PARTIT DE LA LISTE DES REPONSES
            ProxiedPlayer target = (ProxiedPlayer) Bungeemessaging.message.get(player); //ONT DEFINIT CELUI A QUI IL PEUT REPONDRE
            if(player.equals(Bungeemessaging.message.get(target))){ //SI CELUI A QUI PEUT REPONDRE SONT TARGET EST LE JOUEUR
                Bungeemessaging.message.remove(target); //ON SUPRIME LE TARGET DE LA LISTE
            }
            Bungeemessaging.message.remove(player); //ON SUPPRIME LE JOUEUR DE LA LISTE
        }

    }

}
