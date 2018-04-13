package service;


import request.Request;
import theater.Section;
import theater.TheaterScheme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class TheaterSeatingService implements SeatingService {
    @Override
    public TheaterScheme getScheme(String scheme) throws Exception {
        TheaterScheme theater = new TheaterScheme();
        Section section;
        List<Section> sectionList = new ArrayList<Section>();
        int totalSeats = 0;
        int seats = 0;
        String[] row = scheme.split(System.lineSeparator());
        String[] sections;
        for(int i = 0; i < row.length; i++) {
            sections = row[i].split(" ");
            for(int j = 0; j < sections.length; j++) {
                try {
                    seats = Integer.valueOf(sections[j]);
                } catch(Exception e) {
                    throw new Exception(sections[j] + " is invalid section capacity.");
                }
                totalSeats += seats;
                section = new Section();
                section.setAvailableSeats(seats);
                section.setSeats(seats);
                section.setRow(i + 1);
                section.setSectionNum(j + 1);
                sectionList.add(section);
            }
        }
        theater.setAvailableSeats(totalSeats);
        theater.setTotalSeats(totalSeats);
        theater.setSection(sectionList);
        return theater;

    }

    @Override
    public List<Request> getRequest(String request) throws Exception{
        List<Request> list = new ArrayList<>();
        Request customerRequests;
        String[] requests = request.split(System.lineSeparator());
        for(String s : requests) {
            String[] singleRequest = s.split(" ");
            customerRequests = new Request();
            customerRequests.setName(singleRequest[0]);
            try{
                customerRequests.setNumOfTickets(Integer.valueOf(singleRequest[1]));
            } catch (Exception e) {
                throw new Exception("Customer" + singleRequest[0] + "ordered " + singleRequest[1] + "'" + " is invalid ticket request.");
            }
            customerRequests.setSatisfied(false);
            list.add(customerRequests);
        }
        return list;
    }

    @Override
    public void processRequest(TheaterScheme scheme, List<Request> request) {
        for(int i = 0; i < request.size(); i++) {
            Request singleRequest = request.get(i);
            if(singleRequest.getNumOfTickets() > scheme.getAvailableSeats()) {
                singleRequest.setRowNumber(-1);
                singleRequest.setSectionNumber(-1);
                continue;
            }
            List<Section> section =  scheme.getSection();
            for(int j = 0;j < section.size(); j++) {
                Section singleSection = section.get(j);
                if(singleRequest.getNumOfTickets() == singleSection.getAvailableSeats()) {
                    singleRequest.setRowNumber(singleSection.getRow());
                    singleRequest.setSectionNumber(singleSection.getSectionNum());
                    singleRequest.setSatisfied(true);
                    singleSection.setAvailableSeats(singleSection.getAvailableSeats() - singleRequest.getNumOfTickets());
                    scheme.setAvailableSeats( scheme.getAvailableSeats() - singleRequest.getNumOfTickets());
                    break;
                }else if(singleRequest.getNumOfTickets() < singleSection.getAvailableSeats()) {
                    int requestNum = addUpRequest(request, singleSection.getAvailableSeats() - singleRequest.getNumOfTickets(), i);
                    if(requestNum >=0) {
                            singleRequest.setRowNumber(singleSection.getRow());
                            singleRequest.setSectionNumber(singleSection.getSectionNum());
                            singleRequest.setSatisfied(true);

                            Request fitRequest = request.get(requestNum);
                            fitRequest.setRowNumber(singleSection.getRow());
                            fitRequest.setSectionNumber(singleSection.getRow());
                            fitRequest.setSatisfied(true);

                            singleSection.setAvailableSeats(singleSection.getAvailableSeats() - singleRequest.getNumOfTickets() - fitRequest.getNumOfTickets());
                            scheme.setAvailableSeats(scheme.getAvailableSeats() - singleRequest.getNumOfTickets() - fitRequest.getNumOfTickets());
                            break;
                    } else{
                            int sectionNum = findSection(section, singleRequest.getNumOfTickets());
                            if(sectionNum >= 0) {
                                Section fitSection = section.get(sectionNum);
                                singleRequest.setRowNumber(fitSection.getRow());
                                singleRequest.setSectionNumber(fitSection.getSectionNum());
                                singleRequest.setSatisfied(true);
                                fitSection.setAvailableSeats(fitSection.getAvailableSeats() - singleRequest.getNumOfTickets());
                                scheme.setAvailableSeats(scheme.getAvailableSeats() - singleRequest.getNumOfTickets());
                                break;
                            } else {
                                singleRequest.setRowNumber(singleSection.getRow());
                                singleRequest.setSectionNumber(singleSection.getSectionNum());
                                singleRequest.setSatisfied(true);
                                singleSection.setAvailableSeats(singleSection.getAvailableSeats() - singleRequest.getNumOfTickets());
                                scheme.setAvailableSeats(scheme.getAvailableSeats() - singleRequest.getNumOfTickets());
                                break;
                            }
                    }
                }

            }
            if(!singleRequest.isSatisfied()) {
                singleRequest.setRowNumber(-2);
                singleRequest.setSectionNumber(-2);
            }
        }
        for(Request r : request) {
            System.out.println(r.status());
        }
    }

    private int findSection(List<Section> section, int numOfTickets) {
        Section singleSection = new Section();
        singleSection.setAvailableSeats(numOfTickets);
        Collections.sort(section);
        int sectionNum = Collections.binarySearch(section, singleSection, (a,b) -> (a.getAvailableSeats() - b.getAvailableSeats()));
        if(sectionNum > 0) {
            int i = 0;
            for(i = sectionNum - 1; i >= 0; i--) {
                Section s = section.get(i);
                if(s.getAvailableSeats() != numOfTickets) {
                    break;
                }
            }
            sectionNum = i + 1;
        }
        return sectionNum;
    }

    private int addUpRequest(List<Request> request, int seatNeed, int requestIndex) {
        int result = -1;
        for(int i = requestIndex + 1; i < request.size(); i++) {
            Request singleRequest = request.get(i);
            if(singleRequest.getNumOfTickets() == seatNeed && !singleRequest.isSatisfied()) {
                result = i;
                break;
            }
        }
        return result;
    }



}
