# **Holiday Reminder Telegram Bot**

Welcome to the **Holiday Reminder Telegram Bot** project! This bot serves as a convenient reminder for holidays in both the **United States** and **Ethiopia**. By integrating with **Telegram**, it sends you timely notifications whenever it detects a holiday.

## **Technologies Used**
- **Spring Boot** & **MySQL**

## **Features**

### **Daily Holiday Reminders**
Every day, the bot checks whether the current date corresponds to a holiday in either the **US** or **Ethiopia**. If it identifies a holiday, a notification is promptly sent to your **Telegram** account, ensuring you never miss out on any festive occasions.

### **Integration with HolidayAPI**
To facilitate holiday data retrieval, this project integrates with the **HolidayAPI** service. Since my account is on trial basis, access is limited to a one-year historical record. To overcome this limitation, a custom logic has been implemented: the system fetches holidays from the previous year, adds one year to it, and then compares the dates with the current year. If they match, a holiday message is dispatched.


