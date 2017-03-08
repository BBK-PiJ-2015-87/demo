package com.example;

import com.example.model.CreditCard;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardApplicationTest {

    final String BASE_URL = "http://localhost:8080/";


    @Mock
    private CreditCardsService creditCards;

    @Before
    public void setup() {
        given(this.creditCards.
                getCards()
        ).willReturn(
                asList(new CreditCard("Halifax", "1212", new Date())));
    }

    @Ignore
	@Test
	public void shouldReturnJsonWithCards() {
        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(BASE_URL+ "getCards", String.class);

        System.out.println("BODY" + responseEntity.getBody());
    }
}
