package net.levss.chat;

import net.levss.chat.commands.Chat;
import net.levss.chat.listeners.Pisanie;
import net.levss.chat.listeners.Slow;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	
	Chat chat;
	Slow slow;
	Pisanie pisanie;

	public void onEnable()
	{
		
		this.getLogger().info("LChat - Plugin OFF");		
		this.getLogger().info("https://github.com/levss");
		
		this.chat = new Chat(this);
		this.slow = new Slow(this);
		this.pisanie = new Pisanie(this);
		
	}

	public void onDisable()
	{
		
		this.getLogger().info("LChat - Plugin OFF");		
		this.getLogger().info("https://github.com/levss");
		
	}
}
