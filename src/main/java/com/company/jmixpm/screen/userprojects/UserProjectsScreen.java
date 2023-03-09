package com.company.jmixpm.screen.userprojects;

import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.User;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("UserProjectsScreen")
@UiDescriptor("user-projects-screen.xml")
public class UserProjectsScreen extends Screen {
    @Autowired
    private CollectionLoader<Project> projectsDl;
    private User user;

    public User getUser() {
        return user;
    }

    public UserProjectsScreen withUser(User user) {
        this.user = user;

        projectsDl.setParameter("user", user);
        projectsDl.load();

        return this;
    }
}