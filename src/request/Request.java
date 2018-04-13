package request;

public class Request {
    private String name;
    private int numOfTickets;
    private int rowNumber;
    private int sectionNumber;
    private boolean satisfied;

    public Request() {
    }

    public Request(String name, int numOfTickets, int rowNumber, int sectionNumber, boolean satisfied) {
        this.name = name;
        this.numOfTickets = numOfTickets;
        this.rowNumber = rowNumber;
        this.sectionNumber = sectionNumber;
        this.satisfied = satisfied;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfTickets() {
        return numOfTickets;
    }

    public void setNumOfTickets(int numOfTickets) {
        this.numOfTickets = numOfTickets;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public boolean isSatisfied() {
        return satisfied;
    }

    public void setSatisfied(boolean satisfied) {
        this.satisfied = satisfied;
    }
    public String status() {
        String status = null;
        if(satisfied) {
            status = name + " " + "Row " + rowNumber + " " + "Section " + sectionNumber;
        } else if(rowNumber == -1 && sectionNumber == -1) {
            status = name + " " + " Sorry, we can't handle your party.";
        } else {
            status = name + " " + "Call to split party";
        }
        return status;
    }
}
