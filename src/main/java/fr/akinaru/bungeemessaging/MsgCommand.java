package fr.akinaru.bungeemessaging;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class MsgCommand extends Command implements TabExecutor {
    public MsgCommand() {
        super("msg", null, "mp", "w", "tell", "message", "m");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof ProxiedPlayer)) {
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        if( args.length == 0){
            player.sendMessage(new TextComponent("§cTu n'as pas mis de joueur."));
            return;
        }
        if(args.length == 1){
            player.sendMessage(new TextComponent("§cTu n'as pas mis de message."));
            return;
        }
        else{
            String destinataire = args[0];
            if(ProxyServer.getInstance().getPlayer(destinataire) == null){
                player.sendMessage(new TextComponent("§cCe joueur n'est pas connecté..."));
                return;
            }else{
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(destinataire);
                if(target == player){
                    player.sendMessage(new TextComponent("§cTu ne peux pas parler avec toi même... :c"));
                    return;
                }
                String msg = "";
                for (int i = 1; i != args.length; i++) msg += args[i] + " ";
                player.sendMessage(new TextComponent("§8Envoyé à §3"+ target.getName() +"§8: §7"+msg));
                target.sendMessage(new TextComponent("§8Reçu de §3"+ player.getName() +"§8: §7"+msg));
                Bungeemessaging.message.put(player, target);
                Bungeemessaging.message.put(target, player);
            }
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        if (args.length >= 1) {
            List<String> list = Bungeemessaging.getContainedPlayers(args[0]);
            return list;
        }
        return null;
    }



}
