package at.jojokobi.restartutil;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class RestartHandler implements Listener {
	
	private int timer = 0;
	private int interval = 0;

	public RestartHandler(Plugin plugin, int interval) {
		super();
		this.interval = interval;
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
			timer++;
			if (timer >= this.interval) {
				if (Bukkit.getOnlinePlayers().isEmpty()) {
					Bukkit.broadcastMessage("Restarting server now!");
					Bukkit.spigot().restart();
				}
				timer = 0;
			}
			else if (timer >= this.interval - 1) {
				Bukkit.broadcastMessage("Restarting server in one minute!");
			}
		}, 20 * 60, 20 * 60);
	}
	
	

}
