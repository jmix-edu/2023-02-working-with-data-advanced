package com.company.jmixpm.listener;

import com.company.jmixpm.security.FullAccessRole;
import io.jmix.core.session.SessionData;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;

@Component
public class AuthenticationEventListener {

    private final ObjectProvider<SessionData> sessionDataProvider;

    public AuthenticationEventListener(ObjectProvider<SessionData> sessionDataProvider) {
        this.sessionDataProvider = sessionDataProvider;
    }

    @EventListener
    public void onInteractiveAuthenticationSuccess(InteractiveAuthenticationSuccessEvent event) {
        SessionData sessionData = sessionDataProvider.getIfAvailable();
        if (sessionData == null) {
            return;
        }

        Authentication authentication = event.getAuthentication();
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (FullAccessRole.CODE.equals(authority.getAuthority())) {
                sessionData.setAttribute("isManager", true);
                return;
            }
        }
        sessionData.setAttribute("isManager", false);
    }
}