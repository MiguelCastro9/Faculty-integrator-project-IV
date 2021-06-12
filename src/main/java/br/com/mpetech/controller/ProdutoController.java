package br.com.mpetech.controller;

import br.com.mpetech.enums.EnumStatusProduto;
import br.com.mpetech.model.Produtos;
import br.com.mpetech.resources.Produtos_ExportExel;
import br.com.mpetech.resources.Produtos_ExportPdf;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.mpetech.service.ProdutosService;
import com.itextpdf.text.BadElementException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutosService produtosService;

    private static final String caminhoImagens = "src//main//resources//static//imagem_produtos//";

    @GetMapping("/cadastrar")
    public String cadastrar(Produtos produto) {
        return "admin/cadastro_produtos";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model, @RequestParam(defaultValue = "0") int pag) {
        PageRequest pagina = PageRequest.of(pag, 10);
        model.addAttribute("produtos", produtosService.findAll(pagina));
        model.addAttribute("caminho", "listar");
        model.addAttribute("pagAtual", pag);
        return "admin/lista_produtos";
    }

    @GetMapping("/buscar")
    public String busca(ModelMap model, String nome, @RequestParam(defaultValue = "0") int pag) {
        PageRequest pagina = PageRequest.of(pag, 10);
        model.addAttribute("produtos", produtosService.buscaNomeProduto(nome, pagina));
        model.addAttribute("caminho", "buscar");
        model.addAttribute("pagAtual", pag);
        return "admin/lista_produtos";
    }

    @GetMapping("/exibeImagem/{imagem}")
    @ResponseBody
    public byte[] exibeImagem(@PathVariable("imagem") String imagem) throws IOException {

        File imagemArquivo = new File(caminhoImagens + imagem);
        return Files.readAllBytes(imagemArquivo.toPath());
    }

    @GetMapping("/detalhes/{id}")
    public String detalhes(@PathVariable("id") Long id, Produtos produto, ModelMap model) {

        produto = produtosService.findById(id).get();
        model.addAttribute("produtos", produto);

        return "admin/detalhes_produtos";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Produtos produto, BindingResult result, RedirectAttributes attr,
            @RequestParam("file1") MultipartFile arquivo1,
            @RequestParam("file2") MultipartFile arquivo2,
            @RequestParam("file3") MultipartFile arquivo3) {

        if (result.hasErrors()) {
            return "admin/cadastro_produtos";
        }

        produtosService.save(produto);

        try {
            if (!arquivo1.isEmpty()) {

                byte[] bytes1 = arquivo1.getBytes();
                byte[] bytes2 = arquivo2.getBytes();
                byte[] bytes3 = arquivo3.getBytes();
                Path caminho1 = Paths.get(caminhoImagens + String.valueOf(produto.getId()) + arquivo1.getOriginalFilename());
                Path caminho2 = Paths.get(caminhoImagens + String.valueOf(produto.getId()) + arquivo2.getOriginalFilename());
                Path caminho3 = Paths.get(caminhoImagens + String.valueOf(produto.getId()) + arquivo3.getOriginalFilename());
                Files.write(caminho1, bytes1);
                Files.write(caminho2, bytes2);
                Files.write(caminho3, bytes3);
                produto.setImagemPrimaria(String.valueOf(produto.getId()) + arquivo1.getOriginalFilename());
                produto.setImagemSecundaria(String.valueOf(produto.getId()) + arquivo2.getOriginalFilename());
                produto.setImagemTerciaria(String.valueOf(produto.getId()) + arquivo3.getOriginalFilename());
                produtosService.save(produto);
            }

        } catch (IOException erro) {
            erro.getMessage();
        }

        attr.addFlashAttribute("success", "Produto incluído com sucesso!");
        return "redirect:/produtos/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("produtos", produtosService.findById(id));
        return "admin/cadastro_produtos";
    }

    @PostMapping("/editar")
    public String editar(@Valid Produtos produto, BindingResult result, RedirectAttributes attr,
            @RequestParam("file1") MultipartFile arquivo1,
            @RequestParam("file2") MultipartFile arquivo2,
            @RequestParam("file3") MultipartFile arquivo3) {

        if (result.hasErrors()) {
            return "admin/cadastro_produtos";
        }

        try {
            if (!arquivo1.isEmpty()) {

                byte[] bytes1 = arquivo1.getBytes();
                byte[] bytes2 = arquivo2.getBytes();
                byte[] bytes3 = arquivo3.getBytes();
                Path caminho1 = Paths.get(caminhoImagens + String.valueOf(produto.getId()) + arquivo1.getOriginalFilename());
                Path caminho2 = Paths.get(caminhoImagens + String.valueOf(produto.getId()) + arquivo2.getOriginalFilename());
                Path caminho3 = Paths.get(caminhoImagens + String.valueOf(produto.getId()) + arquivo3.getOriginalFilename());
                Files.write(caminho1, bytes1);
                Files.write(caminho2, bytes2);
                Files.write(caminho3, bytes3);
                produto.setImagemPrimaria(String.valueOf(produto.getId()) + arquivo1.getOriginalFilename());
                produto.setImagemSecundaria(String.valueOf(produto.getId()) + arquivo2.getOriginalFilename());
                produto.setImagemTerciaria(String.valueOf(produto.getId()) + arquivo3.getOriginalFilename());

            }

        } catch (IOException erro) {
            erro.getMessage();
        }
        produtosService.save(produto);
        attr.addFlashAttribute("success", "Produto editado com sucesso!");
        return "redirect:/produtos/cadastrar";
    }

    @GetMapping(value = "/exportPdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> exportPdf(ModelMap model) throws IOException, BadElementException {
        model.addAttribute("produtos", produtosService.findAll());
        ByteArrayInputStream bis = Produtos_ExportPdf.produtosExportPdf(produtosService.findAll());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=MPE Tech-produtos.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping("/exportExel")
    public void exportExel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=MPE Tech-produtos.xlsx");
        ByteArrayInputStream stream = Produtos_ExportExel.produtosExportExel(produtosService.findAll());
        IOUtils.copy(stream, response.getOutputStream());
    }

    @GetMapping("/status/{id}")
    public String preAtualizaStatus(@PathVariable("id") Long id, ModelMap model) {
        return atualizaStatus(id, model);
    }

    @PutMapping("/atualizar")
    public String atualizaStatus(@PathVariable("id") Long id, ModelMap model) {
        Produtos produto = produtosService.findById(id).get();
        model.addAttribute("produtos", produto);
        if (produto.getEnumStatusProduto() == EnumStatusProduto.ATIVO) {
            produto.setEnumStatusProduto(EnumStatusProduto.INATIVO);
        } else {
            produto.setEnumStatusProduto(EnumStatusProduto.ATIVO);
        }
        produtosService.save(produto);
        return "redirect:/produtos/listar";
    }

}
