package br.com.mpetech.controller;

import br.com.mpetech.model.CarrinhoCompras;
import br.com.mpetech.model.Produtos;
import br.com.mpetech.service.ClientesService;
import br.com.mpetech.service.ProdutosService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@Controller
@RequestMapping("/home")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class HomeController {

    @Autowired
    private ProdutosService produtosService;

    @Autowired
    private ClientesService clientesService;
    
    @Autowired
    CarrinhoCompras carrinhoCompras;

    private static final String caminhoImagens = "src//main//resources//static//imagem_produtos//";
    
    @GetMapping("/dashboard")
    public String index(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user;

        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        } else {
            user = principal.toString();
        }

        model.addAttribute("clientes", clientesService.exibePerfil(user));
        model.addAttribute("carrinhoCompras", carrinhoCompras);
        return "/index";
    }

    @GetMapping("/produtos")
    public String home(ModelMap model, @RequestParam(defaultValue = "0") int pag) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user;

        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        } else {
            user = principal.toString();
        }

        PageRequest pagina = PageRequest.of(pag, 9);
        model.addAttribute("produtos", produtosService.findAll(pagina));
        model.addAttribute("clientes", clientesService.exibePerfil(user));
        model.addAttribute("caminho", "produtos");
        model.addAttribute("pagAtual", pag);
        model.addAttribute("carrinhoCompras", carrinhoCompras);
        return "/produtos";
    }

    @GetMapping("/exibeImagem/{imagem}")
    @ResponseBody
    public byte[] exibeImagem(@PathVariable("imagem") String imagem) throws IOException {

        File imagemArquivo = new File(caminhoImagens + imagem);
        return Files.readAllBytes(imagemArquivo.toPath());
    }

    @GetMapping("/detalhes/{id}")
    public String detalhes(@PathVariable("id") Long id, Produtos produto, ModelMap model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user;

        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        } else {
            user = principal.toString();
        }

        produto = produtosService.findById(id).get();
        model.addAttribute("produtos", produto);
        model.addAttribute("clientes", clientesService.exibePerfil(user));
        model.addAttribute("carrinhoCompras", carrinhoCompras);

        return "/detalhes";
    }
}
