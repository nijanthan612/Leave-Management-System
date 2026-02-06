package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.LeaveChat;
import com.example.demo.repository.LeaveChatRepository;

@RestController
@RequestMapping("/chat")
public class LeaveChatController {

    @Autowired
    private LeaveChatRepository repo;

    @GetMapping("/{leaveId}")
    public List<LeaveChat> getChat(@PathVariable int leaveId) {
        return repo.findByLeaveIdOrderBySentAtAsc(leaveId);
    }

    @PostMapping("/send/{leaveId}")
    public void send(@PathVariable int leaveId,
                     @RequestBody LeaveChat chat) {

        chat.setLeaveId(leaveId);
        chat.setSentAt(LocalDateTime.now());
        repo.save(chat);
    }
}
