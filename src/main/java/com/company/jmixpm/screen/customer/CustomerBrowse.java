package com.company.jmixpm.screen.customer;

import com.company.jmixpm.entity.Project;
import io.jmix.ui.component.PropertyFilter;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Customer;

import javax.inject.Named;

@UiController("Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
public class CustomerBrowse extends StandardLookup<Customer> {
}