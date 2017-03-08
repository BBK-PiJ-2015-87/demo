package com.example;

import com.example.model.CreditCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class CreditCardController {

    @Autowired
    private CreditCardsService creditCardsService;

    private Logger LOGGER = LoggerFactory.getLogger(CreditCardController.class);

    @RequestMapping(value = "/addCards", method = RequestMethod.POST)
    public List<CreditCard> addCards(@RequestBody String cards) throws UnsupportedEncodingException {
        LOGGER.info("Saving cards to CardService");
        return creditCardsService.addCards(cards);
    }

    @RequestMapping(value = "/getCards")
    public List<CreditCard> getCards(){
        LOGGER.info("Retrieving all cards");
        return creditCardsService.getCards();
    }
}
