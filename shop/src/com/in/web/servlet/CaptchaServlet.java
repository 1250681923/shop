package com.in.web.servlet;
 
import com.in.utils.CaptchaUtil;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 
@WebServlet(urlPatterns = "/captcha.png")
public class CaptchaServlet extends HttpServlet {
 
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        response.setContentType("image/png");
        //Obtenir une chaîne aléatoire
        String random = CaptchaUtil.random(); 
        //Sortie
        CaptchaUtil.outputImage(random,response.getOutputStream());
        //Transmettre en ligne
        request.getSession().setAttribute("res",CaptchaUtil.num);
 
    }
 
 
}
