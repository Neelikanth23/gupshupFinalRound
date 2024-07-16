package com.example.sessionmanagement.repository;

import com.example.sessionmanagement.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findBySessionId(String sessionId);
}
