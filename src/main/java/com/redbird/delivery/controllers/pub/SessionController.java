package com.redbird.delivery.controllers.pub;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("session")
@PreAuthorize("hasAuthority('permission:user')")
public class SessionController {

    @GetMapping
    public String getSessionParams(HttpServletRequest request, Principal principal, Model model) {
        model.addAttribute("theme", request.getSession().getAttribute("THEME"));
        model.addAttribute("language", request.getSession().getAttribute("LANGUAGE"));
        model.addAttribute("username", principal.getName());
        return "session";
    }

    @PostMapping()
    public String addSessionParams(@RequestParam(required = false) String theme,
                                   @RequestParam(required = false) String language,
                                   HttpServletRequest request,
                                   Model model) {
        if (theme != null) {
            if (theme.equals("")) {
                request.getSession().setAttribute("THEME", "light");
            }
            else {
            request.getSession().setAttribute("THEME", theme);
            }
        }
        if (language != null) {
            if (language.equals("")) {
                request.getSession().setAttribute("LANGUAGE", "english");
            }
            else {
                request.getSession().setAttribute("LANGUAGE", language);
            }
        }
        return "redirect:/session";
    }
}
