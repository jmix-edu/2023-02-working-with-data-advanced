package com.company.jmixpm.screen.project;

import com.company.jmixpm.app.ProjectsService;
import com.company.jmixpm.datatype.ProjectLabels;
import com.company.jmixpm.entity.Project;
import com.company.jmixpm.screen.user.UserBrowse;
import io.jmix.audit.snapshot.EntitySnapshotManager;
import io.jmix.core.validation.group.UiComponentChecks;
import io.jmix.core.validation.group.UiCrossFieldChecks;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.TextArea;
import io.jmix.ui.component.validator.BeanPropertyValidator;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@UiController("Project.edit")
@UiDescriptor("project-edit.xml")
@EditedEntityContainer("projectDc")
public class ProjectEdit extends StandardEditor<Project> {
//    @Autowired
//    private TextArea<ProjectLabels> projectLabelsField;
    @Autowired
    private ProjectsService projectsService;
    @Autowired
    private Notifications notifications;

    @Autowired
    private Validator validator;
    @Autowired
    private EntitySnapshotManager entitySnapshotManager;

    @Subscribe
    public void onInit(InitEvent event) {
        /*Collection<Validator<ProjectLabels>> validators = projectLabelsField.getValidators();
        for (Validator<ProjectLabels> fieldValidator : validators) {
            if (fieldValidator instanceof BeanPropertyValidator) {
                ((BeanPropertyValidator) fieldValidator).setValidationGroups(new Class[]{UiCrossFieldChecks.class});
            }
        }*/
    }

    @Subscribe
    public void onAfterCommitChanges(AfterCommitChangesEvent event) {
        entitySnapshotManager.createSnapshot(getEditedEntity(),
                getEditedEntityContainer().getFetchPlan());
    }

    @Subscribe
    public void onInitEntity(InitEntityEvent<Project> event) {
//        projectLabelsField.setEditable(true);
        event.getEntity().setProjectLabels(new ProjectLabels(List.of("bug", "enhancement", "task")));
    }

    @Subscribe("commitWithBeanValidation")
    public void onCommitWithBeanValidationClick(Button.ClickEvent event) {
        try {
            projectsService.saveProject(getEditedEntity());
        } catch (ConstraintViolationException e) {
            showBeanValidationException((Set) e.getConstraintViolations());
        }
    }

    @Subscribe("performBeanValidation")
    public void onPerformBeanValidationClick(Button.ClickEvent event) {
        Set<ConstraintViolation<Project>> violations = validator.validate(getEditedEntity(), Default.class, UiComponentChecks.class, UiCrossFieldChecks.class);

        showBeanValidationException(violations);
    }

    private void showBeanValidationException(Set<ConstraintViolation<Project>> violations) {
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : violations) {
            sb.append(constraintViolation.getMessage()).append("\n");
        }

        notifications.create()
                .withType(Notifications.NotificationType.TRAY)
                .withCaption(sb.toString())
                .show();
    }

    @Install(to = "participantsTable.add", subject = "screenConfigurer")
    private void participantsTableAddScreenConfigurer(Screen screen) {
        ((UserBrowse) screen).setFilterProject(getEditedEntity());
    }


}