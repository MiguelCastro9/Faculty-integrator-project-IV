function gerarFreteCarrinho() {

    if (document.getElementById('freteCarrinho').value.length === 9) {

        var freteAleatorio1 = Math.floor(Math.random() * (80 - 60) + 60);
        document.getElementById('freteCarrinho2').value = ('2 dias úteis R$' + freteAleatorio1);

        var freteAleatorio2 = Math.floor(Math.random() * (50 - 25) + 25);
        document.getElementById('freteCarrinho5').value = ('5 dias úteis R$' + freteAleatorio2);

        var freteAleatorio3 = Math.floor(Math.random() * (20 - 5) + 5);
        document.getElementById('freteCarrinho7').value = ('7 dias úteis R$' + freteAleatorio3);
        if (freteAleatorio3 === 0) {
            document.getElementById('freteCarrinho7').value = ('Frete Grátis');
        } else {
            document.getElementById('freteCarrinho7').value = ('7 dias úteis R$' + freteAleatorio3);
        }

    }
}



