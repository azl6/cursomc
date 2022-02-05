package com.nelioalves.curso.services;

import com.nelioalves.curso.domain.Cliente;
import com.nelioalves.curso.domain.ItemPedido;
import com.nelioalves.curso.domain.PagamentoComBoleto;
import com.nelioalves.curso.domain.Pedido;
import com.nelioalves.curso.enums.EstadoPagamento;
import com.nelioalves.curso.exceptions.AuthorizationException;
import com.nelioalves.curso.repositories.ClienteRepository;
import com.nelioalves.curso.repositories.ItemPedidoRepository;
import com.nelioalves.curso.repositories.PagamentoRepository;
import com.nelioalves.curso.repositories.PedidoRepository;
import com.nelioalves.curso.security.UserSS;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private BoletoService boletoService;
    @Autowired
    ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EmailService emailService;

    public Pedido find(Integer id){
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(1, "Página não encontrada!"));
    }

    @Transactional
    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        emailService.sendOrderConfirmationHtmlEmail(obj);
        return obj;
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Cliente cliente =  clienteService.find(user.getId());
        return repo.findByCliente(cliente, pageRequest);
    }
}
