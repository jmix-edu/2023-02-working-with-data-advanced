package com.company.jmixpm.screen.roadmap;

import io.jmix.ui.Notifications;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Roadmap;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Roadmap.edit")
@UiDescriptor("roadmap-edit.xml")
@EditedEntityContainer("roadmapDc")
public class RoadmapEdit extends StandardEditor<Roadmap> {
    @Autowired
    private Notifications notifications;

    @Subscribe(id = "roadmapDc", target = Target.DATA_CONTAINER)
    public void onRoadmapDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Roadmap> event) {
        notifications.create(Notifications.NotificationType.TRAY)
                .withCaption("Property: " + event.getProperty())
                .show();
    }


}