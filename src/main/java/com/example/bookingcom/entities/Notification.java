package com.example.bookingcom.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "notification")
public class Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Пока думаю здесь юзеру должен приходить уведомления о публикациях админа,если заказчик пишет сообщения хозяину
    // и еще заказчик добавляет это отель в избранное и т.д


}
