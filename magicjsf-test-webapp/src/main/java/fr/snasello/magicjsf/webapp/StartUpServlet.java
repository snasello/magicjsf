package fr.snasello.magicjsf.webapp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import fr.snasello.magicjsf.common.service.InitService;

public class StartUpServlet extends HttpServlet {

	private static final long serialVersionUID = -3728100326340639176L;

	@Override
	public void init() throws ServletException {
		super.init();
		InitService initService = new InitService();
		initService.init();
	}

}
