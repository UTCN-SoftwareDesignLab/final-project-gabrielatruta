package com.finalsdproject.donation;

import com.finalsdproject.donation.model.DonationDTO;
import com.finalsdproject.donation.model.StripeResp;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.finalsdproject.URLMapping.DONATION;

@RestController
@RequestMapping(DONATION)
@RequiredArgsConstructor
public class DonationController {

    @Autowired
    private final DonationService donationService;

    @PostMapping
    public ResponseEntity<StripeResp> makeDonation(@RequestBody DonationDTO donationDTO) throws StripeException {

        Session session = donationService.createSession(donationDTO);
        StripeResp stripeResponse = new StripeResp(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);

    }
}
