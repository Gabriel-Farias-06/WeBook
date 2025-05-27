package com.webook.app.config;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class StripeConfig {
    @PostConstruct
    public void init() {
        Stripe.apiKey = "";
    }
}
