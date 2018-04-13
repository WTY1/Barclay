import request.Request;
import service.SeatingService;
import service.TheaterSeatingService;
import theater.TheaterScheme;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String line;
        StringBuilder layout = new StringBuilder();
        StringBuilder ticketRequests = new StringBuilder();
        boolean isLayoutFinished = false;

        System.out.println("Please enter Theater Layout and Ticket requests and then enter 'finish'.\n");

        Scanner input = new Scanner(System.in);

        while((line = input.nextLine()) != null && !line.equals("finish")){

            if(line.trim().isEmpty()){

                isLayoutFinished = true;
                continue;

            }

            if(!isLayoutFinished){

                layout.append(line + System.lineSeparator());

            }else{

                ticketRequests.append(line + System.lineSeparator());

            }

        }

        input.close();

        SeatingService service = new TheaterSeatingService();

        try{

            TheaterScheme theaterLayout = service.getScheme(layout.toString());

            List<Request> requests = service.getRequest(ticketRequests.toString());

            service.processRequest(theaterLayout, requests);

        }catch(NumberFormatException nfe){

            System.out.println(nfe.getMessage());

        }catch(Exception e){

            e.printStackTrace();

        }
    }
}
