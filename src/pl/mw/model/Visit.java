package pl.mw.model;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Visit {

    private int id_visit;
    private User customer;
    private Dog dog;
    private User groomer;
    private LocalDate visit_date;
    private String visit_time;
    private boolean booked;

    public int getId_visit() {
        return id_visit;
    }

    public void setId_visit(int id_visit) {
        this.id_visit = id_visit;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public User getGroomer() {
        return groomer;
    }

    public void setGroomer(User groomer) {
        this.groomer = groomer;
    }

    public LocalDate getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(LocalDate visit_date) {
        this.visit_date = visit_date;
    }

    public String getVisit_time() {
        return visit_time;
    }

    public void setVisit_time(String visit_time) {
        this.visit_time = visit_time;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visit)) return false;
        Visit visit = (Visit) o;
        return getId_visit() == visit.getId_visit() &&
                Objects.equals(getCustomer(), visit.getCustomer()) &&
                Objects.equals(getDog(), visit.getDog()) &&
                Objects.equals(getGroomer(), visit.getGroomer()) &&
                Objects.equals(getVisit_date(), visit.getVisit_date()) &&
                Objects.equals(getVisit_time(), visit.getVisit_time());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_visit(), getCustomer(), getDog(), getGroomer(), getVisit_date(), getVisit_time());
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id_visit=" + id_visit +
                ", customer=" + customer +
                ", dog=" + dog +
                ", groomer=" + groomer +
                ", visit_date=" + visit_date +
                ", visit_time='" + visit_time + '\'' +
                ", booked=" + booked +
                '}';
    }
}
