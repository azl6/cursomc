package com.nelioalves.curso.services;

import com.nelioalves.curso.domain.*;
import com.nelioalves.curso.enums.EstadoPagamento;
import com.nelioalves.curso.enums.TipoCliente;
import com.nelioalves.curso.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    //REPOSITORIES responsáveis pela manipulação dos dados
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    CidadeRepository cidadeRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    PagamentoRepository pagamentoRepository;
    @Autowired
    ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private BCryptPasswordEncoder pe;

    public void instantiateTestDatabase() throws ParseException {
        Categoria cat1 = new Categoria("Informática");
        Categoria cat2 = new Categoria("Escritório");
        Categoria cat3 = new Categoria("Cama, mesa e banho");
        Categoria cat4 = new Categoria("Eletrônicos");
        Categoria cat5 = new Categoria("Jardinagem");
        Categoria cat6 = new Categoria("Decoração");
        Categoria cat7 = new Categoria("Perfumaria");

        Produto p1 = new Produto("Computador", 2000d);
        Produto p2 = new Produto("Impressora", 800d);
        Produto p3 = new Produto("Mouse", 80d);
        Produto p4 = new Produto("Mesa de escritório", 300.00);
        Produto p5 = new Produto("Toalha", 50.00);
        Produto p6 = new Produto("Colcha", 200.00);
        Produto p7 = new Produto("TV true color", 1200.00);
        Produto p8 = new Produto("Roçadeira", 800.00);
        Produto p9 = new Produto("Abajour", 100.00);
        Produto p10 = new Produto("Pendente", 180.00);
        Produto p11 = new Produto("Shampoo", 90.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9, p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));

        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));
        p7.getCategorias().addAll(Arrays.asList(cat4));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));
        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));

        produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
        categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));

        Estado est1 = new Estado("Minas Gerais");
        Estado est2 = new Estado("São Paulo");

        Cidade cid1 = new Cidade("Uberlândia");
        Cidade cid2 = new Cidade("São Paulo");
        Cidade cid3 = new Cidade("Campinas");

        est1.getCidades().addAll(Arrays.asList(cid1));
        est2.getCidades().addAll(Arrays.asList(cid2, cid3));

        cid1.setEstado(est1);
        cid2.setEstado(est2);
        cid3.setEstado(est2);

        //salvar primeiro os estados, pois não se tem uma cidade sem estado
        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

        Cliente cli1 = new Cliente("Maria Silva", "alex.rodrigues23997@gmail.com","36378912377", TipoCliente.PESSOAFISICA, pe.encode("123"));
        cli1.getTelefone().addAll(Arrays.asList("27363323","93838393"));

        Endereco e1 = new Endereco("Rua Flores", "300", "Apto. 203", "Jardim", "38220834", cli1, cid1);
        Endereco e2 = new Endereco("Avenida Matos", "105", "Sala 600", "Centro", "38777012", cli1, cid2);

        cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1,e2));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm");
        Pedido ped1 = new Pedido(sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);
        Pagamento pagto2 = new PagamentoComBoleto(EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00 );

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}
