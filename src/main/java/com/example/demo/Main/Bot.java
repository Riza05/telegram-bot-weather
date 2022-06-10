package com.example.demo.Main;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
public class Bot extends TelegramLongPollingBot {
    @Value("${name.bot}")
    private String USER_NAME;
    @Value("${token.bot}")
    private String USER_TOKEN;
    @Autowired
    private Button button;

    @Override
    public String getBotUsername() {
        return USER_NAME;
    }

    @Override
    public String getBotToken() {
        return USER_TOKEN;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        String id = String.valueOf(update.getMessage().getChatId());

        if (update.getMessage() != null && update.getMessage().hasText()) {
            if (message.equals("/start")) {
                execute(button.sendMessageReplyKey(id));

            } else if (message.equals("Помощь")) {
                execute(new SendMessage(id, "На выбор есть 4 варианта кнопок которые выдают погоду.\n" +
                        "Чтобы узнать на завтра нажмите:'Погода на сегодня'\n" +
                        "Чтобы узнать на завтра нажмите:'Погода на завтра'\n" +
                        "Чтобы узнать на завтра нажмите:'Погода через 5 дней'\n" +
                        "Чтобы узнать на завтра нажмите:'Погода через 7 дней'"));

            } else if (message.equals("Погода на сегодня")) {
                execute(new SendMessage(id, "Погода на сегодня"));

            } else if (message.equals("Погода на завтра")) {
                execute(new SendMessage(id, "Погода на завтра"));

            } else if (message.equals("Погода через 5 дней")) {
                execute(new SendMessage(id, "Погода через 5 дней"));

            } else if (message.equals("Погода через 7 дней")) {
                execute(new SendMessage(id, "Погода через 7 дней"));
            }
        }
    }
}
