package at.spengergasse.views.home;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Home")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.GLOBE_SOLID)
public class HomeView extends VerticalLayout {

    private TextField name;
    private Button sayHello;

    public HomeView() {
        setSpacing(false);
        setAlignItems(Alignment.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        VerticalLayout header = getHeader();

        HorizontalLayout logoText = new HorizontalLayout();
        Image logo = new Image("images/img.png", "Dentist Logo");
        logo.setWidth("800px");
        Paragraph line1 = new Paragraph("In unserer Zahnarztpraxis trifft moderne Zahnmedizin auf eine entspannte, freundliche Atmosphäre. Wir nehmen uns Zeit für dich und sorgen dafür");
        line1.setWidth("500px");
        line1.getStyle()
                .set("font-size", "22px")
                .set("line-height", "1.6")
                .set("text-align", "left");
        logoText.add(logo, line1);

        Paragraph line2 = new Paragraph(", dass jeder Besuch so angenehm wie möglich ist – von der ersten Beratung bis zum strahlenden Endergebnis. Ob Routinecheck, professionelle Zahnreinigung");
        line2.setWidth("500px");
        line2.getStyle()
                .set("font-size", "22px")
                .set("line-height", "1.6")
                .set("text-align", "left");
        Paragraph line3 = new Paragraph(" oder ästhetische Behandlung: Bei uns steht dein Wohlgefühl im Mittelpunkt. Für ein gesundes Lächeln, das genauso gut aussieht wie es sich anfühlt.");
        line3.setWidth("500px");
        line3.getStyle()
                .set("font-size", "22px")
                .set("line-height", "1.6")
                .set("text-align", "left");

        HorizontalLayout address = new HorizontalLayout();
        H3 name = new H3("Dentist GmbH");
        H3 street = new H3("Spengergasse 20");
        H3 city = new H3("1050 Wien");
        address.add(name, street, city);

        add(header, logoText, line2, line3, address);
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
