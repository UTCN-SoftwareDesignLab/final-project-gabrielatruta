package com.finalsdproject.donation;

import com.finalsdproject.donation.model.DonationDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DonationService {

    @Value("${baseURL}")
    private String baseURL;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;

    public Session createSession(DonationDTO donationDTO) throws StripeException{

        String successful = baseURL + "/donation/successful";
        String failed = baseURL + "/donation/failed";

        Stripe.apiKey = apiKey;

        SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("euro")
                .setUnitAmount(donationDTO.getDonation() * 100)
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(donationDTO.getName())
                                .build())
                .build();

        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                .setPriceData(priceData)
                .build();

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failed)
                .addLineItem(lineItem)
                .setSuccessUrl(successful)
                .build();
        return Session.create(params);
    }


}
