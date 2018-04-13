package theater;

import java.util.List;

public class TheaterScheme {
    private int totalSeats;
    private int availableSeats;
    private List<Section> section;

    public TheaterScheme() {
    }

    public TheaterScheme(int totalSeats, int availableSeats, List<Section> section) {
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.section = section;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<Section> getSection() {
        return section;
    }

    public void setSection(List<Section> section) {
        this.section = section;
    }
}
