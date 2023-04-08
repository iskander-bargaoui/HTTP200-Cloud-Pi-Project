package tn.esprit.pibakcend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;



@Component
public class RequestLoggingFilter implements Filter {
    private final Set<String> prohibitedWords = new HashSet<>(Arrays.asList("fuck", "shit", "asshole"));

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        // Check if the request is for creating a new publication (i.e., POST request to /publications endpoint)
        if ("POST".equalsIgnoreCase(req.getMethod()) && "/*".equals(req.getServletPath())) {
            String title = req.getParameter("title");
            String content = req.getParameter("content");

            // Check if the title or content contain any prohibited words
            if (containsProhibitedWords(title) || containsProhibitedWords(content)) {
                HttpServletResponse res = (HttpServletResponse) response;
                res.setStatus(HttpStatus.BAD_REQUEST.value());
                res.getWriter().write("Publication contains prohibited words.");
                return;
            }
        }

        // Proceed with the request
        chain.doFilter(request, response);
    }

    private boolean containsProhibitedWords(String text) {
        for (String word : prohibitedWords) {
            if (text.toLowerCase().contains(word)) {
                return true;
            }
        }
        return false;
    }
}
