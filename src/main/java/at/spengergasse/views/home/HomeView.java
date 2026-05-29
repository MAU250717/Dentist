package at.spengergasse.views.home;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Home")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.HOME_SOLID)
public class HomeView extends VerticalLayout {

    private TextField name;
    private Button sayHello;

    public HomeView() {
        setSpacing(false);
        VerticalLayout header = getHeader();

        Image img = new Image("src/main/resources/META-INF.resources/images/img.png", "Dentist Logo");
        img.setWidth("220px");



        Paragraph description = new Paragraph(
                "In unserer Zahnarztpraxis trifft moderne Zahnmedizin auf eine entspannte, freundliche Atmosphäre." +
                        " Wir nehmen uns Zeit für dich und sorgen dafür, dass jeder Besuch so angenehm wie möglich ist –" +
                        " von der ersten Beratung bis zum strahlenden Endergebnis. Ob Routinecheck, professionelle Zahnreinigung" +
                        " oder ästhetische Behandlung: Bei uns steht dein Wohlgefühl im Mittelpunkt. Für ein gesundes Lächeln, das " +
                        "genauso gut aussieht wie es sich anfühlt."
        );

        description.setWidth("500px");
        description.getStyle()
                .set("font-size", "22px")
                .set("line-height", "1.6")
                .set("text-align", "left");

        H3 name = new H3("Dentists GmbH");
        H3 street = new H3("Spengergasse 20");
        H3 city = new H3("1050 Wien");

        add(header, img, description, name, street, city);
    }

    public VerticalLayout getHeader() {
        VerticalLayout header = new VerticalLayout();

        H1 companyName = new H1("Dentist");
        companyName.getStyle()
                .set("font-family", "bold")
                .set("font-size", "6rem")
                .set("margin", "0");

        H2 subName = new H2("... the best Dentist ...");
        subName.getStyle()
                .set("margin", "0")
                .set("color", "gray");


        header.add(companyName, subName);

        return header;
    }

}
