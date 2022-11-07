package com.example.demo.dataAccess.repositories;

import com.example.demo.dataAccess.entities.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MessageRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Message save(Message message) {
        em.persist(message);
        return message;
    }
    @Transactional
    public List<Message> getMessagesBetweenDates(LocalDateTime start, LocalDateTime end) {
        TypedQuery<Message> query = em.createQuery(
                "select m from Message m where m.createdAt > :dateStart and m.createdAt < :dateEnd",
                Message.class
        )
        .setParameter("dateStart", start)
        .setParameter("dateEnd", end);

        return query.getResultList();
    }
    @Transactional
    public List<Message> getMessagesByAlien(String alien) {
        TypedQuery<Message> query = em.createQuery(
                        "select m from Message m where m.alien = :alien",
                        Message.class
                )
                .setParameter("alien", alien);

        return query.getResultList();
    }
    @Transactional
    public List<Message> getMessagesByType(String type) {
        TypedQuery<Message> query = em.createQuery(
                        "select m from Message m where m.type = :type",
                        Message.class
                )
                .setParameter("type", type);

        return query.getResultList();
    }
    @Transactional
    public List<Message> getMessagesByValid(Boolean isValid) {
        TypedQuery<Message> query = em.createQuery(
                        "select m from Message m where m.isValid = :isValid",
                        Message.class
                )
                .setParameter("isValid", isValid);

        return query.getResultList();
    }
}
