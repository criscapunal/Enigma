package com.example.demo.services;

import com.example.demo.businessLogic.services.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MessageDtoServiceTest {

    @Autowired
    private MessageService messageService;


    @Test
    public void hasSameFirstLetter() {
        assertEquals(messageService.hasSameFirstLetter("Rortf", 'R'), true);
        assertEquals(messageService.hasSameFirstLetter("Hugehw", 'R'), false);
    }

    @Test
    public void hasThreeConsonants() {
        assertEquals(messageService.hasThreeConsonants("tiasek"), true);
        assertEquals(messageService.hasThreeConsonants("heasee"), false);
    }

    @Test
    public void isOrderAsc() {
        assertEquals(messageService.isOrderAsc("ugehw"), true);
        assertEquals(messageService.isOrderAsc("onixob"), false);
        assertEquals(messageService.isOrderAsc("ozyx"), false);
    }

    @Test
    public void isOrderDesc() {
        assertEquals(messageService.isOrderDesc("ugehw"), false);
        assertEquals(messageService.isOrderDesc("onixob"), false);
        assertEquals(messageService.isOrderDesc("ozyx"), true);
    }

    @Test
    public void removeVowels() {
        assertEquals(messageService.removeVowels("tiasek"), "tsk");
        assertEquals(messageService.removeVowels("heasee"), "hs");
    }

    @Test
    public void prune() {
        assertEquals(
                messageService.prune("Rortf Rtiasek Rnagne").collect(Collectors.joining("")),
                Stream.of("ortf", "tiasek", "nagne").collect(Collectors.joining(""))
        );
    }

    @Test
    public void getType() {
        assertEquals(
                messageService.getType("Mozyx"),
                "WARNING"
        );
        assertEquals(
                messageService.getType("Hugehw"),
                "DANGER"
        );
        assertEquals(
                messageService.getType("Monixob"),
                "INFO"
        );
    }

    @Test
    public void isValid() {
        assertEquals(
                messageService.isValid("Rortf Rtiasek Rnagne"),
                true
        );
    }

    @Test
    public void StringToDate() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));
        Locale.setDefault(Locale.forLanguageTag("es_CO"));
        assertEquals(
                messageService.StringToDate("09/25/2017 12:55 PM").getClass(),
                LocalDateTime.class
        );
    }
}
