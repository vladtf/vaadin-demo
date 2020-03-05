package com.live.vladislav.DataProvider;

import com.live.vladislav.Models.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MockDataService {
    public static List<UserModel> GetAllPeople() {
        List users = new ArrayList<UserModel>();

        users.add(new UserModel("Andrei", "Popescu", "andpop@email.com"));
        users.add(new UserModel("Ion", "Ionescu", "ionnn@email.com"));
        users.add(new UserModel("Radu", "Georgescu", "rad.georg@email.com"));
        users.add(new UserModel("George", "Alexandru", "geoalex@email.com"));

        return users;
    }
}
