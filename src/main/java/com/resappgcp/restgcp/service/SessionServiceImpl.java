package com.resappgcp.restgcp.service;

import com.resappgcp.restgcp.dao.SessionRepository;
import com.resappgcp.restgcp.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository theSessionRepository) {
        sessionRepository = theSessionRepository;
    }

    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Session findById(UUID theId) {
        Optional<Session> result = sessionRepository.findById(theId);

        Session theSession = null;

        if (result.isPresent()) {
            theSession = result.get();
        } else {
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theSession;
    }

    @Override
    public Session save(Session theSession) {
        return sessionRepository.save(theSession);
    }

    @Override
    public void deleteById(UUID theId) {
        sessionRepository.deleteById(theId);
    }
}
