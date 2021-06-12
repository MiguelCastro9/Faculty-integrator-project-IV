package br.com.mpetech.controller;

import br.com.mpetech.enums.EnumStatusPedido;
import br.com.mpetech.model.CarrinhoCompras;
import br.com.mpetech.model.Clientes;
import br.com.mpetech.model.Compras;
import br.com.mpetech.resources.Compras_ExportPdf;
import br.com.mpetech.service.ClientesService;
import br.com.mpetech.service.ComprasService;
import br.com.mpetech.service.EnderecosExtrasService;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@Controller
@RequestMapping("/checkout")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CheckoutController {

    @Autowired
    ClientesService clientesService;

    @Autowired
    EnderecosExtrasService enderecosExtrasService;

    @Autowired
    ComprasService comprasService;

    @Autowired
    private CarrinhoCompras carrinho;
    
    @GetMapping("/pagamento")
    public String pagamentoAnonimo() {
        
        return "cliente/checkout";
    }

    @GetMapping("/pagamento/{id}")
    public String pagamentoCliente(@PathVariable("id") Clientes id, ModelMap model, Compras compra) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user;

        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        } else {
            user = principal.toString();
        }
        
        Random random = ThreadLocalRandom.current();

        model.addAttribute("clientes", clientesService.exibePerfil(user));
        model.addAttribute("frete2", random.nextInt(80 - 60 + 1) + 60);
        model.addAttribute("frete5", random.nextInt(50 - 25 + 1) + 25);
        model.addAttribute("frete7", random.nextInt(20 - 05 + 1) + 05);
        model.addAttribute("enderecosExtras", enderecosExtrasService.listarEnderecoExtraCliente(id));
        model.addAttribute("carrinhoCompras", carrinho);     
        
        return "cliente/checkout";
    }

    @PostMapping("/compra")
    public String compra(Clientes cliente, ModelMap model, @Valid Compras compra, BindingResult result, CarrinhoCompras carrinhoCompra, RedirectAttributes ra) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user;

        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        } else {
            user = principal.toString();
        }

        if (result.hasErrors()) {
            
            return pagamentoCliente(Clientes.class.cast(cliente.getId()), model, compra);
        }

        LocalDateTime hoje = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String hojeFormatado = hoje.format(formato);

        Random random = new Random();
        
        compra.setDataCompra(hojeFormatado);
        compra.setNumeroPedido(random.nextInt(999999));
        compra.setStatusPedido(EnumStatusPedido.AGUARDANDO_PAGAMENTO);
        compra.setEmailCliente(user);
        comprasService.save(compra);
        Integer numeroPedido = compra.getNumeroPedido();
        ra.addFlashAttribute("idPedido", numeroPedido);
        return "redirect:/checkout/compra_finalizada";
    }

    @GetMapping("/compra_finalizada")
    public String compraFinalizada(ModelMap model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user;

        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        } else {
            user = principal.toString();
        }

        model.addAttribute("clientes", clientesService.exibePerfil(user));
        model.addAttribute("carrinhoCompras", carrinho);
        carrinho.removerTodos();
        return "cliente/compra_finalizada";
    }

    @GetMapping(value = "/exportPdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> exportPdf(@PathVariable("id") Long id, ModelMap model) throws IOException, BadElementException, DocumentException {
        model.addAttribute("compras", comprasService.findById(id).get());
        ByteArrayInputStream bis = Compras_ExportPdf.comprasExportPdf(comprasService.historicoClienteId(id));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=MPE Tech-nota fiscal.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
