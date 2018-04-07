package org.golde.java.discordbot.arcanenetwork.cmds;

import org.golde.java.discordbot.arcanenetwork.ArcaneBot;
import org.golde.java.discordbot.arcanenetwork.helpers.ArcaneException;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;

public abstract class DiscordCommand {

	public final String NO_PERM = "You do not have permission to use that command!";
	
	private final String theCMD;
	private final ArcaneBot bot;

	public DiscordCommand(ArcaneBot bot, String cmd) throws ArcaneException {
		this.bot = bot;
		if(cmd.contains(" ")) {
			throw new ArcaneException("Command name can not have spaces in it!");
		}

		this.theCMD = cmd;
	}

	public final String getCmd() {
		return theCMD;
	}
	
	public abstract void execute(IUser user, IChannel channel, IMessage message, String[] args);

	public void shutdown() {}
	public void tick() {}
	public void onReady() {};

	public final ArcaneBot getDiscordBot() {
		return bot;
	}
	
	public final boolean hasRole(IUser user, String roleName) {
		for(IRole role : user.getRolesForGuild(bot.getGuild())) {
			if(role.getName().equalsIgnoreCase(roleName)) {
				return true;
			}
		}
		return false;
	}
	
}
