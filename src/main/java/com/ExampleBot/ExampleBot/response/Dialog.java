package com.ExampleBot.ExampleBot.response;

import lombok.Getter;


@Getter
public enum Dialog {

    COMMAND("!명령어"),
    COMMAND_ANSWER("명령어에는 다음과같은 명령어들이 있습니다.\n !명령어: 명령어를 볼 수 있습니다. \n !멘션: 멘션 예시를 확인할 수 있습니다."),

    HELLO("예제 봇"),
    HELLO_ANSWER("안녕하세요 무엇을 도와드릴까요? 예제 봇입니다."),

    MENTION("!멘션"),
    MENTION_ANSWER("님 안녕하세요! 멘션 예시입니다!");




    private final String value;

    Dialog(String value){
        this.value = value;
    }

}

