package com.example.train.Service;

import com.example.train.DTO.TicketReceiptDTO;
import com.example.train.DTO.UserSeatDTO;
import com.example.train.Model.Ticket;
import com.example.train.Model.User;
import com.example.train.Repository.TicketRepository;
import com.example.train.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    public Ticket purchase(Ticket ticket, Long userId) {

        User user = userService.getUserById(userId);
        if (user != null) {

            ticket.setUser(user);
            return ticketRepository.save(ticket);
        } else {

            throw new IllegalArgumentException("User with ID " + userId + " not found.");
        }
    }


    public List<TicketReceiptDTO> getReceiptDetails(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(user -> {
            List<Ticket> tickets = ticketRepository.findByUser(user);
            return tickets.stream().map(this::convertToDTO).collect(Collectors.toList());
        }).orElse(null);
    }

    private TicketReceiptDTO convertToDTO(Ticket ticket) {
        TicketReceiptDTO dto = new TicketReceiptDTO();
        dto.setId(ticket.getId());
        dto.setDeparturePlace(ticket.getDeparturePlace());
        dto.setDestination(ticket.getDestination());
        User user = ticket.getUser();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPricePaid(ticket.getPricePaid());
        dto.setSection(ticket.getSection());
        dto.setSeat(ticket.getSeat());
        return dto;
    }

    public List<UserSeatDTO> viewUsersAndSeatsBySection(String section) {
        List<Ticket> tickets = ticketRepository.findBySection(section);
        return tickets.stream().map(this::convertToUserSeatDTO).collect(Collectors.toList());
    }

    private UserSeatDTO convertToUserSeatDTO(Ticket ticket) {
        UserSeatDTO dto = new UserSeatDTO();
        User user = ticket.getUser();
        dto.setUserId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setSeat(ticket.getSeat());
        return dto;
    }


    public String removeUserFromTrain(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Ticket> tickets = ticketRepository.findByUser(user);


            ticketRepository.deleteAll(tickets);



            return "User removed from the train successfully.";
        } else {
            return "User not found";
        }
    }

    public String modifyUserSeat(Long userId, Long ticketId, String newSeat) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);

        if (optionalUser.isPresent() && optionalTicket.isPresent()) {
            User user = optionalUser.get();
            Ticket ticket = optionalTicket.get();

            // Ensure the ticket belongs to the specified user
            if (ticket.getUser().getId() == userId) {
                ticket.setSeat(newSeat);
                ticketRepository.save(ticket);
                return "User seat modified successfully.";
            } else {
                return "Ticket does not belong to the specified user.";
            }
        } else {
            return "User or ticket not found.";
        }
    }
}