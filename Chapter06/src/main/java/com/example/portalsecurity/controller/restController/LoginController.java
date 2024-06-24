package com.example.portalsecurity.controller.restController;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequestMapping("/hello")
@RestController
public class LoginController {
    //start 6.2.1
    @GetMapping("hello")
    public String hello() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        Object details = authentication.getDetails();
        Object principal = authentication.getPrincipal();
        Object credentials = authentication.getCredentials();
        boolean authenticated = authentication.isAuthenticated();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return "Hi ".concat(name);
    }

    @GetMapping("hello2")
    public String hello2(Authentication authentication) {
        String name = authentication.getName();
        Object details = authentication.getDetails();
        Object principal = authentication.getPrincipal();
        Object credentials = authentication.getCredentials();
        boolean authenticated = authentication.isAuthenticated();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return "Hi 2 ".concat(name);
    }
    //end 6.2.1

    //start 6.2.2
    @Async
    @GetMapping("/bye")
    public void goodbye() {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName(); //nullPinterException
        System.out.println("GoodBye ".concat(username));
    }
    //end 6.2.2

    //start 6.2.4
    @GetMapping("/ciao")
    public String ciao() throws Exception {
        Callable<String> task = () -> {
            //TODO : why it has Authentication ???
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };
        return "ciao: ".concat(task.call()).concat("!");
    }

    @GetMapping("/ciao2")
    public String ciao2() throws Exception {
        Callable<String> task = () -> {
            //hasn't Authentication -> NullPointerException
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };
        ExecutorService service = Executors.newCachedThreadPool();
        try {
            return "ciao2: ".concat(service.submit(task).get()).concat("!");
        } finally {
            service.shutdown();
        }
    }

    @GetMapping("/ciao3")
    public String ciao3() throws Exception {
        Callable<String> task = () -> {
            //has Authentication
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };
        ExecutorService service = Executors.newCachedThreadPool();
        try {
            var securityContextCallableTask = new DelegatingSecurityContextCallable<>(task);
            return "ciao3: ".concat(service.submit(securityContextCallableTask).get()).concat("!");
        } finally {
            service.shutdown();
        }
    }
    //end 6.2.4

    //start 6.2.5
    @GetMapping("/hola")
    public String hola() throws Exception {
        Callable<String> task = () -> {
            //has Authentication
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };
        ExecutorService service = Executors.newCachedThreadPool();
        try {
            service = new DelegatingSecurityContextExecutorService(service);
            return "halo: ".concat(service.submit(task).get()).concat("!");
        } finally {
            service.shutdown();
        }
    }
    //end 6.2.5
}