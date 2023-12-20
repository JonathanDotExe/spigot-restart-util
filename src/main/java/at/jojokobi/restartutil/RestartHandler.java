package at.jojokobi.restartutil;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class RestartHandler implements Listener {
	
	public static final int TICKS_PER_MINUTE = 20 * 60;
	
	private int timer = 0;
	private int interval = 0;
	private boolean restartWithOnlinePlayers = false;

	public RestartHandler(Plugin plugin, int interval, boolean restartWithOnlinePlayers) {
		super();
		this.interval = interval;
		this.restartWithOnlinePlayers = restartWithOnlinePlayers;
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
			timer++;
			if (this.interval > 0 && timer >= this.interval) {
				if (this.restartWithOnlinePlayers || Bukkit.getOnlinePlayers().isEmpty()) {
					Bukkit.broadcastMessage("Restarting server now!");
					Bukkit.spigot().restart();
				}
				timer = 0;
			}
			else if (this.interval > 0 && timer >= this.interval - 1 && this.restartWithOnlinePlayers) {
				Bukkit.broadcastMessage("Restarting server in one minute, you can use /interruptrestart to prevent this!");
			}
		}, TICKS_PER_MINUTE, TICKS_PER_MINUTE);
	}
	
	public void reset() {
		timer = 0;
	}

	public int getTimer() {
		return timer;
	}

	public int getInterval() {
		return interval;
	}

	public boolean isRestartWithOnlinePlayers() {
		return restartWithOnlinePlayers;
	}
	
}
