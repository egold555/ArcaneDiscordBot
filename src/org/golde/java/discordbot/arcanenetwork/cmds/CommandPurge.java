package org.golde.java.discordbot.arcanenetwork.cmds;

import org.golde.java.discordbot.arcanenetwork.ArcaneBot;
import org.golde.java.discordbot.arcanenetwork.helpers.ArcaneException;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class CommandPurge extends DiscordCommand {

	public CommandPurge(ArcaneBot bot) throws ArcaneException {
		super(bot, "purgemsg");
	}

	@Override
	public void execute(IUser user, IChannel channel, IMessage message, String[] args) {
		if(hasRole(user, "Developer") || hasRole(user, "Owner")) {
			int amount = channel.bulkDelete().size();
			channel.sendMessage(user.mention() + " cleared " + amount + " messages from " + channel.getName());
		} 
		else {
			channel.sendMessage(user.mention() + NO_PERM);
		}
	}
	

}
