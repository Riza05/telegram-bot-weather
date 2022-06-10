package com.example.demo.Main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

@Component
public class Button {
    @Value("${commands.buttons}")
    private String[] arr;

    public SendMessage sendMessageReplyKey(String chat_id) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRows.add(keyboardRow1);
        KeyboardButton btnToday = new KeyboardButton();
        btnToday.setText("Погода на сегодня");
        arr[0] = btnToday.getText();
        keyboardRow1.add(arr[0]);

        KeyboardButton btnTomorrow = new KeyboardButton();
        btnTomorrow.setText("Погода на завтра");
        arr[1] = btnTomorrow.getText();
        keyboardRow1.add(arr[1]);

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRows.add(keyboardRow2);
        KeyboardButton btnFiveDay = new KeyboardButton();
        btnFiveDay.setText("Погода через 5 дней");
        arr[2] = btnFiveDay.getText();
        keyboardRow2.add(arr[2]);

        KeyboardButton btnSevenDay = new KeyboardButton();
        btnSevenDay.setText("Погода через 7 дней");
        arr[3] = btnSevenDay.getText();
        keyboardRow2.add(arr[3]);

        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRows.add(keyboardRow3);
        KeyboardButton btnHelp = new KeyboardButton();
        btnHelp.setText("Помощь");
        arr[4] = btnHelp.getText();
        keyboardRow3.add(arr[4]);

        replyKeyboardMarkup.setKeyboard(keyboardRows);

        SendMessage sendMessageRK = new SendMessage();
        sendMessageRK.setText("Привет! Я бот который показывает погоду.\n" +
                "Чтобы понять как я работаю советую ознакомиться с инструкцией.\n" +
                "Чтобы открыть инструкцию нажми на кнопку:'Помощь'");
        sendMessageRK.setChatId(chat_id);


        sendMessageRK.setReplyMarkup(replyKeyboardMarkup);
        return sendMessageRK;
    }
}
