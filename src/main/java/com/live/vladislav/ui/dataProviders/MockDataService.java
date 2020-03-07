package com.live.vladislav.ui.dataProviders;

import com.live.vladislav.ui.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class MockDataService {
    public static List<UserModel> GetAllPeople() {
        ArrayList<UserModel> users = new ArrayList<>();

        users.add(new UserModel("Andrei", "Popescu", "andpop@email.com"));
        users.add(new UserModel("Ion", "Ionescu", "ionnn@email.com"));
        users.add(new UserModel("Radu", "Georgescu", "rad.georg@email.com"));
        users.add(new UserModel("George", "Alexandru", "geoalex@email.com"));

        return users;
    }
}
