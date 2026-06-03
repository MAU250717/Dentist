package at.spengergasse.service;
import at.spengergasse.domain.Dentist;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class DentistService {
    private ArrayList<Dentist> dentists;

    public DentistService() {
        dentists = new ArrayList<>(1000);
    }

    public void fillTestData(){
        dentists.add(new Dentist(LocalDate.of(2026,04,29), "Kontrolle", "Liam Moser", 50.0, 1, false));
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

    }
}
