package org.golde.java.discordbot.arcanenetwork;

public class Main {
	
	private static ArcaneBot bot;
	
	public static void main(String[] args) throws Exception {
		bot = new ArcaneBot();
		bot.start();
		Runtime.getRuntime().addShutdownHook(new Thread() {
	      public void run() {
	    	  bot.stop();
	      }
	    });
	}

}
