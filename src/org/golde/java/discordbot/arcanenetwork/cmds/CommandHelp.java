package org.golde.java.discordbot.arcanenetwork.cmds;

import java.util.ArrayList;
import java.util.List;

import org.golde.java.discordbot.arcanenetwork.ArcaneBot;
import org.golde.java.discordbot.arcanenetwork.helpers.ArcaneException;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class CommandHelp extends DiscordCommand {

	private List<String> msg = new ArrayList<String>();
	
	public CommandHelp(ArcaneBot bot) throws ArcaneException {
		super(bot, "help");
		
		//I really should have used a Embeded Builder but what ever, this works
		
		msg.add("**General Commands**");
		msg.add(" ");
		msg.add("  1) ~help - Sends this message");
		msg.add(" ");
		msg.add("**Administrator Commands **");
		msg.add(" ");
		msg.add("  1) ~purge - Purges as many messages as discord can handel in the given channel");
		msg.add(" ");
		
	}

	@Override
	public void execute(IUser user, IChannel channel, IMessage message, String[] args) {
		
		StringBuilder sb = new StringBuilder();
		for (String s : msg) {
		    sb.append(s);
		    sb.append("\n");
		}
		
		channel.sendMessage(sb.toString());
		
	}

}
