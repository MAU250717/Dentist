package at.spengergasse.views.pricing;

import at.spengergasse.views.home.HomeView;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
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
        Paragraph priceText = new Paragraph("Mindestkosten: " + price + " Euro");

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

        add(HomeView.getHeader());

        VerticalLayout zone1 = createCard("5. Bezirk", 30.9, OptionalDouble.of(150.0));
        VerticalLayout zone2 = createCard("3. und 4. Bezirk", 22.9, OptionalDouble.of(200));
        VerticalLayout zone3 = createCard("Wien", 27.9, OptionalDouble.of(300));
        VerticalLayout zone4 = createCard("Außerhalb von Wien", 25, OptionalDouble.empty());
        FlexLayout cardsLayout = new FlexLayout(zone1, zone2, zone3, zone4);
        cardsLayout.setWidthFull();
        cardsLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        cardsLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        add(cardsLayout);

        Paragraph info = new Paragraph("Wir liefern nur die besten Ergebnisse für unsere Patienten - und das 24/7.");
        info.setWidth("100%");
        info.getStyle().set("text-align", "center");
        add(info);
    }
}
