package com.example.application.views.pricing;

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

@PageTitle("Pricing")
@Route("Pricing")
@Menu(order = 2, icon = LineAwesomeIconUrl.FILE)
public class PricingView extends VerticalLayout {

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
