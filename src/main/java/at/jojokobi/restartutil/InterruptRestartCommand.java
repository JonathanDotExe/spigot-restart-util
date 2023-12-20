package at.jojokobi.restartutil;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class InterruptRestartCommand implements CommandExecutor {
	
	public static final String COMMAND_NAME = "interruptrestart";
	
	private boolean needsOp;
	private RestartHandler restartHandler;

	public InterruptRestartCommand(boolean needsOp, RestartHandler restartHandler) {
		super();
		this.needsOp = needsOp;
		this.restartHandler = restartHandler;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String text, String[] args) {
		if (text.equalsIgnoreCase(COMMAND_NAME)) {
			if (sender.isOp() || !needsOp) {
				restartHandler.reset();
				sender.sendMessage("Current restart timer interrupted. Next restart will be in " + restartHandler.getInterval() + " minutes.");
			}
			else {
				sender.sendMessage("You need operator permissions to execute this command.");
			}
			return true;
		}
		return false;
	}

}
