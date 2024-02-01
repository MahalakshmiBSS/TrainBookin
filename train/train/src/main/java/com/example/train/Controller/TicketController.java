package com.example.train.Controller;

import com.example.train.DTO.TicketReceiptDTO;
import com.example.train.DTO.UserSeatDTO;

import com.example.train.Model.Ticket;
import com.example.train.Model.User;
import com.example.train.Repository.TicketRepository;
import com.example.train.Repository.UserRepository;
import com.example.train.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketService ticketService;

    // API to submit a purchase for a ticket
    @PostMapping("/purchase")
    public ResponseEntity<Ticket> purchase(@RequestBody Ticket ticket, @RequestParam Long userId) {
        try {
            Ticket savedTicket = ticketService.purchase(ticket, userId);
            return new ResponseEntity<>(savedTicket, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // API to show details of the receipt for a user
    @GetMapping("/{userId}")
    public ResponseEntity<?> getReceiptDetails(@PathVariable Long userId) {
        List<TicketReceiptDTO> ticketDTOs = ticketService.getReceiptDetails(userId);
        if (ticketDTOs != null) {
            return new ResponseEntity<>(ticketDTOs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/removeUser/{userId}")
    public ResponseEntity<String> removeUserFromTrain(@PathVariable Long userId) {
        String result = ticketService.removeUserFromTrain(userId);

        if (result.equals("User removed from the train successfully.")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/viewSeatsBySection")
    public ResponseEntity<?> viewSeatsBySection(@RequestParam String section) {
        List<UserSeatDTO> userSeatDTOs = ticketService.viewUsersAndSeatsBySection(section);
        return new ResponseEntity<>(userSeatDTOs, HttpStatus.OK);
    }

    @PutMapping("/modifySeat/{userId}/{ticketId}")
    public ResponseEntity<String> modifyUserSeat(
            @PathVariable Long userId,
            @PathVariable Long ticketId,
            @RequestBody Map<String, String> requestBody) {
        try {
            String newSeat = requestBody.get("newSeat");
            String result = ticketService.modifyUserSeat(userId, ticketId, newSeat);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
