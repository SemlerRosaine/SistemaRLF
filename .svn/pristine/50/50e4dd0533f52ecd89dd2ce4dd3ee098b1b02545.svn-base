/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarlf.modelo.entidades.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import sistemarlf.modelo.entidades.Registro;
import sistemarlf.modelo.entidades.caracteristica.Caracteristica;
import sistemarlf.modelo.entidades.pedido.Pedido;
import sistemarlf.modelo.entidades.produto.Produto;
import uteis.NumberFieldVerifier;

/**
 *
 * @author Jhonnatan
 */
@Entity
public class Item extends Registro {
    @ManyToOne
    private Pedido pedido;
    @Column
    private Integer quantidade;
    @Column
    private Double valor;
    @Column
    private Double valorTotalProd;
    @Column
    private Character situacao;
    @OneToOne
    private Produto produto;
    @OneToOne
    private Caracteristica tamanho;
    @OneToOne
    private Caracteristica cor;
    

    public Caracteristica getTamanho() {
        return tamanho;
    }

    public void setTamanho(Caracteristica tamanho) {
        this.tamanho = tamanho;
    }

    public Caracteristica getCor() {
        return cor;
    }

    public void setCor(Caracteristica cor) {
        this.cor = cor;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        setValorTotalProd();
    }

    public Double getValor() {
        return valor;
    }
    public String getValorFormatado() {
        return NumberFieldVerifier.SetValorFormatado(getValor());
    }
    public void setValor(Double valor) {
        this.valor = valor;
        setValorTotalProd();
    }

    public Character getSituacao() {
        return situacao;
    }

    public void setSituacao(Character situacao) {
        this.situacao = situacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getValorTotalProd() {
        return valorTotalProd;
    }
    public String getValorTotalProdFormatado() {
        return NumberFieldVerifier.SetValorFormatado(getValorTotalProd());
    }
    private void setValorTotalProd() {
        if ((valor != null && valor > 0) && (quantidade != null && quantidade > 0)){
            this.valorTotalProd = valor * quantidade;
        }
    }    
//    1. Em Cadastramento
//    2. Em produção
//    3. Atendida
//    4. Cancelada
    public String getDescSituacao(char sit) {
        String ret = "";
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
        Object linha[] = {getProduto().getCodigoReferencia(), getProduto().getDescricao(), getDescSituacao(getSituacao()), getTamanho().getNome(), getCor().getNome(), getQuantidade(), getValorFormatado(), getValorTotalProdFormatado()};
        
        return linha;
    }

    public Object[] getLinhaOrdem() {
        Object linha[] = {getPedido().getCodigo(), getPedido().getCliente().getNomeRazaoSocial(), getQuantidade(), getTamanho().getNome(), getCor().getNome(), getValorFormatado(), getValorTotalProdFormatado()};
        
        return linha;
    }

}
