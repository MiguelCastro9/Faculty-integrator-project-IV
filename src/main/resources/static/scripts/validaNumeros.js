function somenteNumeros_endereco(e) {
    var charCode = e.charCode ? e.charCode : e.keyCode;
    if (charCode != 8 && charCode != 9) {
        var max = 4;
        var num = document.getElementById('numeroCliente');

        if ((charCode < 48 || charCode > 57) || (num.value.length >= max)) {
            return false;
        }
    }
}

function somenteNumeros_endereco_novo(e) {
    var charCode = e.charCode ? e.charCode : e.keyCode;
    if (charCode != 8 && charCode != 9) {
        var max = 4;
        var num = document.getElementById('numero');

        if ((charCode < 48 || charCode > 57) || (num.value.length >= max)) {
            return false;
        }
    }
}

function somenteNumeros_faturamento(e) {
    var charCode = e.charCode ? e.charCode : e.keyCode;
    if (charCode != 8 && charCode != 9) {
        var max = 4;
        var num = document.getElementById('numeroFaturamento');

        if ((charCode < 48 || charCode > 57) || (num.value.length >= max)) {
            return false;
        }
    }
}

function somenteNumeros_quantidade_produto(e) {
    var charCode = e.charCode ? e.charCode : e.keyCode;
    if (charCode != 8 && charCode != 9) {
        var max = 4;
        var num = document.getElementById('quantidadeProduto');

        if ((charCode < 48 || charCode > 57) || (num.value.length >= max)) {
            return false;
        }
    }
}

function somenteNumeros_cartao_cliente(e) {
    var charCode = e.charCode ? e.charCode : e.keyCode;
    if (charCode != 8 && charCode != 9) {
        var max = 16;
        var num = document.getElementById('numeroCartao');

        if ((charCode < 48 || charCode > 57) || (num.value.length >= max)) {
            return false;
        }
    }
}

function somenteNumeros_cartao_codigo(e) {
    var charCode = e.charCode ? e.charCode : e.keyCode;
    if (charCode != 8 && charCode != 9) {
        var max = 3;
        var num = document.getElementById('codigoCartao');

        if ((charCode < 48 || charCode > 57) || (num.value.length >= max)) {
            return false;
        }
    }
}

