package com.ExampleBot.ExampleBot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.util.List;

public class Modals implements Command{
    @Override
    public String getName() {
        return "모달";
    }

    @Override
    public String getDescription() {
        return "예제 모달창이 열릴 것입니다.";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        TextInput example1 = TextInput.create("example1-field", "예제 1", TextInputStyle.SHORT)
                .setRequired(true)
                .setMinLength(1)
                .setMaxLength(50)
                .build();

        TextInput example2 = TextInput.create("example2-field", "예제 2", TextInputStyle.SHORT)
                .setRequired(false)
                .build();

        TextInput example3 = TextInput.create("example3-field", "예제 3", TextInputStyle.PARAGRAPH)
                .setRequired(true)
                .setMinLength(1)
                .setPlaceholder("예시로 아무 글이나 적어보세요.")
                .build();

        Modal modal = Modal.create("예제 모달", "예제 모달 제목")
                .addActionRows(ActionRow.of(example1), ActionRow.of(example2), ActionRow.of(example3))
                .build();

        event.replyModal(modal).queue();
    }


}
