package com.company.jmixpm.screen.workloadinfo;

import io.jmix.core.entity.KeyValueEntity;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.model.KeyValueContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("WorkloadInfoScreen")
@UiDescriptor("workload-info-screen.xml")
public class WorkloadInfoScreen extends Screen {
    @Autowired
    private KeyValueContainer workloadDc;
    @Autowired
    private Notifications notifications;

    public WorkloadInfoScreen withItem(KeyValueEntity item) {
        workloadDc.setItem(item);
        return this;
    }

    @Subscribe("closeBtn")
    public void onCloseBtnClick(Button.ClickEvent event) {
        close(StandardOutcome.CLOSE);
    }

    @Subscribe(id = "workloadDc", target = Target.DATA_CONTAINER)
    public void onWorkloadDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<KeyValueEntity> event) {
        notifications.create()
                .withCaption("Property: " + event.getProperty())
                .show();
    }
}