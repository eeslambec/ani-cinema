package uz.pdp.anicinema.entity;

import uz.pdp.anicinema.utils.enums.SubscriptionStatus;

import java.time.LocalDate;

public class Subscription {
    private Long id;
    private Plan plan;
    private Long startDate;
    private Long endDate;
    private SubscriptionStatus status;
}
