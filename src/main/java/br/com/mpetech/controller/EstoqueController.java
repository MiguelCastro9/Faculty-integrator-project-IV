package br.com.mpetech.controller;

import br.com.mpetech.enums.EnumStatusPedido;
import br.com.mpetech.model.Compras;
import br.com.mpetech.model.Produtos;
import br.com.mpetech.service.ComprasService;
import br.com.mpetech.service.ProdutosService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@Controller
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    ComprasService comprasService;

    @Autowired
    private ProdutosService produtosService;

    @GetMapping("/pedidos")
    public String historicoCompras(Compras compra, ModelMap model, @RequestParam(defaultValue = "0") int pag) {
        PageRequest pagina = PageRequest.of(pag, 10);
        model.addAttribute("compras", comprasService.lista(pagina));
        model.addAttribute("caminho", "pedidos");
        model.addAttribute("pagAtual", pag);
        return "admin/pedidos";
    }

    @GetMapping("/pedidos/buscar")
    public String buscarPedido(ModelMap model, Integer numeroPedido, @RequestParam(defaultValue = "0") int pag) {
        PageRequest pagina = PageRequest.of(pag, 10);
        model.addAttribute("compras", comprasService.buscaNumeroPedido(numeroPedido, pagina));
        model.addAttribute("caminho", "buscar");
        model.addAttribute("pagAtual", pag);
        return "admin/pedidos";
    }

    @GetMapping("/status/{id}&{statusPedido}")
    public String preAtualizaStatus(@PathVariable("id") Long id, @PathVariable("statusPedido") Integer statusPedido,
            ModelMap model) {
        return atualizaStatus(id, statusPedido, model);
    }

    @PutMapping(value = "/atualizar")
    public String atualizaStatus(@PathVariable("id") Long id, Integer statusPedido,
            ModelMap model) {
        Compras compra = comprasService.findById(id).get();

        switch (statusPedido) {
            case 2:
                compra.setStatusPedido(EnumStatusPedido.PAGAMENTO_APROVADO);
                break;
            case 3:
                compra.setStatusPedido(EnumStatusPedido.PAGAMENTO_REJEITADO);
                break;
            case 4:
                compra.setStatusPedido(EnumStatusPedido.AGUARDANDO_RETIRADA);
                break;
            case 5:
                compra.setStatusPedido(EnumStatusPedido.EM_TRANSITO);
                break;
            case 6:
                compra.setStatusPedido(EnumStatusPedido.ENTREGUE);
        }
        model.addAttribute("compras", compra);

        comprasService.save(compra);

        return "redirect:/estoque/pedidos";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("produtos", produtosService.findById(id));
        return "admin/editar_estoque";
    }

    @PostMapping("/editar")
    public String editar(@Valid Produtos produto, BindingResult result, RedirectAttributes attr) {
        Produtos produtoAtualizado = produtosService.findById(produto.getId()).get();
        
        produtoAtualizado.setQuantidadeProduto(produto.getQuantidadeProduto());

        produtosService.save(produtoAtualizado);
        attr.addFlashAttribute("success", "Estoque do produto editado com sucesso!");
        return "redirect:/estoque/produto/editado";
    }

    @GetMapping("/produto/editado")
    public String editar(Produtos produto) {
        return "admin/editar_estoque";
    }

}
