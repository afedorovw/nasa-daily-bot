package ru.edu.nasa.bot;

public interface Commands {

    String start = """
            Привет, я бот NASA! Я высылаю ссылки на картинки по запросу.
            Напоминаю, что картинки на сайте NASA обновляются раз в сутки.
            """;

    String help = """
            Доступные команды:
            /give - получить картинку дня
            /date гггг-мм-дд - картинка дня за указанную дату
            """;

    String def = "Я не понимаю :(";
}
