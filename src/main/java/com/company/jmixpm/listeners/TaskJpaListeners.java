package com.company.jmixpm.listeners;

import com.company.jmixpm.entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

public class TaskJpaListeners {

    private static final Logger log = LoggerFactory.getLogger(TaskJpaListeners.class);

    @PreUpdate
    @PreRemove
    @PrePersist
    public void beforeUpdate(Task task) {
        log.info(task.getClass().getName() + " before update: " + task.getId());
    }

    @PostUpdate
    @PostRemove
    @PostPersist
    public void postUpdate(Task task) {
        log.info(task.getClass().getName() + " post update: " + task.getId());
    }
}
