package com.live.vladislav.Views;

import com.live.vladislav.Application;
import com.live.vladislav.DataProvider.MockDataService;
import com.live.vladislav.Models.UserModel;
import com.live.vladislav.Services.GreetService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

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
@Route
@RouteAlias(value = "root")
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = true)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    private final Label label;
    private final Label label1;

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired GreetService service) {

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

        label = new Label("Hello world");
        label1 = new Label("1");

        Button increaseButtone = new Button("Increase");
        increaseButtone.addClickListener(buttonClickEvent->IncreaseValues());

        Button displayMessageButton = new Button("Show Message", buttonClickEvent -> Notification.show("Hello!"));

        add(displayMessageButton);
        add(textField, button, label,label1, increaseButtone);

        //SandBoxView view = new SandBoxView();

        Grid<UserModel> grid = new Grid<>();

        grid.addColumn(UserModel::getFirstName);
        grid.addColumn(UserModel::getLastName);
        grid.addColumn(UserModel::getEmail);

        grid.setItems(MockDataService.GetAllPeople());

        add(grid);

        add(new Button("Go to Sandbox", buttonClickEvent -> UI.getCurrent().navigate("sandbox")));

    }

    private void IncreaseValues() {
        int currentValue = Integer.parseInt(label1.getText()) + 1;
        label1.setText(Integer.toString(currentValue));

    }

}
