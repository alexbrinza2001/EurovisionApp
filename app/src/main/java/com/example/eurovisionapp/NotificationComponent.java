package com.example.eurovisionapp;

import dagger.Component;

@Component
public interface NotificationComponent {

    Notification getNotification();

    void inject(Top2023 top2023);
}
