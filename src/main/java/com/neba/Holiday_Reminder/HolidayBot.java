package com.neba.Holiday_Reminder;

import com.neba.Holiday_Reminder.model.User;
import com.neba.Holiday_Reminder.repo.UserRepo;
import com.neba.Holiday_Reminder.response.HolidayResponse;
import com.neba.Holiday_Reminder.response.HolidayResponse2;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class HolidayBot extends TelegramLongPollingBot {
private final UserRepo userRepo;


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
         long chatId= message.getChatId();
            String userName = message.getFrom().getUserName();
            String text = message.getText();

            if (text.startsWith("/start")) {


                saveUser(chatId, userName);
                sendTextMessage(chatId, "Hi " + userName + " Welcome to the Holiday Reminder bot. This bot will notify you daily about holidays in both the USA and Ethiopia.");
                checkAndSendHolidayMessageUsa();
                checkAndSendHolidayMessageEthiopia();


            }
        }

    }
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void checkAndSendHolidayMessageUsa() {// for Calendarific api
        List<User> users = userRepo.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = LocalDate.now().format(formatter);

        for (User user : users) {
            long chatId = user.getChatId();

            String apiUrl = "";

            RestTemplate restTemplate = new RestTemplate();
            HolidayResponse response = restTemplate.getForObject(apiUrl, HolidayResponse.class);

            if (response != null && response.getResponse() != null && response.getResponse().getHolidays() != null) {
                for (HolidayResponse.Holiday holiday : response.getResponse().getHolidays()) {
                    String adjustedDate = holiday.getDate().getIso().split("T")[0];
                    if (today.equals(adjustedDate)) {
                        sendTextMessage(chatId, "Today is " + holiday.getName() + ". Happy holiday!");
                    }
                }
            }
        }
    }
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void checkAndSendHolidayMessageEthiopia() {// for Calendarific api
        List<User> users = userRepo.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = LocalDate.now().format(formatter);

        for (User user : users) {
            long chatId = user.getChatId();

            String apiUrl = "";

            RestTemplate restTemplate = new RestTemplate();
            HolidayResponse response = restTemplate.getForObject(apiUrl, HolidayResponse.class);

            if (response != null && response.getResponse() != null && response.getResponse().getHolidays() != null) {
                for (HolidayResponse.Holiday holiday : response.getResponse().getHolidays()) {
                    String adjustedDate = holiday.getDate().getIso().split("T")[0];
                    if (today.equals(adjustedDate)) {
                        sendTextMessage(chatId, "Today is " + holiday.getName() + ". Happy holiday!");
                    }
                }
            }
        }

    }
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void checkAndSendHolidayMessageUsa2() { // for holiday api
        List<User> users = userRepo.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = LocalDate.now().format(formatter);

        for (User user : users) {
            long chatId = user.getChatId();

            String apiUrl = "${HOLIDAY_API_URL_USA}";


            RestTemplate restTemplate = new RestTemplate();
            HolidayResponse2 response = restTemplate.getForObject(apiUrl, HolidayResponse2.class);

            if (response != null && response.getHolidays() != null) {
                for (HolidayResponse2.Holiday holiday : response.getHolidays()) {
                    String adjustedDate = holiday.getDate().replace("2023", "2024");
                    if (today.equals(adjustedDate)) {
                        sendTextMessage(chatId, "Today is " + holiday.getName() + ". Happy holiday!");
                    }
                }
            }
        }
    }
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void checkAndSendHolidayMessageEthiopia2() {// for holiday api
        List<User> users = userRepo.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = LocalDate.now().format(formatter);

        for (User user : users) {
            long chatId = user.getChatId();

            String apiUrl = "${HOLIDAY_API_URL_ETHIOPIA}";

            RestTemplate restTemplate = new RestTemplate();
            HolidayResponse2 response = restTemplate.getForObject(apiUrl, HolidayResponse2.class);

            if (response != null && response.getHolidays() != null) {
                for (HolidayResponse2.Holiday holiday : response.getHolidays()) {
                    String adjustedDate = holiday.getDate().replace("2023", "2024");
                    if (today.equals(adjustedDate)) {
                        sendTextMessage(chatId, "Today is " + holiday.getName() + ". Happy holiday!");
                    }
                }
            }
        }
    }
    public void saveUser(long chatId, String userName){
        Optional<User> existingUserOptional = userRepo.findByChatId(chatId);

        if (existingUserOptional.isEmpty()) {
            User newUser = User.builder()
                    .chatId(chatId)
                    .name(userName)
                    .build();
            userRepo.save(newUser);
        }
    }
    public void sendTextMessage(long chatId, String message) {
        SendMessage sendMessage = new SendMessage(String.valueOf(chatId), message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();


            sendTextMessage(chatId, "An error occurred while fetching the data from the server. 😞");

        }
    }

    @Override
    public String getBotUsername() {

        return "your_holidays_bot";
    }
    @Override
    public String getBotToken() {

        return "";
    }
}
