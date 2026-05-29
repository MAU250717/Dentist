package at.spengergasse.service;
import at.spengergasse.domain.Dentist;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class DentistService {
    private ArrayList<Dentist> dentists;

    public DentistService() {
        dentists = new ArrayList<>(1000);
    }

    public void fillTestData(){
        dentists.add(new Dentist());
    }

    @Override
    public String toString() {
        return dentists.stream()
                .map(d -> d.toString())
                .collect(Collectors.joining("/n"));
    }
}
