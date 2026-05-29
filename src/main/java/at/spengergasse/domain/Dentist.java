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
    private Long      patientId;
    private LocalDate appointmentDate;
    private String    treatment;
    private String    patientName;
    private Double    price;
    private Integer   quantity;
    private Boolean   anestesie;


    private static final AtomicLong sequence = new AtomicLong(1000);

    public Dentist() {
        setPatientId();
        setAppointmentDate(LocalDate.now());
        setTreatment("Kontrolle");
        setPatientName("Max Mustermann");
        setPrice(150.0);
        setQuantity(1);
        setAnestesie(false);
    }

    public Dentist(LocalDate appointmentDate, String treatment, String patientName, Double price, Integer quantity, Boolean anestesie) {
        setPatientId();
        setAppointmentDate(appointmentDate);
        setTreatment(treatment);
        setPatientName(patientName);
        setPrice(price);
        setQuantity(quantity);
        setAnestesie(anestesie);
    }

    public Dentist(Long patientId,LocalDate appointmentDate, String treatment, String patientName, Double price, Integer quantity, Boolean anestesie) {
        setPatientId(patientId);
        setAppointmentDate(appointmentDate);
        setTreatment(treatment);
        setPatientName(patientName);
        setPrice(price);
        setQuantity(quantity);
        setAnestesie(anestesie);
    }

    public void setPatientId() {
        this.patientId = sequence.getAndIncrement();
    }

    @Override
    public Dentist clone() {
        return new Dentist(patientId, appointmentDate, treatment, patientName, price, quantity, anestesie);
    }
}
