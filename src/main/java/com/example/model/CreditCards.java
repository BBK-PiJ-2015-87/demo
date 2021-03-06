package com.example.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CreditCards {

    public static CreditCard fromString(String card) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM-yyyy");
        List<String> list = Stream.of(card.split(",")).map(String::trim).collect(toList());

        return new CreditCard(list.get(0), obfuscate(list.get(1)), formatter.parse(list.get(2)));
    }

    public static List<CreditCard> fromMultilineString(String input) {
        return Stream.of(input.split("\n")).map(line -> { try {
            return fromString(line);
        } catch (ParseException e){
            throw new RuntimeException(e);
        }
        }).collect(toList());
    }

    public static String obfuscate(String cardNumber){
        StringBuilder builder = new StringBuilder();
        builder.append(cardNumber.substring(0, 5));
        String toObfuscate = cardNumber.substring(5);

        return builder.append(toObfuscate.replaceAll("\\d", "X")).toString();
    }
}
