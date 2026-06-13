package at.spengergasse.service;
import at.spengergasse.domain.Dentist;
import at.spengergasse.domain.DentistExeption;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

@Service
public class DentistService {
    private ArrayList<Dentist> dentists;

    public DentistService() {
        dentists = new ArrayList<>(1000);
        fillTestData();
    }

    public void fillTestData(){
        dentists.add(new Dentist(LocalDate.of(2026,4,29), "Kontrolle", "Liam Moser", 50.0, 1, false));
        dentists.add(new Dentist(LocalDate.of(2024, 3, 12), "Zahnreinigung", "Anna Müller", 80.0, 1, false));
        dentists.add(new Dentist(LocalDate.of(2024, 4, 5), "Wurzelbehandlung", "Markus Steiner", 350.0, 1, true));
        dentists.add(new Dentist(LocalDate.of(2024, 5, 20), "Füllung", "Julia Berger", 120.0, 2, false));
        dentists.add(new Dentist(LocalDate.of(2024, 6, 1), "Zahnextraktion", "Thomas Weber", 200.0, 1, true));
        dentists.add(new Dentist(LocalDate.of(2024, 7, 18), "Kronenbehandlung", "Sabine Koch", 780.0, 1, false));
        dentists.add(new Dentist(LocalDate.of(2024, 8, 9), "Zahnspange Kontrolle", "Lukas Mayer", 60.0, 1, false));
        dentists.add(new Dentist(LocalDate.of(2024, 9, 25), "Implantat", "Petra Huber", 1500.0, 1, true));
        dentists.add(new Dentist(LocalDate.of(2024, 10, 3), "Bleaching", "Daniela Schwarz", 250.0, 1, false));
        dentists.add(new Dentist(LocalDate.of(2024, 11, 14), "Zahnsteinentfernung", "Michael Bauer", 95.0, 1, false));
        dentists.add(new Dentist(LocalDate.of(2024, 12, 2), "Brückenanpassung", "Katharina Leitner", 650.0, 1, true));
    }

    public ArrayList<Dentist> findAll(){
        ArrayList<Dentist> clone;
        clone = new ArrayList<>(dentists);
        return clone;
    }

    @Override
    public String toString() {
        return dentists.stream()
                .map(d -> d.toString())
                .collect(Collectors.joining("/n"));
    }

    public void removeAllAppointments() {
        dentists.clear();
    }

    public void add10Appointments() {
        dentists.add(new Dentist(LocalDate.of(2024, 3, 12), "Zahnreinigung", "Anna Müller", 80.0, 1, false));
        dentists.add(new Dentist(LocalDate.of(2024, 4, 5), "Wurzelbehandlung", "Markus Steiner", 350.0, 1, true));
        dentists.add(new Dentist(LocalDate.of(2024, 5, 20), "Füllung", "Julia Berger", 120.0, 2, false));
        dentists.add(new Dentist(LocalDate.of(2024, 6, 1), "Zahnextraktion", "Thomas Weber", 200.0, 1, true));
        dentists.add(new Dentist(LocalDate.of(2024, 7, 18), "Kronenbehandlung", "Sabine Koch", 780.0, 1, false));
        dentists.add(new Dentist(LocalDate.of(2024, 8, 9), "Zahnspange Kontrolle", "Lukas Mayer", 60.0, 1, false));
        dentists.add(new Dentist(LocalDate.of(2024, 9, 25), "Implantat", "Petra Huber", 1500.0, 1, true));
        dentists.add(new Dentist(LocalDate.of(2024, 10, 3), "Bleaching", "Daniela Schwarz", 250.0, 1, false));
        dentists.add(new Dentist(LocalDate.of(2024, 11, 14), "Zahnsteinentfernung", "Michael Bauer", 95.0, 1, false));
        dentists.add(new Dentist(LocalDate.of(2024, 12, 2), "Brückenanpassung", "Katharina Leitner", 650.0, 1, true));
    }

    public void removeAllAnestesie() {
        Iterator<Dentist> it;
        Dentist d;

        it = dentists.iterator();
        while (it.hasNext()) {
            d = it.next();
            if (d.getAnestesie() == true) {
                it.remove();
            }
        }
    }

    public void addWrong() {
        dentists.add(new Dentist(LocalDate.of(2026,4,29), "Kontrolle", "Liam Moser", -5.0, 1, false));
    }

    public void removeOneApp(Long patientId) {
        Dentist d = null;

        for (Dentist dentist : dentists) {
            if (dentist.getAppointmentId().equals(patientId)) {
                d = dentist;
            }
        }
        dentists.remove(d);

        if (!dentists.removeIf(dentist -> dentist.getAppointmentId().equals(patientId))) {
            throw new DentistExeption("AppointmentId: " + patientId + " not found");
        }
    }

    public void add1App(Dentist dentist) {
        if (dentist == null) {
            throw new DentistExeption("Dentist is null");
        }
        dentists.add(dentist);
    }
}
