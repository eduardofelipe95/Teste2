import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

@WebServlet("/adicionaContato")
public class addContatoServlet extends HttpServlet {
    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
                        
        PrintWriter out = response.getWriter();
                        
        // pegando os parâmetros do request
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String dataEmTexto = request.getParameter("dataNascimento");
        Calendar dataNascimento = null;
        
        // fazendo a conversão da data
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy")
                    .parse(dataEmTexto);
            dataNascimento = Calendar.getInstance();
            dataNascimento.setTime(date);
        } catch (ParseException e) {
            out.println("Erro de conversão da data");
            return; //para a execução do método
        } catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // monta um objeto contato
        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setEndereco(endereco);
        contato.setEmail(email);
        contato.setDataNascimento(dataNascimento);
        
        // salva o contato
        ContatoDao dao = new ContatoDao();
        dao.adiciona(contato);
        
        // imprime o nome do contato que foi adicionado
        out.println("<html>");
        out.println("<body>");
        out.println("Contato " + contato.getNome() + 
                " adicionado com sucesso");    
        out.println("</body>");
        out.println("</html>");
    }
}