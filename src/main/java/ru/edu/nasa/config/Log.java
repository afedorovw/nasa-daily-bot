package ru.edu.nasa.config;

import ru.edu.nasa.Main;
import ru.edu.nasa.bot.MyTelegramBot;

public interface Log {

    org.slf4j.Logger startLog = org.slf4j.LoggerFactory.getLogger(Main.class);
    org.slf4j.Logger messLog = org.slf4j.LoggerFactory.getLogger(MyTelegramBot.class);
}
