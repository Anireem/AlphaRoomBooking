package com.example.alpharoombooking.components;

import com.example.alpharoombooking.models.User;
import com.example.alpharoombooking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        User user = userRepository.findByUsername(authentication.getName());
        String redirectURL = request.getContextPath();
        if (user.hasRole("ADMIN"))
            redirectURL = "/users";
        else if (user.hasRole("EMPLOYEE"))
            redirectURL = "/rooms";
        else if (user.hasRole("OFFICE_MANAGER"))
            redirectURL = "/users";

        response.sendRedirect(redirectURL);
    }
}
