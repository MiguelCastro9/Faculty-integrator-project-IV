function copiaValor() {

    let logradouroCliente = document.getElementById("logradouroCliente").value;
    document.getElementById("logradouroFaturamento").value = logradouroCliente;

    let numeroCliente = document.getElementById("numeroCliente").value;
    document.getElementById("numeroFaturamento").value = numeroCliente;

    let localidadeCliente = document.getElementById("localidadeCliente").value;
    document.getElementById("localidadeFaturamento").value = localidadeCliente;

    let ufCliente = document.getElementById("ufCliente").value;
    document.getElementById("ufFaturamento").value = ufCliente;

    let complementoCliente = document.getElementById("complementoCliente").value;
    document.getElementById("complementoFaturamento").value = complementoCliente;

    let cepCliente = document.getElementById("cepCliente").value;
    document.getElementById("cepFaturamento").value = cepCliente;
}

