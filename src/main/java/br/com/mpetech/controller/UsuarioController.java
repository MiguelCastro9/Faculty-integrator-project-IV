package br.com.mpetech.controller;

import br.com.mpetech.model.Usuarios;
import br.com.mpetech.resources.Usuarios_ExportExel;
import br.com.mpetech.resources.Usuarios_ExportPdf;
import br.com.mpetech.service.ComprasService;
import br.com.mpetech.service.UsuariosService;
import com.itextpdf.text.BadElementException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuariosService usuariosService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    ComprasService comprasService;

    private final int statusAtivo = 1;

    private final int statusInativo = 2;

    @GetMapping("/cadastrar")
    public String cadastrar(Usuarios usuario) {
        return "admin/cadastro_admins";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Usuarios usuario, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "admin/cadastro_admins";
        }

        if (usuario.getSenha().equals(usuario.getSenhaRepetida())) {

            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            usuario.setSenhaRepetida(passwordEncoder.encode(usuario.getSenhaRepetida()));
            usuariosService.save(usuario);
            attr.addFlashAttribute("success", "Usuário cadastrado com sucesso!");

        } else {

            attr.addFlashAttribute("danger", "As senhas estão diferentes.");
        }

        return "redirect:/usuarios/cadastrar";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model, @RequestParam(defaultValue = "0") int pag) {
        PageRequest pagina = PageRequest.of(pag, 10);
        model.addAttribute("usuarios", usuariosService.listaDESC(pagina));
        model.addAttribute("caminho", "listar");
        model.addAttribute("pagAtual", pag);
        return "admin/lista_usuarios";
    }

    @GetMapping("/buscar")
    public String busca(ModelMap model, String nome, @RequestParam(defaultValue = "0") int pag) {
        PageRequest pagina = PageRequest.of(pag, 10);
        model.addAttribute("usuarios", usuariosService.buscaNomeUsuario(nome, pagina));
        model.addAttribute("caminho", "buscar");
        model.addAttribute("pagAtual", pag);
        return "admin/lista_usuarios";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("usuarios", usuariosService.findById(id));
        return "admin/cadastro_admins";
    }

    @PostMapping("/editar")
    public String editar(@Valid Usuarios usuario, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "admin/cadastro_admins";
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setSenhaRepetida(passwordEncoder.encode(usuario.getSenhaRepetida()));
        usuariosService.save(usuario);

        attr.addFlashAttribute("success", "Usuário editado com sucesso!");
        return "redirect:/usuarios/cadastrar";
    }

    @GetMapping(value = "/exportPdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> exportPdf(ModelMap model) throws IOException, BadElementException {
        model.addAttribute("usuarios", usuariosService.findAll());
        ByteArrayInputStream bis = Usuarios_ExportPdf.usuariosExportPdf(usuariosService.findAll());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=MPE Tech-usuários internos.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping("/exportExel")
    public void exportExel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=MPE Tech-usuários internos.xlsx");
        ByteArrayInputStream stream = Usuarios_ExportExel.usuariosExportExel(usuariosService.findAll());
        IOUtils.copy(stream, response.getOutputStream());
    }

    @GetMapping("/status/{id}")
    public String preAtualizaStatus(@PathVariable("id") Long id, ModelMap model) {
        return atualizaStatus(id, model);
    }

    @PutMapping(value = "/atualizar")
    public String atualizaStatus(@PathVariable("id") Long id, ModelMap model) {
        Usuarios usuario = usuariosService.findById(id).get();
        model.addAttribute("usuarios", usuario);
        if (usuario.getStatus() == statusAtivo) {
            usuario.setStatus(statusInativo);
        } else {
            usuario.setStatus(statusAtivo);
        }
        usuariosService.save(usuario);
        return "redirect:/usuarios/listar";
    }
}
