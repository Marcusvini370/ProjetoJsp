package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnetionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/principal/*"})/* Filter =Intercepta todas as requisi��es que vierem do projeto ou mapeamento */
public class FilterAutenticacao implements Filter {

	private static Connection connection;
   
    public FilterAutenticacao() {
        
    }

    /* Encerra os processos quando o servidor � parado */
    /* Mataria os processos de conex�o com o banco */
	public void destroy() {
		
		try {
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	/* Intercepra as requisi��es e as respostas do sistema */
	/* tudo que fizer no  sistema vai fazer por aqui */
	/*Valida��o de autentica��o, dar commit e rollback de transa��es no banco,
	 *  validar e fazer redirecionamento de p�ginas */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	try {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		String urlParaAutenticar = req.getServletPath(); /*Url est� sendo acessada */
		
		/* Validase est� logado sen�o redireciona para a dela de login */
		if (usuarioLogado == null  && 
			!urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) { /* N�o est� logado */
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "Por Favor, realize o login!");
			redireciona.forward(req, response);
			return; /*Para a execu��o e redireciona para o login */
		}else {
			
			chain.doFilter(request, response);
		}
		
		connection.commit(); /* Deu tudo certo, ent�o comita as altera��es no banco de dados */
		
	}catch(Exception e) {
		e.printStackTrace();
		
		RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
		request.setAttribute("msg", e.getMessage());
		redirecionar.forward(request, response);
		
		try {
			connection.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	}

/* Inicia os processos ou recursos quando o servidor sobe o projeto */
	//EX: iniciar a conex�o com o banco quando subir o proejto
	public void init(FilterConfig fConfig) throws ServletException {
		
		connection = SingleConnetionBanco.getConnection();
	}

}
