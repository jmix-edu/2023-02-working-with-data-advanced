package com.company.jmixpm.screen.customerserach;

import com.company.jmixpm.entity.Customer;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("CustomerSerachScreen")
@UiDescriptor("customer-serach-screen.xml")
public class CustomerSerachScreen extends Screen {
    private static final Logger log = LoggerFactory.getLogger(CustomerSerachScreen.class);
    @Autowired
    private CollectionContainer<Customer> customersDc;

    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        log.info("AfterInitEvent - customers size: " + customersDc.getItems().size());
    }

    @Subscribe
    public void onInit(InitEvent event) {
        log.info("InitEvent - customers size: " + customersDc.getItems().size());
    }
}
