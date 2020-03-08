package com.live.vladislav.ui.views;

import com.live.vladislav.backend.entity.Company;
import com.live.vladislav.backend.entity.Contact;
import com.live.vladislav.backend.service.ContactService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@Route(value = "sandbox")
@UIScope
public class SandBoxView extends VerticalLayout {

    private Grid<Contact> grid = new Grid<Contact>(Contact.class);
    private TextField filterText = new TextField();
    private ContactService contactService;

    public SandBoxView(ContactService contactService) {
        this.contactService = contactService;
        add(new Button("Go back", buttonClickEvent -> UI.getCurrent().navigate("root")));

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureFilter();

        add(filterText,grid);
        updateList();
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("company");
        grid.setColumns("firstName", "lastName", "email", "status");
        grid.addColumn(contact -> {
            Company company = contact.getCompany();
            return company == null ? "-" : company.getName();
        }).setHeader("Company");

        grid.getColumns().forEach(col->col.setAutoWidth(true));
    }

    private void configureFilter() {
        filterText.setPlaceholder("Filter by name ...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateList());
    }

    private void updateList() {
        grid.setItems(contactService.findAll(filterText.getValue()));
    }
}