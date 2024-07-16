package com.example.sessionmanagement.service;

import com.example.sessionmanagement.model.Session;
import com.example.sessionmanagement.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public Session createSession(Long userId) {
        Session session = new Session();
        session.setSessionId(UUID.randomUUID().toString());
        session.setUserId(userId);
        session.setExpirationTime(System.currentTimeMillis() + 30 * 60 * 1000); // 30 minutes expiry
        return sessionRepository.save(session);
    }

    public Session findBySessionId(String sessionId) {
        return sessionRepository.findBySessionId(sessionId);
    }
}
