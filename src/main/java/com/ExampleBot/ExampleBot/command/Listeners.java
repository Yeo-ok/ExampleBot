package com.ExampleBot.ExampleBot.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import org.jetbrains.annotations.NotNull;

public class Listeners extends ListenerAdapter {

    @Override
   public void onButtonInteraction(@NotNull ButtonInteractionEvent event){
        if(event.getButton().getId().equals("yes-button")){
            event.reply("맞습니다 정답이에요.").queue();
        }
        else if(event.getButton().getId().equals("no-button")){
            event.reply("틀렸습니다.").queue();
        }
        //대답 이후 삭제
        event.getMessage().delete().queue();
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if(event.getModalId().equals("예제 모달")) {
            ModalMapping example1Value = event.getValue("example1-field");
            ModalMapping example2Value = event.getValue("example2-field");
            ModalMapping example3Value = event.getValue("example3-field");

            String example1 = example1Value.getAsString();
            String example3 = example3Value.getAsString();

            String example2;
            if (example2Value.getAsString().isBlank()) {
                example2 = "비어 있음";
            } else {
                example2 = example2Value.getAsString();
            }


            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle(example1);
            builder.setDescription("이것은 예제 1의 설명입니다." + example1);
            builder.addField("예제 1", example1, false);
            builder.addField("예제 2", example2, false);
            builder.addField("예제 3", example3, false);
            event.replyEmbeds(builder.build()).queue();
        }
    }
}
