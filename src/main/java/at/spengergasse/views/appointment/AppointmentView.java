package at.spengergasse.views.appointment;
import at.spengergasse.domain.Dentist;
import at.spengergasse.domain.DentistExeption;
import at.spengergasse.service.DentistService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import com.vaadin.flow.component.checkbox.Checkbox;

import java.time.LocalDate;

@PageTitle("Appointment")
@Route("Appointment")
@Menu(order = 1, icon = LineAwesomeIconUrl.CALENDAR_ALT_SOLID)
public class AppointmentView extends VerticalLayout {
    private final Button buttonRemoveAll =  new Button("Remove all");
    private final Button buttonAdd10Appointments = new Button("Add 10 Appointments");
    private final Button buttonRemoveAllAnestesie =  new Button("Remove all Anestesie Patienten");
    private final Button buttonAddWrong = new Button("Add wrong");
    private final Button buttonAddOne = new Button("Add Appointment");
    private final Grid<Dentist> grid = new Grid<>(Dentist.class, false);
    private final DentistService dentistService;

    public AppointmentView(@Autowired DentistService dentistService) {
        this.dentistService = dentistService;
        setSpacing(true);
        setSizeFull();
        grid.setSizeFull();
        grid.setItems();

        buttonRemoveAll.addClickListener(e -> removeAllAppointments());
        buttonAdd10Appointments.addClickListener(e -> add10Appointments());
        buttonRemoveAllAnestesie.addClickListener(e -> removeAllAnestesie());
        buttonAddWrong.addClickListener(e -> addWrong());
        buttonAddOne.addClickListener(e -> addOneAppointment());

        add(new HorizontalLayout(buttonRemoveAll,buttonAdd10Appointments,buttonRemoveAllAnestesie,buttonAddWrong,buttonAddOne));

        grid.addColumn(dentist -> dentist.getAppointmentId())
                .setHeader("Patient ID")
                .setSortable(true);
        grid.addColumn(dentist -> dentist.getAppointmentDate())
                .setHeader("Appointment Date")
                .setSortable(true);
        grid.addColumn(dentist -> dentist.getTreatment())
                .setHeader("Treatment")
                .setSortable(true);
        grid.addColumn(dentist -> dentist.getPatientName())
                .setHeader("Patient Name")
                .setSortable(true);
        grid.addColumn(dentist -> dentist.getPrice())
                .setHeader("Price EUR")
                .setSortable(true);
        grid.addColumn(dentist -> dentist.getQuantity())
                .setHeader("Quantity")
                .setSortable(true);
        /*
        grid.addColumn(dentist -> dentist.getAnestesie())
                .setHeader("Anestesie")
                .setSortable(true);
        */
        Image p = new Image("icons/anesthesie.png", "Anesthesie");
        p.setWidth("25px");
        grid.addColumn(o -> o.getAnestesie())
                .setHeader(new HorizontalLayout(p, new Span("Anesthesie")))
                .setSortable(true);

        grid.addComponentColumn(dentist -> {
                Checkbox cb = new Checkbox(dentist.getAnestesie());
                cb.setReadOnly(true);
                return cb;
        })
                .setHeader("Anestesie")
                .setSortable(true);

        grid.addComponentColumn(dentist -> {
            Button delete = new Button("Delete");
            delete.addClickListener(e -> removeOneApp(dentist.getAppointmentId()));
            return delete;
        })
                        .setHeader("Action")
                        .setSortable(false);

        add(grid);
        reload();
    }

    private void addOneAppointment() {
        Dialog dialog = new Dialog();

        dialog.setHeaderTitle("Add 1 Appointment");
        TextField appointmentId = new TextField("Appointment ID");
        DatePicker appointmentDate = new DatePicker("Appointment Date");
        ComboBox treatment = new ComboBox("Treatment Type");
        treatment.setItems("Kontrolle","Zahnspange Kontrolle","Zahnreinigung","Wurzelbehandlung","Füllung","Zahnextraktion","Kronenbehandlung",
                "Implantat","Bleaching","Zahnsteinentfernung","Brückenanpassung");
        TextField patientName = new TextField("Patient Name");
        NumberField price = new NumberField("Price");
        IntegerField quantity = new IntegerField("Quantity");
        Checkbox anestesie = new Checkbox("Anestesie");

        BeanValidationBinder<Dentist> binder = new BeanValidationBinder<>(Dentist.class);

        binder.forField(appointmentDate)
                .bind("appointmentDate");

        binder.forField(treatment)
                .bind("treatment");

        binder.forField(patientName)
                .bind("patientName");

        binder.forField(price)
                .bind("price");

        binder.forField(quantity)
                .bind("quantity");

        binder.forField(anestesie)
                .bind("anestesie");

        Dentist dentist = new Dentist();
        binder.setBean(dentist);

        appointmentId.setValue(""+dentist.getAppointmentId());
        appointmentId.setReadOnly(true);

        VerticalLayout formlayout = new VerticalLayout(
                appointmentId,
                appointmentDate,
                treatment,
                patientName,
                price,
                quantity,
                anestesie
        );

        Button buttonOK = new Button("OK");
        Button buttonCancel = new Button("Cancel");

        buttonOK.addClickListener(e -> {
            try {
                if (binder.validate().isOk()) {
                    dentistService.add1App(dentist);
                    dialog.close();
                    reload();
                    Notification.show("Appointment has been added");
                } else {
                    Notification.show("Validation Failed! Check your input!");
                }
            }
            catch (DentistExeption ex) {
                Notification.show(ex.getMessage());
            }
        });
        buttonCancel.addClickListener(e -> dialog.close());

        dialog.add(formlayout);
        dialog.getFooter().add(buttonOK, buttonCancel);

        dialog.open();
    }

    private void removeOneApp(Long patientId) {
        try {
            dentistService.removeOneApp(patientId);
            reload();
        }
        catch(DentistExeption e){
            Notification.show(e.getMessage());
            reload();
        }
    }

    private void addWrong() {
        try{
            dentistService.addWrong();
            reload();
        } catch (DentistExeption e) {
            Notification.show(e.getMessage());
            reload();
        }
    }

    private void removeAllAnestesie() {
        try {
            dentistService.removeAllAnestesie();
            buttonRemoveAllAnestesie.setEnabled(false);
            reload();
        }
        catch (DentistExeption e) {
            Notification.show(e.getMessage());
            reload();
        }
    }

    private void add10Appointments() {
        try {
            dentistService.add10Appointments();
            buttonRemoveAll.setEnabled(true);
            buttonRemoveAllAnestesie.setEnabled(true);
            reload();
        }
        catch (DentistExeption e) {
            Notification.show(e.getMessage());
            reload();
        }
    }

    private void removeAllAppointments() {
        dentistService.removeAllAppointments();
        buttonRemoveAll.setEnabled(false);
        buttonRemoveAllAnestesie.setEnabled(false);
        reload();
    }

    private void reload() {
        grid.setItems(dentistService.findAll());
    }
}
