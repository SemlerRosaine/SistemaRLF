/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarlf.visualizacao.cliente;

import componente.text.RequiredTextField;
import componente.text.formated.TxtCPFCNPJDinamico;
import componente.text.formated.TxtTelefoneDinamico;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import sistemarlf.modelo.entidades.cliente.Cliente;
import sistemarlf.modelo.oad.cliente.OADCliente;

/**
 * Tela de cadastramento de clientes.
 *
 * @author Rosaine e Jhonnatan
 */
public class FormCadCliente extends javax.swing.JPanel {

    /**
     * Atributos com nomes representativos.
     */
    private JInternalFrame telaCarregada;
    private Object telaChamadora;
    private Boolean closeOnSave = false;
    private Boolean addNewRecord = false;
    private Cliente cliente;

    /**
     * Método construtor da classe.
     *
     * @param cliente Objeto do tipo <b>Cliente</b> que será editado nesta tela.
     */
    public FormCadCliente(Cliente cliente) {
        super(); //Chamada do método construtor da classe pai.

        initComponents(); //Chamada do método de inicialização dos objetos componentes da interface.

        this.cliente = cliente; //Valorização da variável global da classe, repassando a referência do objeto passado por parâmetro.

        this.txtNomeCliente.requestFocus(); // Foco requisitado para o primeiro componente do formulário por questão de usabilidade.

        /*
         * Adicionado "listener" para verificar na base de dados a existência de 
         * cliente com o CPF/CNPJ informado no campo txtCpfCnpjCliente, possibilitando, 
         * em caso de existência, a edição dos dados deste por parte do usuário.
         */
        this.txtCpfCnpjCliente.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fe) {
                super.focusLost(fe);

                /*
                 * Busca de cliente pelo CPF/CNPJ informado no campo txtCpfCnpjCliente.
                 */
                Cliente clienteBancoDados = new OADCliente().buscaClientePorCPF(txtCpfCnpjCliente.getText());

                /*
                 * Verificando resultado da busca do cliente e questionando o 
                 * usuário sobre a inclusão ou atualização do cliente.
                 * 
                 * A variável addNewRecord serve como "flag" para evitar novos 
                 * questionamentos ao usuário sobre a inclusão ou atualização do 
                 * cliente toda vez que o foco passar pelo campo txtCpfCnpjCliente 
                 * durante a edição do cliente atual.
                 * 
                 * ps.: Quando a tela tem os campos reiniciados e é iniciada 
                 * a inserção de um novo cliente a "flag" addNewRecord é 
                 * reiniciada, permitindo novamente ao sistema questionar o 
                 * usuário caso encontre um cliente com o CPF/CNPJ informado.
                 */
                if (clienteBancoDados instanceof Cliente && !addNewRecord) {
                    if (JOptionPane.showConfirmDialog(telaCarregada, "Já foi localizado um cliente cadastrado com este CPF/CNPJ, deseja incluir um novo registro?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                        if (JOptionPane.showConfirmDialog(telaCarregada, "Deseja atualizar os dados do cliente encontrado na base de dados?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            setCliente(clienteBancoDados);
                            carregarAtributosNosCampos();
                        } else {
                            habilitaFormularioCadastro(true);
                            limparCampos();
                            addNewRecord = false;
                        }
                    } else {
                        addNewRecord = true;
                    }
                }
            }
        });

        this.carregarAtributosNosCampos();
    }

    /**
     * Método construtor da classe.
     *
     * @param jfCarregada JInternalFrame onde este formulário está contido.
     * @param cliente Objeto do tipo <b>Cliente</b> que será editado nesta tela.
     */
    public FormCadCliente(JInternalFrame jfCarregada, Cliente cliente) {
        this(cliente); // Chamada do método construtor "FormCadCliente(Cliente cliente)".
        /*
         * Valorização da variável global contendo a referência do objeto do 
         * JInternalFrame onde este formulário está contido.
         */
        this.telaCarregada = jfCarregada;
    }

    /**
     * Método construtor da classe.
     *
     * @param jfCarregada JInternalFrame onde este formulário está contido.
     * @param jfChamadora JInternalFrame de onde foi disparada a chamada do
     * método de abertura do JInternalFrame contendo este formulário.
     * @param cliente Objeto do tipo <b>Cliente</b> que será editado nesta tela.
     */
    public FormCadCliente(JInternalFrame jfCarregada, Object jfChamadora, Cliente cliente) {
        this(jfCarregada, cliente); // Chamada do método construtor "FormCadCliente(JInternalFrame jfCarregada, Cliente cliente)".
        /*
         * Valorização da variável global contendo a referência do objeto do 
         * JInternalFrame de onde foi disparada a chamada do método de abertura 
         * da JInternalFrame que contém este formulário.
         */
        telaChamadora = jfChamadora;
    }

    /**
     * Método construtor da classe.
     *
     * @param jfCarregada JInternalFrame onde este formulário está contido.
     * @param jfChamadora JInternalFrame de onde foi disparada a chamada do
     * método de abertura do JInternalFrame contendo este formulário.
     * @param cliente Objeto do tipo <b>Cliente<b> que será editado nesta tela.
     * @param closeOnSave Parâmetro indicando se o JInternalFrame que contém
     * este formulário deve ser fechado após o clique do botão "Salvar".
     */
    public FormCadCliente(JInternalFrame jfCarregada, Object jfChamadora, Cliente cliente, Boolean closeOnSave) {
        this(jfCarregada, jfChamadora, cliente);// Chamada do método construtor "FormCadCliente(JInternalFrame jfCarregada, Object jfChamadora, Cliente cliente)".
        /**
         * Valorização da variável global que indica a ação de fechamento do
         * JInternalFrame que contém este formulário após o clique no botão
         * "Salvar".
         */
        this.closeOnSave = closeOnSave;
    }

    /**
     * Método privado utilizado internamente nesta classe para executar a
     * reinicialização dos valores dos campos contidos neste formulário para
     * seus respectivos valores padrão.
     */
    private void carregarAtributosNosCampos() {
        /**
         * <code>this.cliente.getNomeRazaoSocial() == null ? "" : this.cliente.getNomeRazaoSocial()</code>
         * Trata-se de um comando "if ternário" indicando que caso
         * this.cliente.getNomeRazaoSocial() seja igual a "null", o respectivo
         * campo txtNomeCliente receberá por valor uma String vazia "", caso
         * contrário receberá o valor do atributo "nomeRazaoSocial".
         */
        this.txtNomeCliente.setText(this.cliente.getNomeRazaoSocial() == null ? "" : this.cliente.getNomeRazaoSocial());
        this.txtEnderecoCliente.setText(this.cliente.getEndereco() == null ? "" : this.cliente.getEndereco());
        this.txtCpfCnpjCliente.setText(this.cliente.getCpfCnpj() == null ? "" : this.cliente.getCpfCnpj().replaceAll("_", "").replaceAll("-", "").replaceAll("/", ""));
        this.txtCodCliente.setText(this.cliente.getCodigo() == null ? "" : this.cliente.getCodigo().toString());
        this.txtNomeContato.setText(this.cliente.getNomeContato() == null ? "" : this.cliente.getNomeContato());
        this.txtTelefoneCliente.setText(this.cliente.getTelefone() == null ? "" : this.cliente.getTelefone().replaceAll("_", ""));
        this.txtCelularCliente.setText(this.cliente.getTelefoneCelular() == null ? "" : this.cliente.getTelefoneCelular().replaceAll("_", ""));
        this.txtTelefoneAlternativoCliente.setText(this.cliente.getTelefoneAlternativo() == null ? "" : this.cliente.getTelefoneAlternativo().replaceAll("_", ""));
        this.txtEmailCliente.setText(this.cliente.getEmail() == null ? "" : this.cliente.getEmail());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        txtCodCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEnderecoCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEmailCliente = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNomeContato = new javax.swing.JTextField();
        txtCpfCnpjCliente = new TxtCPFCNPJDinamico(false);
        txtTelefoneAlternativoCliente = new TxtTelefoneDinamico();
        txtCelularCliente = new TxtTelefoneDinamico();
        txtTelefoneCliente = new TxtTelefoneDinamico();
        txtNomeCliente = new RequiredTextField();
        btnFechar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setAutoscrolls(true);
        setMaximumSize(new java.awt.Dimension(0, 0));
        setMinimumSize(new java.awt.Dimension(700, 300));
        setName("ProtCadastraCliente"); // NOI18N
        setPreferredSize(new java.awt.Dimension(700, 300));
        setVerifyInputWhenFocusTarget(false);

        jLabel3.setText("Código:");

        txtCodCliente.setEditable(false);
        txtCodCliente.setFocusable(false);

        jLabel1.setText("Nome:");

        jLabel8.setText("CPF/CNPJ:");

        jLabel2.setText("Endereço:");

        jLabel4.setText("Telefone:");

        jLabel5.setText("Celular:");

        jLabel6.setText("Telefone Alternativo:");

        jLabel7.setText("E-mail:");

        jLabel9.setText("Nome Contato:");

        btnFechar.setText("Fechar");
        btnFechar.setFocusable(false);
        btnFechar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFechar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.setFocusable(false);
        btnSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnFechar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalvar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator1)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtCodCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTelefoneAlternativoCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCelularCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTelefoneCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNomeContato, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                                .addComponent(txtEnderecoCliente, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCpfCnpjCliente, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEmailCliente, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNomeCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(10, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtCpfCnpjCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEnderecoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNomeContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTelefoneCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTelefoneAlternativoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtEmailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFechar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método disparado quando executado o clique no botão "Fechar".
     *
     * @param evt Evento de clique no botão.
     */
    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        /*
         * Quando disparado o clique no botão "Fechar" a tela que contém 
         * este formulário é fechada, através da chamada do método "dispose()".
         */
        telaCarregada.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    /**
     * Método disparado quando executado o clique no botão "Salvar".
     *
     * @param evt Evento de clique no botão.
     */
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed

//        Cliente cliente = new Cliente(); //Não há necessidade de criação de um novo objeto, tendo em vista que temos um objeto global da classe Cliente para este fim.

        /*
         * Podemos substituir o trecho de código comentado utilizando-se do 
         * retorno da expressão "(txtCpfCnpjCliente.getText().length() > 11)" 
         * sendo que o retorno é "true" caso "(txtCpfCnpjCliente.getText().length() > 11)" 
         * e "false" caso contrário.
         */
//        if (txtCpfCnpjCliente.getText().length() > 11) {
//            clienteEhPJ = true;
//        } else {
//            clienteEhPJ = false;
//        }

        /*
         * Este trecho poderia ser substituído por:
         * cliente.setCodigo(!(txtCodCliente.getText().isEmpty())?Integer.valueOf(txtCodCliente.getText()):null);
         */
        if (!txtCodCliente.getText().isEmpty()) {
            cliente.setCodigo(Integer.valueOf(txtCodCliente.getText()));
        }

        cliente.setNomeRazaoSocial(txtNomeCliente.getText());
        cliente.setCpfCnpj(txtCpfCnpjCliente.getText());
        cliente.setEhPJ((txtCpfCnpjCliente.getText().length() > 11));
        cliente.setNomeContato(txtNomeContato.getText());
        cliente.setTelefone(txtTelefoneCliente.getText());
        cliente.setTelefoneAlternativo(txtTelefoneAlternativoCliente.getText());
        cliente.setTelefoneCelular(txtCelularCliente.getText());
        cliente.setEmail(txtEmailCliente.getText());
        cliente.setEndereco(txtEnderecoCliente.getText()); // Corrigido de txtEndereçoCliente para txtEnderecoCliente [convenção Java de nomenclatura de variáveis].

        /*
         * Verificando se os dados preenchidos no formulário são válidos e se os
         * campos obrigatórios estão preenchidos.
         */
        if (validaFormulario()) {
            /*
             * Incluindo ou atualizando "cliente" na base de dados.
             */
            if ((new OADCliente().salvar(cliente))) {
                JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso.");
                /*
                 * Habilitando campos do formulário de cadastro possibilitando o 
                 * cadastramento/atualização imediato(a) de um novo cliente.
                 */
                this.habilitaFormularioCadastro(true);
                /*
                 * Chamada de método responsável pela reversão dos valores dos 
                 * campos do formulário para seus respectivos valores padrão.
                 */
                this.limparCampos();
                /*
                 * Reinicialização da "flag" responsável pelo controle das 
                 * mensagens de confirmação, quando da existência de um cliente 
                 * com o CPF/CNPJ informado.
                 */
                this.addNewRecord = false;
                /*
                 * Caso a ordem seja para fechar a tela que contém este 
                 * formulário após o salvamento, isto será executado através da 
                 * chamada do método "dispose()".
                 */
                if (this.closeOnSave) {
                    this.telaCarregada.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar o cliente, por favor, reinicie o processo de cadastramento.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            /*
             * Movido para local mais apropriado, tendo em vista que em caso de 
             * erro de salvamento os dados do formulário seriam revertidos para 
             * o valor padrão, perdendo-se as informações providas pelo cliente.
             */
//            this.habilitaFormularioCadastro(true);
//            this.limparCampos();
//            this.addNewRecord = false;
        } else {
            JOptionPane.showMessageDialog(this, "Há campos necessários não preenchidos, por favor, verifique.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnSalvarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btnFechar;
    javax.swing.JButton btnSalvar;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel5;
    javax.swing.JLabel jLabel6;
    javax.swing.JLabel jLabel7;
    javax.swing.JLabel jLabel8;
    javax.swing.JLabel jLabel9;
    javax.swing.JSeparator jSeparator1;
    javax.swing.JFormattedTextField txtCelularCliente;
    javax.swing.JTextField txtCodCliente;
    javax.swing.JFormattedTextField txtCpfCnpjCliente;
    javax.swing.JTextField txtEmailCliente;
    javax.swing.JTextField txtEnderecoCliente;
    componente.text.RequiredTextField txtNomeCliente;
    javax.swing.JTextField txtNomeContato;
    javax.swing.JFormattedTextField txtTelefoneAlternativoCliente;
    javax.swing.JFormattedTextField txtTelefoneCliente;
    // End of variables declaration//GEN-END:variables

    /**
     * Método responsável pela reversão do valor dos campos do formulário para
     * seus respectivos valores padrão.
     */
    private void limparCampos() {
        this.txtNomeCliente.setText(null);
        this.txtEnderecoCliente.setText(null);
        this.txtCpfCnpjCliente.setText(null);
        this.txtCodCliente.setText(null);
        this.txtNomeContato.setText(null);
        this.txtTelefoneCliente.setText(null);
        this.txtCelularCliente.setText(null);
        this.txtTelefoneAlternativoCliente.setText(null);
        this.txtEmailCliente.setText(null);

        /*
         * Por melhoria na usabilidade, toda a vez que os campos tem seus 
         * valores revertidos para o padrão o foco é movido para o primeiro 
         * campo do formulário.
         */
        this.txtNomeCliente.requestFocus();
    }

    /**
     * Método responsável por habilitar/desabilitar os campos editáveis do
     * formulário.
     *
     * @param valor "true" para habilitar, "false" para desabilitar.
     */
    private void habilitaFormularioCadastro(Boolean valor) {
        this.txtNomeCliente.setEnabled(valor);
        this.txtCpfCnpjCliente.setEnabled(valor);
        this.txtEmailCliente.setEnabled(valor);
        this.txtEnderecoCliente.setEnabled(valor);
        this.txtTelefoneAlternativoCliente.setEnabled(valor);
        this.txtTelefoneCliente.setEnabled(valor);
        this.txtCelularCliente.setEnabled(valor);
        this.txtNomeContato.setEnabled(valor);
        this.btnSalvar.setEnabled(valor);
    }

    /**
     * Método responsável pela validação dos dados inseridos no formulário.
     *
     * @return "true" caso o formulário possua dados consistentes, válidos e
     * esteja preenchido a contento, "false" caso contrário.
     */
    private Boolean validaFormulario() {
        Boolean retorno = true;

        if (!this.txtNomeCliente.runValidation()) {
            retorno = false;
        }

        return retorno;
    }

    /*
     * Métodos de encapsulamento das variáveis globais.
     */
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
