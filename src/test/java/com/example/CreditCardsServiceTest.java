package com.example;

import com.example.model.CreditCard;
import com.example.model.CreditCards;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CreditCardsServiceTest {

    @InjectMocks
    private CreditCardsService creditCardsService;

    @Mock
    private List<CreditCard> cards;

    @Test
    public void shouldReturnCardsThatWhereAddedToExistingList() throws Exception {
        String input = "HSBC Canada   , 5601-2345-3446-5678 ,  Nov-2017  \n" +
                        "Royal Bank of Canada , 4519-4532-4524-2456,  Oct-2017\n" +
                        "American Express, 3786-7334-8965-345, Dec-2018\n";

        when(cards.addAll(anyList())).thenReturn(true);

        List<CreditCard> result = creditCardsService.addCards(input);
        assertThat(result.size(), is(3));
    }

    @Test
    public void shouldReturnEmptyListIfNoCardsAdded() throws Exception {
        String input = "HSBC Canada   , 5601-2345-3446-5678 ,  Nov-2017  \n" +
                "Royal Bank of Canada , 4519-4532-4524-2456,  Oct-2017\n" +
                "American Express, 3786-7334-8965-345, Dec-2018\n";

        when(cards.addAll(anyList())).thenReturn(false);

        List<CreditCard> result = creditCardsService.addCards(input);
        assertThat(result.isEmpty(), is(true));
    }

    @Test
    public void shouldReturnSortedByDateDescList() throws Exception {
        String input = "HSBC Canada   , 5601-2345-3446-5678 ,  Nov-1910  \n" +
                "Royal Bank of Canada , 4519-4532-4524-2456,  Oct-1950\n" +
                "American Express, 3786-7334-8965-345, Dec-1930\n";

        creditCardsService.setCards(CreditCards.fromMultilineString(input));

        List<CreditCard> list = creditCardsService.getCards();

        assertThat(list.get(0).getBankName(), is("Royal Bank of Canada"));
        assertThat(list.get(1).getBankName(), is("American Express"));
        assertThat(list.get(2).getBankName(), is("HSBC Canada"));
    }
}