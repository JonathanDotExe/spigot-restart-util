package at.jojokobi.restartutil;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RestartUtilPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		super.onEnable();
		int interval = getConfig().getInt("restartInterval", -1);
		int saveInterval = getConfig().getInt("saveInterval", -1);

		boolean restartWithOnlinePlayers = getConfig().getBoolean("restartWithOnlinePlayers", true);
		boolean needsOp = getConfig().getBoolean("resetartInterruptNeedsOp", false);
		
		//Restart handler
		RestartHandler restartHandler = new RestartHandler(this, interval, restartWithOnlinePlayers);
		Bukkit.getPluginManager().registerEvents(restartHandler, this);
		
		//Save handler
		new SaveHandler(this, saveInterval);
		
		//Commands
		getCommand(InterruptRestartCommand.COMMAND_NAME).setExecutor(new InterruptRestartCommand(needsOp, restartHandler));
	}

}
