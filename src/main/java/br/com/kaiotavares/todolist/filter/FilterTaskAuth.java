package br.com.kaiotavares.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.kaiotavares.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var serveltPath = request.getServletPath();

        if (serveltPath.startsWith("/tasks/")) {
            // Pegar a autentificação (usuario e senha)
            // Autorização vem indicando um basic <código>
            var authorization = request.getHeader("Authorization");

            var authEncoded = authorization.substring("Basic".length()).trim();// Pegando somenete o código do Base64

            byte[] authDecode = Base64.getDecoder().decode(authEncoded); // Transformando em um array de byte

            var authString = new String(authDecode);

            // ["KaioSande", "12345"]
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            // Validar usuário
            var user = this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(401);
            } else {
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response); //resquest é oq vem e response oq é mandado
                } else {
                    response.sendError(401);
                }
            }
        }else{
            filterChain.doFilter(request, response);
        }

    }
}
