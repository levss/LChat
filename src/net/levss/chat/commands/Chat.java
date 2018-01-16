package net.levss.chat.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;

import net.levss.chat.Main;

public class Chat implements CommandExecutor, Listener
{
	
	Main plugin;
	final HashMap<Player, Boolean> muted;
	public static boolean muteall = false;
	

	public Chat(Main plugin)
	{
		
		this.muted = new HashMap();
		this.plugin = plugin;
		this.plugin.getCommand("chat").setExecutor(this);
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}

	public void muteall()
	{
		
		muteall = false;
		Bukkit.broadcastMessage("§c» " + "§7Czat zostal wlaczony!");
		
	}

	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent event)
	{
		
		Player chatter = event.getPlayer();
		
		if (!this.muted.containsKey(chatter))
		{
			this.muted.put(chatter, Boolean.valueOf(false));
		}
		if ((!chatter.hasPermission("LChat.pisanie")) && ((muteall) || (((Boolean) this.muted.get(chatter)).booleanValue())))
		{
			event.setCancelled(true);
			chatter.sendMessage("§c» " + "§7Czat jest wylaczony!");
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		
		Player p = (Player) sender;
		
		if (!p.hasPermission("LChat.chat"))
		{
			p.sendMessage("§c§lBrak uprawnien! LChat.chat");
			return true;
		}
		if (args.length == 0)
		{
			p.sendMessage("");
			p.sendMessage("§e§l### SERVER.PL CZAT! ###");
			p.sendMessage("                    ");
			p.sendMessage("§c» " + "§3/chat on" + "§8 - " + "§7Wlacza czat");
			p.sendMessage("§c» " + "§3/chat off" + "§8 - " + "§7Wylacza czat");
			p.sendMessage("§c» " + "§3/chat cc" + "§8 - " + "§7Czysci czat");
			p.sendMessage("");
			p.sendMessage("§e§l### SERVER.PL CZAT! ###");
			p.sendMessage("");
			return true;
		}
		if ((args.length == 1) && (args[0].equalsIgnoreCase("on")))
		{
			if (!muteall)
			{
				p.sendMessage("§c» " + "§7Musisz najpierw wylaczyc czat!");
				return true;
			}
			muteall = false;
			Bukkit.broadcastMessage("§c» " + "§7Chat zostal wylaczony przez " + "§c" + p.getDisplayName() + "§7!");
		}
		if ((args.length == 1) && (args[0].equalsIgnoreCase("off")))
		{
			if (muteall)
			{
				p.sendMessage("§c» " + "§7Musisz najpierw wlaczyc czat!");
				return true;
			}
			muteall = true;
			Bukkit.broadcastMessage("§c» " + "§7Chat zostal wylaczony przez " + "§c" + p.getDisplayName() + "§7!");
		}
		if ((args.length == 1) && (args[0].equalsIgnoreCase("cc")))
		{
			for (int i = 0; i < 100; i++)
			{
				if (i == 95)
				{
					Bukkit.broadcastMessage("§c» " + "§7Chat zostal wyczyszczony przez" + "§c" + p.getDisplayName() + "§7!");
				}
				else
				{
					Bukkit.broadcastMessage(" ");
				}
			}
		}
		return false;
	}
}
