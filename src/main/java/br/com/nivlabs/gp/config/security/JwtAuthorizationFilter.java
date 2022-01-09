package br.com.nivlabs.gp.config.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.nivlabs.gp.exception.HttpException;
import br.com.nivlabs.gp.repository.UserRepository;

/**
 * Classe AuthorizationFilter.java
 * 
 * @author <a href="mailto:viniciosarodrigues@gmail.com">Vinícios Rodrigues</a>
 * 
 * @since 15 de set de 2019
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private JwtUtils jwtUtils;
    private UserRepository userDetailsService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils,
            UserRepository userDao) {
        super(authenticationManager);
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDao;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain)
            throws IOException, ServletException {

        String authorizationHeader = req.getHeader("Authorization");
        String customerIdHeader = req.getHeader("CUSTOMER_ID");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            UsernamePasswordAuthenticationToken auth = null;
            try {
                if (customerIdHeader == null || customerIdHeader.isEmpty()) {
                    throw new HttpException(HttpStatus.UNAUTHORIZED, "Cabeçalho de identificação do cliente não enviado");
                }

                auth = getAuthentication(authorizationHeader.substring(7));
            } catch (HttpException e) {
                if (e.getStatus() == HttpStatus.UNAUTHORIZED) {
                    resp.setStatus(401);
                    resp.setContentType("application/json;charset=utf-8");
                    resp.getWriter().append(json(req, e));
                    return;
                }
            }

            if (auth != null)
                SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(req, resp);
    }

    /**
     * Converte os erros tratador Http da camada de utilidades de Token para objetos de resposta do filtro
     * 
     * @param req Requisição http
     * @param e Exception lançada pelo utilitário
     * @return Json em String para setar no Stream da resposta do filtro
     */
    private String json(HttpServletRequest req, HttpException e) {
        String jsonResponse = """
                {
                   "timestamp": DATE,
                   "status": 401,
                   "error": "ERROR",
                   "message": "MESSAGE",
                   "path": "PATH"
                }
                """;
        return ("" + jsonResponse)
                .replace("DATE", String.valueOf(new Date().getTime()))
                .replace("ERROR", "Não autorizado")
                .replace("MESSAGE", e.getMessage())
                .replace("PATH", req.getServletPath());
    }

    /**
     * Pega informações do token
     * 
     * @param token
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (jwtUtils.isValidToken(token)) {
            String userName = jwtUtils.getUserName(token);
            var user = userDetailsService.findByUserName(userName)
                    .orElseThrow(() -> new HttpException(HttpStatus.UNAUTHORIZED, "Não autorizado"));

            var userOfSystem =
                             new UserOfSystem(user.getUserName(), user.getPassword(), user.getPerson(), !user.isActive(), user.getRoles());

            return new UsernamePasswordAuthenticationToken(userOfSystem, null, userOfSystem.getAuthorities());
        }
        return null;
    }
}