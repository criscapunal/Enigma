package com.example.demo.businessLogic.services;

import com.example.demo.businessLogic.dtos.MessageDto;
import com.example.demo.dataAccess.entities.Message;
import com.example.demo.dataAccess.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public boolean isOrderDesc(String word) {
        var wordWithoutVowels = removeVowels(word);
        return wordWithoutVowels
                .chars()
                .mapToObj(x -> (String.valueOf((char) x)))
                .sorted(Collections.reverseOrder())
                .collect(Collectors.joining(""))
                .equals(wordWithoutVowels);
    }

    public boolean isOrderAsc(String word) {
        var wordWithoutVowels = removeVowels(word);
        return wordWithoutVowels
                .chars()
                .mapToObj(x -> (String.valueOf((char) x)))
                .sorted()
                .collect(Collectors.joining(""))
                .equals(wordWithoutVowels);
    }

    public boolean hasThreeConsonants(String word) {
        var wordWithoutVowels = removeVowels(word);
        return wordWithoutVowels.length() == 3;
    }

    public String removeVowels(String word) {
        return word.replaceAll("a|e|i|o|u", "");
    }

    public boolean hasSameFirstLetter(String word, char firstLetter) {
        if (word.indexOf(firstLetter) == 0) {
            return true;
        }
        return false;
    }

    public Stream<String> prune(String text) {
        return Arrays.stream(text.split(" "))
                .map(x -> x.substring(1, x.length()));
    }

    public String getType(String text) {
        String type = "";
        var danger = prune(text)
                .allMatch(x -> isOrderAsc(x));

        var warning = prune(text)
                .allMatch(x -> isOrderDesc(x));

        if (danger) {
            type = "DANGER";
        } else if (warning) {
            type = "WARNING";
        } else {
            type = "INFO";
        }
        return type;
    }

    public boolean isValid(String text) {
        boolean isValid = true;
        var hasThreeConsonants =  prune(text)
                .allMatch(x -> hasThreeConsonants(x));

        var hasSameFirstLetter =  Arrays.stream(text.split(" "))
                .allMatch(x -> hasSameFirstLetter(x, text.charAt(0)));
        if (!hasThreeConsonants) {
            isValid = false;
        }
        if (!hasSameFirstLetter) {
            isValid = false;
        }
        return isValid;
    }

    public MessageDto saveMessage(String text) {
        var message = new Message();
        var type = this.getType(text);
        var isValid = this.isValid(text);
        var alien = isValid ? text.substring(0, 1) : "";

        message.setText(text)
                .setAlien(alien)
                .setValid(isValid)
                .setType(type);

        return MessageDto.mapper(
                this.messageRepository.save(message)
        );
    }

    public LocalDateTime StringToDate(String date) {
        return LocalDateTime.parse(
                date,
                DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")
        );
    }

    public List<MessageDto> getMessagesBetweenDates(String start, String end) {
        List<Message> messages = this.messageRepository.getMessagesBetweenDates(
                this.StringToDate(start),
                this.StringToDate(end)
        );
        return messages.stream().map(MessageDto::mapper).collect(Collectors.toList());
    }

    public List<MessageDto> getMessagesByAlien(String alien) {
        List<Message> messages = this.messageRepository.getMessagesByAlien(alien);
        return messages.stream().map(MessageDto::mapper).collect(Collectors.toList());
    }

    public List<MessageDto> getMessagesByType(String type) {
        List<Message> messages = this.messageRepository.getMessagesByType(type);
        return messages.stream().map(MessageDto::mapper).collect(Collectors.toList());
    }

    public List<MessageDto> getMessagesByValid(String isValid) {
        List<Message> messages = this.messageRepository.getMessagesByValid(Boolean.parseBoolean(isValid));
        return messages.stream().map(MessageDto::mapper).collect(Collectors.toList());
    }
}
