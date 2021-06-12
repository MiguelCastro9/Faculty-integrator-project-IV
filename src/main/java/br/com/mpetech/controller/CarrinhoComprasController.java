package br.com.mpetech.controller;

import br.com.mpetech.model.CarrinhoCompras;
import br.com.mpetech.model.CarrinhoItem;
import br.com.mpetech.model.Produtos;
import br.com.mpetech.service.ClientesService;
import br.com.mpetech.service.ProdutosService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@Controller
@RequestMapping("/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {

    @Autowired
    private ProdutosService produtoService;
    
    @Autowired
    ClientesService clientesService;

    @Autowired
    private CarrinhoCompras carrinho;
    
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Integer produtoId, Model model) {
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user;

        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        } else {
            user = principal.toString();
        }

        model.addAttribute("clientes", clientesService.exibePerfil(user));
        
        CarrinhoItem carrinhoItem = criaItem(produtoId);
        carrinho.add(carrinhoItem);

        model.addAttribute("carrinhoCompras", carrinho);

        return "redirect:/carrinho/exibir";
    }
    
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer produtoId, Model model, RedirectAttributes attr) {
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user;

        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        } else {
            user = principal.toString();
        }

        model.addAttribute("clientes", clientesService.exibePerfil(user));
        
        CarrinhoItem carrinhoItem = criaItem(produtoId);
        if(carrinho.getQuantidade(carrinhoItem) == 1){
            remover(produtoId, model);
        }else{
           carrinho.remove(carrinhoItem);
        }

        model.addAttribute("carrinhoCompras", carrinho);

        return "redirect:/carrinho/exibir";
    }

    private CarrinhoItem criaItem(Integer produtoId) {
        Long produtoIdL = produtoId.longValue();
        Produtos produto = produtoService.findById(produtoIdL).get();
        CarrinhoItem carrinhoItem = new CarrinhoItem(produto);

        return carrinhoItem;
    }

    @GetMapping("/exibir")
    public String exibirCarrinho(Model model) {
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user;

        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        } else {
            user = principal.toString();
        }

        model.addAttribute("clientes", clientesService.exibePerfil(user));
        model.addAttribute("carrinhoCompras", carrinho);
        return "/carrinho_compras";
    }

    @RequestMapping("/remove-item/{id}")
    public String remover(@PathVariable("id") Integer produtoId, Model model) {
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user;

        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        } else {
            user = principal.toString();
        }

        model.addAttribute("clientes", clientesService.exibePerfil(user));
        
        carrinho.remover(produtoId);
        model.addAttribute("carrinhoCompras", carrinho);
        return "/carrinho_compras";
    }

}
