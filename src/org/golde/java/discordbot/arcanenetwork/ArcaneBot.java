package org.golde.java.discordbot.arcanenetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.golde.java.discordbot.arcanenetwork.cmds.*;
import org.golde.java.discordbot.arcanenetwork.helpers.*;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserLeaveEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class ArcaneBot {

	
	
	public final String prefix = "~";
	private IDiscordClient bot;
	boolean botIsRunning = false;
	private List<DiscordCommand> cmds = new ArrayList<DiscordCommand>();

	private void registerCommands() throws ArcaneException {
		cmds.add(new CommandHelp(this));
		cmds.add(new JokeCommand(this));
		cmds.add(new CommandPurge(this));
		cmds.add(new CommandAnnounce(this));
	}

	private void tick() {
		
	}
	
	private void onMessage(IUser user, IChannel channel, IMessage message) {
		
	}
	
	@EventSubscriber
	public void onJoin(final UserJoinEvent e) {
		bot.getChannelByID(Channels.ARCANE).sendMessage("Welcome " + e.getUser().mention() + " to the Arcane Network! Enjoy your stay. :)");
	}
	
	@EventSubscriber
	public void onLeave(final UserLeaveEvent e) {
		bot.getChannelByID(Channels.ARCANE).sendMessage("Goodbye, " + e.getUser().mention() + ". Come again soon.. :(");
	}
	
	

	//======= Events and other stuff ========

	
	public void start() throws Exception {

		boolean botIsReady = false;
		ClientBuilder clientBuilder = new ClientBuilder();
		clientBuilder.withToken("NDMyMjI5MzQyMjgyNzc2NTc2.DaqQdg.SLjCAxC0dHhe-FBQGbqfnaXukso");
		bot = clientBuilder.login();
		EventDispatcher dispatcher = bot.getDispatcher();
		if(bot != null && dispatcher != null) {
			botIsRunning = true;
		}

		dispatcher.registerListener(this);

		registerCommands();
		
		for(DiscordCommand cmd:cmds) {
			dispatcher.registerListener(cmd);
		}
		
		while(botIsRunning) {
			if(botIsReady) {
				tick();
				for(DiscordCommand command:cmds) {
					command.tick();
				}
			}
		}

	}
	
	public void stop() {
		log("Stopped: " + new Date());
		for(DiscordCommand cmd:cmds) {
			cmd.shutdown();
		}
		botIsRunning = false;
		bot.dnd();
		bot.logout();
	}
	
	@EventSubscriber
	public void onReadyEvent(ReadyEvent event) {
		
		bot.online("~help");

		for(DiscordCommand command:cmds) {
			command.onReady();
		}

		log("Started: " + new Date());
		
	}

	@EventSubscriber
	public void onMessageEvent(MessageReceivedEvent event) {
		try {
			IMessage message = event.getMessage();
			IUser user = message.getAuthor();
			IChannel channel = message.getChannel();

			if (user.isBot()) return;

			String[] split = message.getContent().split(" ");

			if (split.length >= 1 && split[0].startsWith(prefix)) {

				String command = split[0].replaceFirst(prefix, "");
				String[] args = split.length >= 2 ? Arrays.copyOfRange(split, 1, split.length) : new String[0];
				for(DiscordCommand ac:cmds) {
					if(ac.getCmd().equals(command)) {
						message.delete(); //Delete command as a message ebcause it was a command
						ac.execute(user, channel, message, args);
					}
				}

			} else {
				onMessage(user, channel, message);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	

	public IDiscordClient getDiscord() {
		return bot;
	}
	
	public IGuild getGuild() {
		return bot.getGuildByID(431963123646332938L);
	}
	
	private void log(String msg) {
		System.out.println(msg);
		bot.getChannelByID(Channels.LOGS).sendMessage(msg);
	}
	
	public List<DiscordCommand> getCmds() {
		return cmds;
	}

}
