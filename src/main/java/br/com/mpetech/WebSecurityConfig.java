package br.com.mpetech;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
        http.authorizeRequests().antMatchers("/acesso/cadastrar", "/acesso/salvar", "/home/detalhes/{id}", "/home/produtos", "/home/exibeImagem/{imagem}", 
                "/css/**", "/img/**", "/imagem_produtos/**", "/imagem_perfil/**", "/scripts/**").permitAll()
                .antMatchers("/produtos/listar").hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/produtos/cadastrar").hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/produtos/buscar").hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/produtos/detalhes/{id}").hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/produtos/salvar").hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/produtos/editar/**").hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/produtos/exportPdf").hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/produtos/exportExel").hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/produtos/status/{id}").hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/produtos/atualizar").hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/usuarios/listar").hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/usuarios/cadastrar").hasAnyRole("ADMIN")
                .antMatchers("/usuarios/salvar").hasAnyRole("ADMIN")
                .antMatchers("/usuarios/editar/**").hasAnyRole("ADMIN")
                .antMatchers("/usuarios/buscar").hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/clientes/perfil/{email}").hasAnyRole("USER")
                .antMatchers("/clientes/editar/**").hasAnyRole("USER")
                .antMatchers("/clientes/novo_endereco").hasAnyRole("USER")
                .antMatchers("/clientes/salvar_endereco").hasAnyRole("USER")
                .antMatchers("/clientes/meus_enderecos/{id}").hasAnyRole("USER")
                .antMatchers("/clientes/editar_endereco/**").hasAnyRole("USER")
                .antMatchers("/clientes/excluir_endereco/{id}").hasAnyRole("USER")
                .antMatchers("/carrinho/exibir").not().hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/carrinho/update").not().hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/carrinho/add/{id}").not().hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/carrinho/remove/{id}").not().hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/carrinho/remove-item/{id}").not().hasAnyRole("ADMIN", "STOCK")
                .antMatchers("/checkout/pagamento/{id}").hasAnyRole("USER")
                .antMatchers("/checkout/compra_finalizada").hasAnyRole("USER")
                .antMatchers("/checkout/compra").hasAnyRole("USER")
                .antMatchers("/clientes/historico_compras").hasAnyRole("USER", "STOCK")
                .antMatchers("/clientes/exportPdf/{id}").hasAnyRole("USER")
                .antMatchers("/estoque/pedidos").hasAnyRole("STOCK")
                .antMatchers("/estoque/pedidos/buscar").hasAnyRole("STOCK")
                .antMatchers("/estoque/editar").hasAnyRole("STOCK")
                .antMatchers("/estoque/editar/{id}").hasAnyRole("STOCK")
                .antMatchers("/estoque/produto/editado").hasAnyRole("STOCK")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/acesso/login").defaultSuccessUrl("/home/dashboard", true).permitAll()
                .and()
                .logout().permitAll();
    }

    @Autowired
    public void ConfigurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception {

        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passEncoder)
                .usersByUsernameQuery("SELECT email, senha, status FROM (SELECT email, senha, status FROM usuarios WHERE NOT status = 2 UNION SELECT email, senha, status FROM clientes WHERE NOT status = 2) tabela_union WHERE tabela_union.email = ?")
                .authoritiesByUsernameQuery("SELECT email, roles FROM (SELECT email, roles FROM usuarios UNION SELECT email, roles FROM clientes) tabela_union WHERE tabela_union.email = ?");

    }
}
