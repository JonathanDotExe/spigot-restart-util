package at.jojokobi.restartutil;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RestartUtilPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		super.onEnable();
		int interval = getConfig().getInt("restartInterval", -1);
		Bukkit.getPluginManager().registerEvents(new RestartHandler(this, interval), this);
	}

}
