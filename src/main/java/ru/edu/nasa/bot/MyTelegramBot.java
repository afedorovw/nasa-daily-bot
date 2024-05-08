package ru.edu.nasa.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.edu.nasa.config.DataBot;
import ru.edu.nasa.config.Log;
import ru.edu.nasa.service.Utils;

import java.io.IOException;

public class MyTelegramBot extends TelegramLongPollingBot
        implements DataBot, Commands, Log {

    private static final String BOT_USERNAME = botName;
    private static long chat_id;

    public MyTelegramBot() throws TelegramApiException {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            chat_id = update.getMessage().getChatId();
            String answer = update.getMessage().getText();
            String[] separatedAnswer = answer.split(" ");
            String action = separatedAnswer[0];

            switch (action) {
                case "/start":
                    sendMessage(start);
                    break;
                case "/help":
                    sendMessage(help);
                    break;
                case "/give":
                    try {
                        sendMessage(Utils.getUrl(nasa_uri));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "/date":
                    String date = separatedAnswer[1];
                    try {
                        sendMessage(Utils.getUrl(nasa_uri + "&date=" + date));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    sendMessage(def);
            }
        }
    }

    private void sendMessage(String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chat_id);
        message.setText(messageText);
        try {
            execute(message);
            messLog.info("A message has been sent to {}", chat_id);
        } catch (TelegramApiException e) {
            messLog.error("Don't send message " + chat_id, e);
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }
}
