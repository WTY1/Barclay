package theater;

public class Section implements Comparable<Section> {
    private int row;
    private int sectionNum;
    private int seats;
    private int availableSeats;

    public Section() {
    }

    public Section(int row, int sectionNum, int seats, int availableSeats) {
        this.row = row;
        this.sectionNum = sectionNum;
        this.seats = seats;
        this.availableSeats = availableSeats;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(int sectionNum) {
        this.sectionNum = sectionNum;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    @Override
    public int compareTo(Section s) {
        if(this.getAvailableSeats() - s.getAvailableSeats() == 0) {
            if(this.getRow() - s.getRow() == 0) {
                return this.getSectionNum() - s.getSectionNum();
            } else{
                return this.getRow() - s.getRow();
            }
        } else {
            return this.getAvailableSeats() - s.getAvailableSeats();
        }
    }

}
