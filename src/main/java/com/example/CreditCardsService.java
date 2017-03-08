package com.example;

import com.example.model.CreditCard;
import com.example.model.CreditCards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Comparator.comparing;

@Service
public class CreditCardsService {

    @Autowired
    private List<CreditCard> cards;

    public CreditCardsService(List<CreditCard> cards){
        this.cards = cards;
    }

    public List<CreditCard> addCards(String cardsAsString) throws UnsupportedEncodingException {
        List<CreditCard> list = CreditCards.fromMultilineString(cardsAsString);

        if (cards.addAll(list)) return list;
        return emptyList();

    }

    public List<CreditCard> getCards(){
        cards.sort(comparing(CreditCard::getExpieryDate).reversed());
        return cards;
    }

    public void setCards(List<CreditCard> cards) {
        this.cards = cards;
    }
}