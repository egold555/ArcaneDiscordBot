package org.golde.java.discordbot.arcanenetwork.cmds;

import org.golde.java.discordbot.arcanenetwork.ArcaneBot;
import org.golde.java.discordbot.arcanenetwork.helpers.ArcaneException;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public abstract class DiscordCommand {

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
	
}
