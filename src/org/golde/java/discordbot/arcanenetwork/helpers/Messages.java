package org.golde.java.discordbot.arcanenetwork.helpers;

import sx.blah.discord.handle.obj.IUser;

//{user_mention}
//{user}
public enum Messages {

	WELCOMER_JOIN("Welcome {user_mention} to the ArcaneNetwork! Enjoy your stay!"),
	WELCOMER_Leave("We're sorry to see you go {user_mention}. Come again soon!"),
	;
	
	private final String msg;
	Messages(String msg){
		this.msg = msg;
	}
	
	public String format(IUser user) {
		return msg.replace("{user_mention}", user.mention()).replace("{user}", user.getName());
	}
	
}
