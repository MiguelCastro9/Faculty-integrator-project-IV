function limpa_formulário_cep_endereco() {
    document.getElementById('logradouroCliente').value = ("");
    document.getElementById('localidadeCliente').value = ("");
    document.getElementById('ufCliente').value = ("");
    document.getElementById('cepCliente').value = ("");
}

function meu_callback_endereco(conteudo) {
    if (!("erro" in conteudo)) {
        document.getElementById('logradouroCliente').value = (conteudo.logradouro);
        document.getElementById('localidadeCliente').value = (conteudo.localidade);
        document.getElementById('ufCliente').value = (conteudo.uf);
        document.getElementById('cepCliente').value = (conteudo.cep);
    } else {
        limpa_formulário_cep_endereco();
        alert("CEP não encontrado.");
    }
}

function pesquisacep_endereco(valor) {

    var cep = valor.replace(/\D/g, '');

    if (cep != "") {

        var validacep = /^[0-9]{8}$/;

        if (validacep.test(cep)) {

            document.getElementById('logradouroCliente').value = "...";
            document.getElementById('localidadeCliente').value = "...";
            document.getElementById('ufCliente').value = "...";
            document.getElementById('cepCliente').value = "...";

            var script = document.createElement('script');

            script.src = 'https://viacep.com.br/ws/' + cep + '/json/?callback=meu_callback_endereco';

            document.body.appendChild(script);

        } else {
            limpa_formulário_cep_endereco();
            let element = document.getElementById('alert-viaCep');
            element.removeAttribute('hidden');
            element.innerHTML += '<label class="validation-message">CEP inválido.</label>';


        }
    } else {
        limpa_formulário_cep_endereco();
    }
}
;

function limpa_formulário_cep_endereco_novo() {
    document.getElementById('logradouro').value = ("");
    document.getElementById('localidade').value = ("");
    document.getElementById('uf').value = ("");
    document.getElementById('cep').value = ("");
}

function meu_callback_endereco_novo(conteudo) {
    if (!("erro" in conteudo)) {
        document.getElementById('logradouro').value = (conteudo.logradouro);
        document.getElementById('localidade').value = (conteudo.localidade);
        document.getElementById('uf').value = (conteudo.uf);
        document.getElementById('cep').value = (conteudo.cep);
    } else {
        limpa_formulário_cep_endereco_novo();
        alert("CEP não encontrado.");
    }
}

function pesquisacep_endereco_novo(valor) {

    var cep = valor.replace(/\D/g, '');

    if (cep != "") {

        var validacep = /^[0-9]{8}$/;

        if (validacep.test(cep)) {

            document.getElementById('logradouro').value = "...";
            document.getElementById('localidade').value = "...";
            document.getElementById('uf').value = "...";
            document.getElementById('cep').value = "...";

            var script = document.createElement('script');

            script.src = 'https://viacep.com.br/ws/' + cep + '/json/?callback=meu_callback_endereco_novo';

            document.body.appendChild(script);

        } else {
            limpa_formulário_cep_endereco_novo();
            let element = document.getElementById('alert-viaCep');
            element.removeAttribute('hidden');
            element.innerHTML += '<label class="validation-message">CEP inválido.</label>';


        }
    } else {
        limpa_formulário_cep_endereco_novo();
    }
}
;

function limpa_formulário_cep_faturamento() {
    document.getElementById('logradouroFaturamento').value = ("");
    document.getElementById('localidadeFaturamento').value = ("");
    document.getElementById('ufFaturamento').value = ("");
    document.getElementById('cepFaturamento').value = ("");
}

function meu_callback_faturamento(conteudo) {
    if (!("erro" in conteudo)) {
        document.getElementById('logradouroFaturamento').value = (conteudo.logradouro);
        document.getElementById('localidadeFaturamento').value = (conteudo.localidade);
        document.getElementById('ufFaturamento').value = (conteudo.uf);
        document.getElementById('cepFaturamento').value = (conteudo.cep);
    } else {
        limpa_formulário_cep_faturamento();
        alert("CEP não encontrado.");
    }
}

function pesquisacep_faturamento(valor) {

    var cep = valor.replace(/\D/g, '');

    if (cep != "") {

        var validacep = /^[0-9]{8}$/;

        if (validacep.test(cep)) {

            document.getElementById('logradouroFaturamento').value = "...";
            document.getElementById('localidadeFaturamento').value = "...";
            document.getElementById('ufFaturamento').value = "...";
            document.getElementById('cepFaturamento').value = "...";

            var script = document.createElement('script');

            script.src = 'https://viacep.com.br/ws/' + cep + '/json/?callback=meu_callback_faturamento';

            document.body.appendChild(script);

        } else {
            limpa_formulário_cep_faturamento();
            let element = document.getElementById('alert-viaCep');
            element.removeAttribute('hidden');
            element.innerHTML += '<label class="validation-message">CEP inválido.</label>';


        }
    } else {
        limpa_formulário_cep_faturamento();
    }
}
;


