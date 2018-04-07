package org.golde.java.discordbot.arcanenetwork.cmds;

import org.golde.java.discordbot.arcanenetwork.ArcaneBot;
import org.golde.java.discordbot.arcanenetwork.helpers.ArcaneException;
import org.golde.java.discordbot.arcanenetwork.helpers.Channels;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class CommandChangelog extends DiscordCommand{

	public CommandChangelog(ArcaneBot bot) throws ArcaneException {
		super(bot, "changelog");
	}

	@Override
	public void execute(IUser user, IChannel channel, IMessage message, String[] args) {
		if(hasRole(user, "Developer") || hasRole(user, "Owner")) {
			getDiscordBot().getDiscord().getChannelByID(Channels.CHANGELOG).sendMessage("@everyone NEW BOT VERSION! Here's what changed: " + joinMessageStrings(0, args, " "));
		} 
		else {
			channel.sendMessage(user.mention() + NO_PERM);
		}
	}

	public static String joinMessageStrings(int start, String[] list, String conjunction) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for(int i = start; i < list.length; ++i)
		{
			String item = list[i];

			if (first)
				first = false;
			else
				sb.append(conjunction);
			sb.append(item);
		}
		return sb.toString();
	}

}
