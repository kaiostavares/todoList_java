package br.com.kaiotavares.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                //Autorização vem indicando um basic <código>
                var authorization = request.getHeader("Authorization");

                var authEncoded = authorization.substring("Basic".length()).trim();//Pegando somenete o código do Base64

                byte[] authDecode = Base64.getDecoder().decode(authEncoded); //Transformando em um array de byte

                var authString = new String(authDecode);

                String[] credentials = authString.split(":");
                String username = credentials[0];
                String password = credentials[1];

                System.out.println(username);
                System.out.println(password);

                filterChain.doFilter(request, response);
    }
}
