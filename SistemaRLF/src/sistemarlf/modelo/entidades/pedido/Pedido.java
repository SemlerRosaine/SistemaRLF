/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarlf.modelo.entidades.pedido;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import sistemarlf.modelo.entidades.Registro;
import sistemarlf.modelo.entidades.cliente.Cliente;
import sistemarlf.modelo.entidades.item.Item;
import sistemarlf.modelo.oad.pedido.OADPedido;
import uteis.NumberFieldVerifier;

/**
 *
 * @author Jhonnatan
 */
@Entity
public class Pedido extends Registro {
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFechamento;
    @Column
    private Character situacao;
    @ManyToOne
    @JoinTable(name = "pedido_cliente", 
            joinColumns = { @JoinColumn(name = "pedido") }, 
            inverseJoinColumns = { @JoinColumn(name = "cliente") })
    private Cliente cliente;

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Character getSituacao() {
        return situacao;
    }

    public void setSituacao(Character situacao) {
        this.situacao = situacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private Double getValorTotalPedido(){
        Double ret = 0.0;
        List<Item> li = new OADPedido().getItensPedido(this);
        for (int i = 0; i < li.size(); i++){
            ret = ret + li.get(i).getValorTotalProd();
        }
        return ret;
    }
    public String getValorTotalPedidoFormatado() {
        return NumberFieldVerifier.SetValorFormatado(getValorTotalPedido());
    }    
//    1. Em Cadastramento
//    2. Em produção
//    3. Atendida
//    4. Cancelada
    public String getDescSituacao(char sit) {
        String ret;
        switch (sit) {
                case '1':
                    ret = "Em Cadastramento";
                    break;
                case '2':
                    ret = "Em Produção";
                    break;
                case '3':
                    ret = "Atendido";
                    break;                    
                case '4':
                    ret = "Cancelado";
                    break;         
                default:
                    ret = "ERRO: Não é uma situção válida!";
        }
         return ret;
    }        
    
    public Object[] getLinha() {
        Object linha[] = {getCodigo(), getDataInclusao(), getDescSituacao(getSituacao()), getDataFechamento(), getValorTotalPedidoFormatado()};
        
        return linha;
    }
}
