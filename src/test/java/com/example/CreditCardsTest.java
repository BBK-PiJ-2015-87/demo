package com.example;

import com.example.model.CreditCard;
import com.example.model.CreditCards;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CreditCardsTest {

    @Test
    public void shouldConstructCreditCardFromString() throws ParseException {
        String input = "HSBC Canada   , 5601-2345-3446-5678 ,  Nov-2017  ";

        CreditCard card = CreditCards.fromString(input);

        assertThat(card.getBankName(), is("HSBC Canada"));
        assertThat(card.getCardNumber(), is("5601-XXXX-XXXX-XXXX"));
        assertThat(card.getExpiryDate().getYear(), is(117));
    }

    @Test(expected = ParseException.class)
    public void shouldThrowAnExceptionWhenDateIsInWrongFormat() throws ParseException {
        String input = "HSBC Canada   , 5601-2345-3446-5678 ,  InvalidDate  ";

        CreditCards.fromString(input);
    }

    @Test
    public void shouldConstructCreditCardsFromMultilineString() throws ParseException {
        String input =
                "HSBC Canada   , 5601-2345-3446-5678 ,  Nov-2017  \n" +
                "Royal Bank of Canada , 4519-4532-4524-2456,  Oct-2017\n" +
                "American Express, 3786-7334-8965-345, Dec-2018\n" +
                "\n" +
                "\n";

        List<CreditCard> cards = CreditCards.fromMultilineString(input);

        assertThat(cards.size(), is(3));
        assertThat(cards.get(0).getCardNumber(), is("5601-XXXX-XXXX-XXXX"));
        assertThat(cards.get(1).getBankName(), is("Royal Bank of Canada"));
        assertThat(cards.get(2).getExpiryDate().getYear(), is(118));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowAnExceptionWhenInvalidInput() throws ParseException {
        String invalidInput = "HSBC Canada   , 5601-2345-3446-5678 ,  InvalidDate \n";

        CreditCards.fromMultilineString(invalidInput);
    }

    @Test
    public void shouldObfuscateCardNumber() throws ParseException {
        String input = "5601-2345-3446-567";
        String obfuscated = CreditCards.obfuscate(input);

        assertThat(obfuscated, is("5601-XXXX-XXXX-XXX"));
    }
}