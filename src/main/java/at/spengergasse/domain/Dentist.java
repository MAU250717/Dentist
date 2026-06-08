package at.spengergasse.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode (of = "patientId", callSuper = false)
@Entity
public class Dentist {

    @Id
    private Long appointmentId;
    private LocalDate appointmentDate;
    private String    treatment;
    private String    patientName;
    private Double    price;
    private Integer   quantity;
    private Boolean   anestesie;


    private static final AtomicLong sequence = new AtomicLong(1000);

    public Dentist() {
        setAppointmentId();
        setAppointmentDate(LocalDate.now());
        setTreatment("Kontrolle");
        setPatientName("Max Mustermann");
        setPrice(150.0);
        setQuantity(1);
        setAnestesie(false);
    }

    public Dentist(LocalDate appointmentDate, String treatment, String patientName, Double price, Integer quantity, Boolean anestesie) {
        setAppointmentId();
        setAppointmentDate(appointmentDate);
        setTreatment(treatment);
        setPatientName(patientName);
        setPrice(price);
        setQuantity(quantity);
        setAnestesie(anestesie);
    }

    public Dentist(Long appointmentId, LocalDate appointmentDate, String treatment, String patientName, Double price, Integer quantity, Boolean anestesie) {
        setAppointmentId(appointmentId);
        setAppointmentDate(appointmentDate);
        setTreatment(treatment);
        setPatientName(patientName);
        setPrice(price);
        setQuantity(quantity);
        setAnestesie(anestesie);
    }

    public void setAppointmentId() {
        this.appointmentId = sequence.getAndIncrement();
    }

    public void setPrice(Double price) {
        if (price > 0 && price <= 2500) {
            this.price = price;
        }
        else  {
            throw new DentistExeption("Price must be between 0 and 1500");
        }
    }



    @Override
    public Dentist clone() {
        return new Dentist(appointmentId, appointmentDate, treatment, patientName, price, quantity, anestesie);
    }
}
