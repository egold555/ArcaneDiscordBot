package org.golde.java.discordbot.arcanenetwork.cmds;

import org.golde.java.discordbot.arcanenetwork.ArcaneBot;
import org.golde.java.discordbot.arcanenetwork.helpers.ArcaneException;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class JokeCommand extends DiscordCommand{

	public CommandTest(ArcaneBot bot) throws ArcaneException {
		super(bot, "joke");
	}

	@Override
	public void execute(IUser user, IChannel channel, IMessage message, String[] args) {
		channel.sendMessage("Here's a joke! You! Lol, jk.");
	}

}
