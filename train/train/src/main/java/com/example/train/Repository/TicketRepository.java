package com.example.train.Repository;


import com.example.train.Model.Ticket;
import com.example.train.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUser(User user);

    List<Ticket> findBySection(String section);

}