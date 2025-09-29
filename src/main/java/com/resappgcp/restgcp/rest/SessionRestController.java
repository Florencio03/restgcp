package com.resappgcp.restgcp.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.resappgcp.restgcp.entity.Session;
import com.resappgcp.restgcp.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class SessionRestController {

    private SessionService sessionService;
    private ObjectMapper objectMapper;

    @Autowired
    public SessionRestController(SessionService theSessionService, ObjectMapper theObjectMapper){
        sessionService = theSessionService;
        objectMapper = theObjectMapper;
    }

    @GetMapping("/sessions")
    public List<Session> findAll(){
        return sessionService.findAll();
    }

    @GetMapping("/sessions/{sessionId}")
    public Session getSession(@PathVariable UUID sessionId){
        Session theSession = sessionService.findById(sessionId);

        if (theSession == null){
            throw new RuntimeException("Session id not found - " + sessionId);
        }

        return theSession;
    }

    @PostMapping("/sessions")
    public Session addSession(@RequestBody Session theSession){

        // also just in case they pass an id in JSON ... set id to null
        // this is to force a save of new item ... instead of update
        //theSession.setId(null);

        Session dbSession = sessionService.save(theSession);

        return dbSession;
    }

    @PatchMapping("/sessions/{sessionId}")
    public Session patchSession(@PathVariable UUID sessionId, @RequestBody Map<String, Object> patchPayload){

        Session tempSession = sessionService.findById(sessionId);

        //throw exception if null
        if (tempSession == null ){
            throw new RuntimeException("Session id not found - " + sessionId);
        }

        // throw exception if request body contains "id" key
        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("Session id not allowed in request body - " + sessionId);
        }

        Session patchedSession = apply(patchPayload, tempSession);

        Session dbSession = sessionService.save(patchedSession);

        return dbSession;
    }
    private Session apply(Map<String, Object> patchPayload, Session tempSession) {

        ObjectNode sessionNode = objectMapper.convertValue(tempSession, ObjectNode.class);

        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        sessionNode.setAll(patchNode);

        return objectMapper.convertValue(sessionNode, Session.class);
    }

    @DeleteMapping("/sessions/{sessionId}")
    public String deleteSession(@PathVariable UUID sessionId){

        Session tempSession = sessionService.findById(sessionId);

        // throw exception if null
        if (tempSession == null) {
            throw new RuntimeException("Session id not found - " + sessionId);
        }

        sessionService.deleteById(sessionId);

        return "Deleted Session id - " + sessionId;
    }

}
