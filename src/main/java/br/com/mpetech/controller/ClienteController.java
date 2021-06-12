package br.com.mpetech.controller;

import br.com.mpetech.model.CarrinhoCompras;
import br.com.mpetech.model.Clientes;
import br.com.mpetech.model.Compras;
import br.com.mpetech.model.EnderecosExtras;
import br.com.mpetech.service.ClientesService;
import br.com.mpetech.service.ComprasService;
import br.com.mpetech.service.EnderecosExtrasService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@Controller
@RequestMapping("/clientes")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class ClienteController {

    @Autowired
    ClientesService clientesService;

    @Autowired
    EnderecosExtrasService enderecosExtrasService;
    
    @Autowired
    ComprasService comprasService;
    
    @Autowired
    private CarrinhoCompras carrinho;

    private static final String caminhoImagens = "src//main//resources//static//imagem_perfil//";

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/atualizar")
    public String atualizar(Clientes cliente) {
        return "cliente/editar_cliente";
    }

    @GetMapping("/perfil/{email}")
    public String perfil(@PathVariable("email") String email, Clientes cliente, ModelMap model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user;

        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        } else {
            user = principal.toString();
        }
        
        model.addAttribute("carrinhoCompras", carrinho);
        model.addAttribute("clientes", clientesService.exibePerfil(user));

        return "cliente/perfil_cliente";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("clientes", clientesService.findById(id));
        return "cliente/editar_cliente";
    }

    @PostMapping("/editar")
    public String editar(@Valid Clientes cliente, BindingResult result, RedirectAttributes attr, @RequestParam("file") MultipartFile arquivo) {

        if (result.hasErrors()) {
            return "cliente/editar_cliente";
        }

        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
        cliente.setSenhaRepetida(passwordEncoder.encode(cliente.getSenhaRepetida()));
        cliente.setStatus(1);
        cliente.setPerfil("ROLE_USER");
        clientesService.save(cliente);
        try {
            if (!arquivo.isEmpty()) {
                byte[] bytes = arquivo.getBytes();
                Path caminho = Paths.get(caminhoImagens + String.valueOf(cliente.getId()) + arquivo.getOriginalFilename());
                Files.write(caminho, bytes);
                cliente.setImagemPerfil(String.valueOf(cliente.getId()) + arquivo.getOriginalFilename());
                clientesService.save(cliente);
            }
        } catch (IOException erro) {
            erro.getMessage();
        }
        attr.addFlashAttribute("success", "Perfil editado com sucesso!");
        return "redirect:/clientes/atualizar";
    }

    @GetMapping("/novo_endereco")
    public String novoEndereco(EnderecosExtras enderecosExtra, ModelMap model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user;

        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        } else {
            user = principal.toString();
        }

        model.addAttribute("clientes", clientesService.exibePerfil(user));
        model.addAttribute("carrinhoCompras", carrinho);
        
        return "cliente/novo_endereco";
    }

    @PostMapping("/salvar_endereco")
    public String salvarEndereco(Clientes cliente, @Valid EnderecosExtras enderecosExtra, BindingResult result, RedirectAttributes attr, ModelMap model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user;

        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        } else {
            user = principal.toString();
        }

        model.addAttribute("clientes", clientesService.exibePerfil(user));
        model.addAttribute("carrinhoCompras", carrinho);
        
        if (result.hasErrors()) {
            return "cliente/novo_endereco";
        }

        enderecosExtra.setCliente(Clientes.class.cast(clientesService.buscaIdCliente(user)));
        enderecosExtrasService.save(enderecosExtra);
        attr.addFlashAttribute("success", "Novo endereço cadastrado com sucesso!");
        return "redirect:/clientes/novo_endereco";
    }

    @GetMapping("/meus_enderecos/{id}")
    public String listarEndereco(@PathVariable("id") Clientes id, EnderecosExtras enderecosExtra, Clientes cliente, ModelMap model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user;

        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        } else {
            user = principal.toString();
        }
        
        model.addAttribute("carrinhoCompras", carrinho);
        model.addAttribute("clientes", clientesService.exibePerfil(user));
        model.addAttribute("enderecosExtras", enderecosExtrasService.listarEnderecoExtraCliente(id)); 

        return "cliente/lista_endereco";
    }

    @GetMapping("/editar_endereco/{id}")
    public String preEditarEndereco(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("enderecosExtras", enderecosExtrasService.findById(id));
        return "cliente/novo_endereco";
    }

    @PostMapping("/editar_endereco")
    public String editarEndereco(Clientes cliente, @Valid EnderecosExtras enderecosExtra, BindingResult result, RedirectAttributes attr, ModelMap model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user;

        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        } else {
            user = principal.toString();
        }

        model.addAttribute("clientes", clientesService.exibePerfil(user));

        if (result.hasErrors()) {
            return "cliente/novo_endereco";
        }

        enderecosExtra.setCliente(Clientes.class.cast(clientesService.buscaIdCliente(user)));
        enderecosExtrasService.save(enderecosExtra);
        attr.addFlashAttribute("success", "Endereço editado com sucesso!");
        return "redirect:/clientes/novo_endereco";
    }

    @GetMapping("/excluir_endereco/{id}")
    public String excluirEndereco(@PathVariable("id") Long id, RedirectAttributes attr) {

        enderecosExtrasService.deleteById(id);
        attr.addFlashAttribute("success", "Endereço excluído com sucesso!");
        return "redirect:/clientes/novo_endereco";
    }

    @GetMapping("/historico_compras")
    public String historicoCompras(Compras compra, ModelMap model) {
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user;

        if (principal instanceof UserDetails) {
            user = ((UserDetails) principal).getUsername();
        } else {
            user = principal.toString();
        }
        
        model.addAttribute("carrinhoCompras", carrinho);
        model.addAttribute("clientes", clientesService.exibePerfil(user));
        model.addAttribute("compras", comprasService.historicoCliente(user));
        
        return "cliente/historico_compra";
    }

}
