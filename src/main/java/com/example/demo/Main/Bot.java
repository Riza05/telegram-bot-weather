package com.example.demo.Main;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

@Component
public class Bot extends TelegramLongPollingBot {
    @Value("${name.bot}")
    private String USER_NAME;
    @Value("${token.bot}")
    private String USER_TOKEN;
    @Value("${commands.bot}")
    private String[] COMMANDS;
    private ArrayList<String> arrayList = new ArrayList<>();
    private String msg;
    private Message message;

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
        if (update.getMessage() != null && update.getMessage().hasText()) {
            String id = String.valueOf(update.getMessage().getChatId());

            if (update.getMessage().getText().equals("/start")) {
                execute(new SendMessage(id, "Привет,я бот который позволяет узнать погоду.\n" +
                        "Чтобы начать напиши:/weather\n" +
                        "Если знать что я умею,напиши:/help\n"));
            } else if (update.getMessage().getText().equals("/help")) {
                execute(new SendMessage(id, "Запустить бота:/start\n" +
                        "Помощь:/help\n" +
                        "Узнать погоду:/weather"));
            } else if (update.getMessage().getText().equals("/weather")) {
                execute(new SendMessage(id, "Напишите город в котором хотите узнать погоду"));
            } else if (update.getMessage().getText().equals("москва")) {
                execute(new SendMessage(id, "Погода хорошая"));
            }
        }
    }
}
