package com.ExampleBot.ExampleBot.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;


import java.util.List;

public class Buttons implements Command{
    @Override
    public String getName() {
        return "버튼";
    }

    @Override
    public String getDescription() {
        return "버튼 예제입니다.";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("버튼 예제 질문");
        builder.setDescription("1 + 1 은 2인가요?");

        Button yesButton = Button.danger("yes-button", "네");
        Button noButton = Button.danger("no-button", "아니오");

        event.reply(" ")
                .addActionRow(yesButton, noButton)
                .addEmbeds(builder.build())
                .queue();
    }
}
