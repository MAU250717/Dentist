package at.spengergasse.views.appointment;
import at.spengergasse.domain.Dentist;
import at.spengergasse.service.DentistService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Appointment")
@Route("Appointment")
@Menu(order = 1, icon = LineAwesomeIconUrl.CALENDAR_ALT_SOLID)
public class AppointmentView extends VerticalLayout {
    private final Button buttonRemoveAll =  new Button("Remove all");
    private final Button buttonAdd10Appointments = new Button("Add 10 Appointments");
    private final Button buttonRemoveAllAnestesie =  new Button("Remove all Anestesie Patienten");
    private final Grid<Dentist> grid = new Grid<>(Dentist.class, true);
    private final DentistService dentistService;

    public AppointmentView(@Autowired DentistService dentistService) {
        this.dentistService = dentistService;
        setSpacing(true);
        setSizeFull();
        grid.setSizeFull();

        buttonRemoveAll.addClickListener(e -> removeAllAppointments());
        buttonAdd10Appointments.addClickListener(e -> add10Appointments());
        buttonRemoveAllAnestesie.addClickListener(e -> removeAllAnestesie());

        add(new HorizontalLayout(buttonRemoveAll,buttonAdd10Appointments));

        add(grid);
        reload();
    }

    private void removeAllAnestesie() {
        dentistService.removeAllAnestesie();
    }

    private void add10Appointments() {
        dentistService.add10Appointments();
    }

    private void removeAllAppointments() {
        dentistService.removeAllAppointments();
        buttonRemoveAll.setEnabled(false);
        buttonRemoveAllAnestesie.setEnabled(false);
    }

    private void reload() {
        grid.setItems(dentistService.findAll());
    }
}
