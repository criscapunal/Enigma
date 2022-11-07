package com.example.demo.presentation.controllers;

import com.example.demo.businessLogic.dtos.DatesDto;
import com.example.demo.businessLogic.dtos.MessageDto;
import com.example.demo.businessLogic.dtos.TextDto;
import com.example.demo.businessLogic.services.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public MessageDto storeMessage(@RequestBody TextDto textDto) {
        return this.messageService.saveMessage(textDto.getText());
    }

    @GetMapping("/dates")
    public List<MessageDto> getMessagesBetweenDates(@RequestBody DatesDto datesDto) {
        return this.messageService.getMessagesBetweenDates(datesDto.getStart(), datesDto.getEnd());
    }

    @GetMapping("/alien")
    public List<MessageDto> getMessagesByAlien(@RequestParam String alien) {
        return this.messageService.getMessagesByAlien(alien);
    }

    @GetMapping("/type")
    public List<MessageDto> getMessagesByType(@RequestParam String type) {
        return this.messageService.getMessagesByType(type);
    }

    @GetMapping("/is_valid")
    public List<MessageDto> getMessagesByValid(@RequestParam String isValid) {
        return this.messageService.getMessagesByValid(isValid);
    }
}
