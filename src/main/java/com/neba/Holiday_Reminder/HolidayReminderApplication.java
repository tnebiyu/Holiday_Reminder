package com.neba.Holiday_Reminder;
import com.neba.Holiday_Reminder.repo.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.neba.Holiday_Reminder")
public class HolidayReminderApplication {

	public static void main(String[] args) {
		try {
			ApplicationContext context = SpringApplication.run(HolidayReminderApplication.class, args);

			UserRepo userRepo = context.getBean(UserRepo.class);

			HolidayBot bot = new HolidayBot(userRepo);

			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(bot);

			System.out.println("Bot started successfully");
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
