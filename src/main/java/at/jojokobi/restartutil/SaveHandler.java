package at.jojokobi.restartutil;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class SaveHandler {
	
	private int timer = 0;
	private int interval = 0;

	public SaveHandler(Plugin plugin, int interval) {
		super();
		this.interval = interval;
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
			timer++;
			if (this.interval > 0 && timer >= this.interval) {
				Bukkit.broadcastMessage("Saving game ...");
				Bukkit.savePlayers();
				for (World world : Bukkit.getWorlds()) {
					world.save();
				}
				Bukkit.broadcastMessage("Finished saving ...");
			}
		}, RestartHandler.TICKS_PER_MINUTE, RestartHandler.TICKS_PER_MINUTE);
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
	
}
