$('input[name="formaPagamento"]').on('change', function () {
    if ($('input[name="formaPagamento"]:checked').val() === "credito") {
        $('.cartaoGenerico').show();
        $('.cartaoCredito').show();
    } else if ($('input[name="formaPagamento"]:checked').val() === "debito") {
        $('.cartaoCredito').hide();
        $('.cartaoGenerico').show();
    } else if ($('input[name="formaPagamento"]:checked').val() === "boleto") {
        $('.cartaoGenerico').hide();
        $('.cartaoCredito').hide();
    }
});

