package com.example.eurovisionapp;

import dagger.Component;

@Component
public interface NotificationComponent {

    Notification getNotification();

    void inject(Top2023 top2023);
    void inject(Top2022 top2022);
    void inject(Top2021 top2021);
}
