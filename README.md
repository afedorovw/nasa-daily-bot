## Telegram бот для получения фото дня NASA

![Telegram](https://img.shields.io/badge/Telegram-2CA5E0?style=for-the-badge&logo=telegram&logoColor=white)


При использовании следующих команд, бот присылает ссылку на фото/видео дня, опубликованного на сайте NASA.

| Команда          | Описание                       |
|------------------|--------------------------------|
| /give            | получить картинку дня          |
| /date гггг-мм-дд | картинка дня за указанную дату |

---

Используются следующие переменные для запуска:

* _bot_name_ - наименование бота
* _bot_token_ - токен бота
* _nasa_key_ - одно из следующих значений:
  * подставить `DEMO_KEY`
  * или личный токен, доступный после [генерации](https://api.nasa.gov/)

Локальный запуск:

```
mvn spring-boot:run -Dspring-boot.run.arguments=" \
--bot_name=***** \
--bot_token=***** \
--nasa_key=*****"
```


<p align="center">
<code><img width="10%" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg" alt=""></code>
<code><img width="10%" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/maven/maven-original.svg" alt=""></code>
</p>
