package br.com.mpetech.controller;

import br.com.mpetech.model.Clientes;
import br.com.mpetech.service.ClientesService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@Controller
@RequestMapping("/acesso")
public class LoginController {

    @Autowired
    private ClientesService clientesService;

    private static final String caminhoImagens = "src//main//resources//static//imagem_perfil//";

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/cadastrar")
    public String cadastrar(Clientes cliente) {
        return "/cadastre_se";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Clientes cliente, BindingResult result, RedirectAttributes attr, @RequestParam("file") MultipartFile arquivo) {

        if (result.hasErrors()) {
            return "/cadastre_se";
        }

        if (cliente.getSenha().equals(cliente.getSenhaRepetida())) {

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

            attr.addFlashAttribute("success", "Cadastro realizado com sucesso!");

        } else {
            attr.addFlashAttribute("danger", "As senhas estão diferentes.");
        }
        return "redirect:/acesso/cadastrar";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {

        if (error != null) {
            model.addAttribute("error", "Credênciais inválidas.");
        }
        return "/login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/home/produtos";
    }

}
