package org.golde.java.discordbot.arcanenetwork;

public class Main {
	
	private static ArcaneBot bot;
	
	public static void main(String[] args) throws Exception {
		bot = new ArcaneBot();
		try {
			bot.start();
		}
		catch(Exception e) {
			log("Exception happend");
			e.printStackTrace();
		}
		Runtime.getRuntime().addShutdownHook(new Thread() {
	      public void run() {
	    	  bot.stop();
	    	  log("Shutdown hook called.");
	      }
	    });
	}
	
	private static void log(String msg) {
		System.out.println("[Sys] " + msg);
	}

}
