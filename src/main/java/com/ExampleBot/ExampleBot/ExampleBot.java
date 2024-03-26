package com.ExampleBot.ExampleBot;

import com.ExampleBot.ExampleBot.command.*;
import com.ExampleBot.ExampleBot.lavaplayer.music.Play;
import com.ExampleBot.ExampleBot.lavaplayer.music.*;
import com.ExampleBot.ExampleBot.response.Response;
import com.ExampleBot.ExampleBot.token.Token;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.security.auth.login.LoginException;


@EnableScheduling
@SpringBootApplication
public class ExampleBot extends ListenerAdapter {

	@Autowired
	private Token token;

	public static void main(String[] args) throws LoginException {
		ApplicationContext context = SpringApplication.run(ExampleBot.class, args);
		ExampleBot bot = context.getBean(ExampleBot.class);
		bot.startBot();
	}

	public void startBot() throws LoginException{
		JDA jda = JDABuilder.createDefault(token.getBotToken())
			.setActivity(Activity.playing("생각"))
			.enableIntents(GatewayIntent.MESSAGE_CONTENT)
			.build();
		jda.addEventListener(new Response());
		jda.addEventListener(new Listeners());



//		jda.addEventListener(new IntCommand());
//		jda.addEventListener(new StringCommand());
//		jda.addEventListener(new ChannelCommand());
//		jda.addEventListener(new UserCommand());
		CommandManager commandManager = new CommandManager();
		commandManager.add(new ChannelCommand());
		commandManager.add(new IntCommand());
		commandManager.add(new UserCommand());
		commandManager.add(new StringCommand());
		commandManager.add(new Embed());
		commandManager.add(new Buttons());
		commandManager.add(new Modals());
		commandManager.add(new Staff());
		commandManager.add(new UnStaff());
		commandManager.add(new Ban());
		commandManager.add(new Mute());
		commandManager.add(new UnMute());


		commandManager.add(new Play());
		commandManager.add(new Skip());
		commandManager.add(new NowPlaying());
		commandManager.add(new Queue());
		commandManager.add(new Stop());
		commandManager.add(new Repeat());


		jda.addEventListener(commandManager);

	}

}
