package net.levss.chat.listeners;

import java.util.ArrayList;

import net.levss.chat.Main;
import net.levss.chat.commands.Chat;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;

public class Slow implements Listener
{
	Main plugin;
	private ArrayList<String> MUTED;

	public Slow(Main plugin)
	{
		
		this.MUTED = new ArrayList();
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}

	@EventHandler
	public void Slow(final AsyncPlayerChatEvent e)
	{
		
		Player p = e.getPlayer();
		
		if ((!p.hasPermission("LChat.slow")) && (!Chat.muteall))
		{
			if (this.MUTED.contains(p.getName()))
			{
				e.setCancelled(true);
				String msg = "§7Kolejna wiadomosc mozesz napisac dopiero za " + "§c20 sekund" + "§7!";
				p.sendMessage(msg);
			}
			else
			{
				this.MUTED.add(p.getName());
				this.plugin.getServer().getScheduler().runTaskLater(this.plugin, new Runnable()
				{
					public void run()
					{
						Slow.this.MUTED.remove(e.getPlayer().getName());
					}
				}, 400L);
			}
		}
	}
}
