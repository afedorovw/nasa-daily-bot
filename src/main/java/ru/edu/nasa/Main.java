package ru.edu.nasa;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.edu.nasa.bot.MyTelegramBot;
import ru.edu.nasa.config.DataBot;
import ru.edu.nasa.config.Log;

public class Main implements Log, DataBot {

    public static void main(String[] args) {

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MyTelegramBot());
            startLog.info("Start app - " + botName);
        } catch (TelegramApiException e) {
            startLog.error("Error app ", e);
        }
    }
}
