package com.example.demo.Main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {
    @Value("${name.bot}")
    private String USER_NAME;
    @Value("${token.bot}")
    private String USER_TOKEN;
    @Value("${commands.bot}")
    private String[] com;

    @Override
    public String getBotUsername() {
        return USER_NAME;
    }

    @Override
    public String getBotToken() {
        return USER_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.getMessage() != null && update.getMessage().hasText()) {
                long chat_id = update.getMessage().getChatId();
                String qwe = update.getMessage().getText();

                if (qwe.equals(com[0])) {
                    execute(new SendMessage(String.valueOf(chat_id), "Привет,я бот"));
                } else if (qwe.equals(com[1])) {
                    execute(new SendMessage(String.valueOf(chat_id),
                            "Список команд:\n" +
                                    "/start-запустить меня\n" +
                                    "/help-открыть список команд"));
                }
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
