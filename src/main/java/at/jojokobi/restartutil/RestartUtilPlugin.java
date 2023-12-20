package at.jojokobi.restartutil;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RestartUtilPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		super.onEnable();
		int interval = getConfig().getInt("restartInterval", -1);
		boolean restartWithOnlinePlayers = getConfig().getBoolean("restartWithOnlinePlayers", true);
		boolean needsOp = getConfig().getBoolean("resetartInterruptNeedsOp", false);
		
		//Resttart handler
		RestartHandler restartHandler = new RestartHandler(this, interval, restartWithOnlinePlayers);
		Bukkit.getPluginManager().registerEvents(restartHandler, this);
		
		//Commands
		getCommand(InterruptRestartCommand.COMMAND_NAME).setExecutor(new InterruptRestartCommand(needsOp, restartHandler));
	}

}
