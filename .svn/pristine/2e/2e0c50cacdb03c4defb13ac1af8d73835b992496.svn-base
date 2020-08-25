/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarlf.modelo.entidades.ordem;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import sistemarlf.modelo.entidades.Registro;
import sistemarlf.modelo.entidades.item.Item;

/**
 *
 * @author Jhonnatan
 */
@Entity
public class OrdemProducao extends Registro {
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;
    @Column
    private Character situacao;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFechamento;
    @OneToMany 
    @JoinTable(name = "item_ordemproducao", 
            joinColumns = { @JoinColumn(name = "item") }, 
            inverseJoinColumns = { @JoinColumn(name = "ordem_producao") })
    private List<Item> itens;

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Character getSituacao() {
        return situacao;
    }

    public void setSituacao(Character situacao) {
        this.situacao = situacao;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public List<Item> getItens() {
        return itens;
    }
    
    public Integer getQtdeGeralItens() {
        Integer qtde = 0;
        for (int i=0; i<getItens().size();i++){
            qtde = qtde + getItens().get(i).getQuantidade();
        }
        return qtde;
    }
    public void setItens(List<Item> itens) {
        this.itens = itens;
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
        Object linha[] = {getCodigo(), getItens().get(0).getProduto().getCodigoReferencia(), getItens().get(0).getProduto().getDescricao(), getQtdeGeralItens(), getDescSituacao(getSituacao()), getDataInclusao()};
        return linha;
    }    
    
}
