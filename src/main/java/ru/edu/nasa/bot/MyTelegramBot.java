package ru.edu.nasa.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.edu.nasa.service.Utils;

import java.io.IOException;

@Slf4j
@Component
public class MyTelegramBot extends TelegramLongPollingBot
        implements Commands {

    @Value("${bot.name}")
    private String botUsername;
    @Value("${bot.uri}")
    private String nasa_uri;
    private long chatId;

    public MyTelegramBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId();
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
                        throw new IllegalArgumentException(e);
                    }
                    break;
                case "/date":
                    String date = separatedAnswer[1];
                    try {
                        sendMessage(Utils.getUrl(nasa_uri + "&date=" + date));
                    } catch (IOException e) {
                        throw new IllegalArgumentException(e);
                    }
                    break;
                default:
                    sendMessage(def);
            }
        }
    }

    private void sendMessage(String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        try {
            execute(message);
            log.info("A message has been sent to {}", chatId);
        } catch (TelegramApiException e) {
            log.error("Don't send message {}", chatId, e);
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

}
