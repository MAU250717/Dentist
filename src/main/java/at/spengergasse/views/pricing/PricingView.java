package at.spengergasse.views.pricing;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.OptionalDouble;

@PageTitle("Pricing")
@Route("Pricing")
@Menu(order = 2, icon = LineAwesomeIconUrl.FILE)
public class PricingView extends VerticalLayout {

    private VerticalLayout createCard(String zone, double price, OptionalDouble freeAnestesie) {
        H2 zoneTitle = new H2(zone);
        Paragraph priceText = new Paragraph("Lieferkosten: " + price + " Euro");

        String text;
        text = freeAnestesie.isPresent() ? "Gratisanestesie ab: " + freeAnestesie.getAsDouble() + " Euro" : "Keine Gratisanestesie";
        Paragraph freeText = new Paragraph(text);

        VerticalLayout card = new VerticalLayout(zoneTitle, priceText, freeText);
        card.setWidth("350px");
        card.setPadding(true);
        card.setSpacing(false);

        card.getStyle()
                .set("border", "1px solid lightgray")
                .set("border-radius", "10px")
                .set("margin", "10px");

        return card;
    }

    public PricingView() {
        setSpacing(false);

        H2 header = new H2("Pricing Tabelle");
        add(header);

        HorizontalLayout produkte = new HorizontalLayout();

        VerticalLayout produkt1 = new VerticalLayout();
        H2 produktName1 = new H2("Produkt 1");
        Paragraph Preis1 = new Paragraph("Preis: 200,50€ ");
        Paragraph Grundgebur = new Paragraph("Zusätzlich kommt eine Gebür von 100,-€ dazu");
        produkt1.add(produktName1, Preis1, Grundgebur);
        produkte.add(produkt1);

        Paragraph info = new Paragraph("Alle Preise inkl Steuer.");

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
        add(produkte, info);
    }

}
