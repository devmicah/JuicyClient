package dev.micah.juicyclient.discord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

public class DiscordRP {

	private static DiscordRP instance = new DiscordRP();
	
	public static DiscordRP getInstance() {
		return instance;
	}
	
	private boolean running = true;
	private long created = 0;
	
	public void start() {
		this.created = System.currentTimeMillis();
		
		DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
			@Override
			public void apply(DiscordUser arg0) {
				System.out.println("Connected! " + arg0.username);
			}
		}).build();
		DiscordRPC.discordInitialize("803394989140213820", handlers, true);
		
		new Thread("Discord Callback") {
			@Override
			public void run() {
				while (running) {
					DiscordRPC.discordRunCallbacks();
				}
			}
		}.start();
	}
	
	public void shutdown() {
		running = false;
		DiscordRPC.discordShutdown();
	}
	
	public void update(String line1, String line2) {
		DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(line2);
		b.setBigImage("logo-1000", "");
		b.setDetails(line1);
		b.setStartTimestamps(created);
		
		DiscordRPC.discordUpdatePresence(b.build());
	}
	
}
