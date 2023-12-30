package com.neba.Holiday_Reminder.repo;

import com.neba.Holiday_Reminder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
   @Query("SELECT u FROM users u WHERE u.chatId = :chatId")
    Optional<User> findByChatId(@Param("chatId") Long chatId);

}
