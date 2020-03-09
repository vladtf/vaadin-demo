package com.live.vladislav.ui.views;

import com.live.vladislav.ui.dataProviders.MockDataService;
import com.live.vladislav.ui.models.UserModel;
import com.live.vladislav.ui.services.GreetService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route("")
@RouteAlias(value = "root")
@UIScope
public class MainView extends VerticalLayout {

    public MainView(@Autowired GreetService service) {

        add(new H1("Main View"), new H2("Hello"));

        setUpMenuBar();
        defaultSetup(service);

        setupGrid();

        add(new Button("Go to Sandbox", buttonClickEvent -> UI.getCurrent().navigate("sandbox")));
    }

    @PostConstruct
    private  void goToSandBox(){
        UI.getCurrent().navigate("sandbox");
    }

    private void defaultSetup(@Autowired GreetService service) {
        // Use TextField for standard text input
        TextField textField = new TextField("Your name");

        // Button click listeners can be defined as lambda expressions
        Button button = new Button("Say hello",
                e -> Notification.show(service.greet(textField.getValue())));

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button is more prominent look.
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        button.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");

        Label label = new Label("1");
        Button increaseButtone = new Button("Increase");
        increaseButtone.addClickListener(buttonClickEvent -> IncreaseValues(label));

        Button displayMessageButton = new Button("Show Message", buttonClickEvent -> Notification.show("Hello!"));

        add(displayMessageButton);
        add(textField, button, label, increaseButtone);
    }

    private void setupGrid() {
        Grid<UserModel> grid = new Grid<>();
        grid.addColumn(UserModel::getFirstName);
        grid.addColumn(UserModel::getLastName);
        grid.addColumn(UserModel::getEmail);
        grid.setItems(MockDataService.GetAllPeople());
        add(grid);
    }

    private void setUpMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.addItem("Main", menuItemClickEvent -> UI.getCurrent().navigate("root"));
        menuBar.addItem("Sandbox", menuItemClickEvent -> UI.getCurrent().navigate("sandbox"));
        menuBar.addItem("ToDo", menuItemClickEvent -> UI.getCurrent().navigate("todo"));
        add(menuBar);
    }

    private void IncreaseValues(Label label) {
        int currentValue = Integer.parseInt(label.getText()) + 1;
        label.setText(Integer.toString(currentValue));

    }

}