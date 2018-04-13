package service;

import request.Request;
import theater.TheaterScheme;

import java.util.List;

public interface SeatingService {
    TheaterScheme getScheme(String scheme) throws Exception;
    List<Request> getRequest(String request) throws Exception;
    void processRequest(TheaterScheme scheme, List<Request> request);

}
