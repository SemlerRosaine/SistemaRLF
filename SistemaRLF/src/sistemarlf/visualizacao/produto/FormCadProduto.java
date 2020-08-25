/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarlf.visualizacao.produto;

import componente.text.RequiredListBox;
import componente.text.RequiredTextField;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import sistemarlf.modelo.entidades.caracteristica.Caracteristica;
import sistemarlf.modelo.entidades.produto.Produto;
import sistemarlf.modelo.oad.caracteristica.OADCaracteristica;
import sistemarlf.modelo.oad.produto.OADProduto;
import sistemarlf.visualizacao.pedido.FormCadPedido;
import uteis.NumberFieldVerifier;

/**
 *
 * @author Jhonnatan
 */
public class FormCadProduto extends javax.swing.JPanel {

    JInternalFrame telaCarregada;
    Object telaChamadora;
    Caracteristica Cor = new Caracteristica();
    Caracteristica Tamanho = new Caracteristica();
    DefaultListModel lmTamDispProdt = new DefaultListModel();
    DefaultListModel lmCoresDispProdt = new DefaultListModel();
    DefaultListModel lmTamSelecProdt = new DefaultListModel();
    DefaultListModel lmCoresSelecProdt = new DefaultListModel();
    List<Caracteristica> listTamDisp = new ArrayList<Caracteristica>();
    List<Caracteristica> listCorDisp = new ArrayList<Caracteristica>();
    List<Caracteristica> listTamSelec = new ArrayList<Caracteristica>();
    List<Caracteristica> listCorSelec = new ArrayList<Caracteristica>();
    NumberFieldVerifier verificador = new NumberFieldVerifier(true);
    Produto produtoCarregado;
    private Boolean closeOnSave = false;
    /**
     * Creates new form ProtCadastraCliente
     */
    public FormCadProduto(JInternalFrame jfCarregado) {
        telaCarregada = jfCarregado;
        inicializa();
        inicializaCaracteristicas();
    }

    public FormCadProduto(JInternalFrame jfCarregado, Object jfChamador, Produto p) {
        telaCarregada = jfCarregado;
        telaChamadora = jfChamador;
        inicializa();
        if (p == null) {
            inicializaCaracteristicas();
        } else {
            carregaCaracteristicasSelecionadas(p);
        }
    }
    public FormCadProduto(JInternalFrame jfCarregada, Object jfChamadora, Produto c, Boolean closeOnSave) {
        this(jfCarregada, jfChamadora, c);
        this.closeOnSave = closeOnSave;
    }

    private void inicializa() {
        initComponents();
        Tamanho.setCodigo(1);
        Cor.setCodigo(2);
        if (new OADCaracteristica().carregaTodos().isEmpty()) {
            Tamanho.setNome("Tamanho");
            new OADCaracteristica().salvar(Tamanho);
            Cor.setNome("Cor");
            new OADCaracteristica().salvar(Cor);
        } else {
            Cor = new OADCaracteristica().carrega(Cor);
            Tamanho = new OADCaracteristica().carrega(Tamanho);
        }


    }

    private void inicializaCaracteristicas() {
        produtoCarregado = null;
        listTamDisp = new OADCaracteristica().ListaPeloPai(1);
        listCorDisp = new OADCaracteristica().ListaPeloPai(2);
        listTamSelec.clear();
        listCorSelec.clear();
        lmTamSelecProdt.clear();
        lmCoresSelecProdt.clear();
        carregaCaracteristicas();
    }

    private void carregaCaracteristicas() {

        lmTamDispProdt.clear();
        for (int i = 0; i < listTamDisp.size(); i++) {
            lmTamDispProdt.addElement(listTamDisp.get(i).getNome());
        }

        lmCoresDispProdt.clear();
        for (int i = 0; i < listCorDisp.size(); i++) {
            lmCoresDispProdt.addElement(listCorDisp.get(i).getNome());
        }


    }

    private void carregaCaracteristicasSelecionadas(Produto p) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        produtoCarregado = p;
        if (p.getCodigoReferencia() != null) {
            txtCodProduto.setText(String.valueOf(p.getCodigoReferencia()));
        }
        txtDataInclProduto.setText(sdf.format(p.getDataInclusao()));
        txtDescProduto.setText(String.valueOf(p.getDescricao()));
        txtVlrCustoProduto.setText(NumberFieldVerifier.SetValorFormatado(p.getValorCusto()));
        txtVlrVendaProduto.setText(NumberFieldVerifier.SetValorFormatado(p.getValorVenda()));

        listTamDisp = new OADCaracteristica().ListaPeloPai(1);
        listCorDisp = new OADCaracteristica().ListaPeloPai(2);

        listTamSelec = p.getCaracteristicas(1);
        for (int i = 0; i < listTamSelec.size(); i++) {
            lmTamSelecProdt.addElement(listTamSelec.get(i).getNome());
            for (int j = 0; j < listTamDisp.size(); j++) {
                if (listTamDisp.get(j).getCodigo().equals(listTamSelec.get(i).getCodigo())) {
                    listTamDisp.remove(j);
                    j = listTamDisp.size();
                }
            }
        }

        listCorSelec = p.getCaracteristicas(2);
        for (int i = 0; i < listCorSelec.size(); i++) {
            lmCoresSelecProdt.addElement(listCorSelec.get(i).getNome());
            for (int j = 0; j < listCorDisp.size(); j++) {
                if (listCorDisp.get(j).getCodigo().equals(listCorSelec.get(i).getCodigo())) {
                    listCorDisp.remove(j);
                    j = listCorDisp.size();
                }
            }
        }

        carregaCaracteristicas();
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
        txtCodProduto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDataInclProduto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtVlrVendaProduto = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstTamDispProduto = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        btnIncluirTamProduto = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnExcluirTamProduto = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstTamSelecProduto = new RequiredListBox(true);
        btnNovoTamanho = new javax.swing.JButton();
        txtNovoTamanho = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstCoresDispProduto = new javax.swing.JList();
        btnIncluirCorProduto = new javax.swing.JButton();
        btnExcluirCorProduto = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstCoresSelecProduto = new RequiredListBox(true);
        jLabel6 = new javax.swing.JLabel();
        txtNovaCor = new javax.swing.JTextField();
        btnNovaCor = new javax.swing.JButton();
        txtDescProduto = new componente.text.RequiredTextField();
        txtVlrCustoProduto = new componente.text.RequiredTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnSalvar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();

        setAutoscrolls(true);
        setMaximumSize(new java.awt.Dimension(0, 0));
        setMinimumSize(new java.awt.Dimension(800, 500));
        setName("ProtCadastraCliente"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 500));
        setVerifyInputWhenFocusTarget(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel3.setText("Código:");

        txtCodProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodProdutoKeyReleased(evt);
            }
        });

        jLabel2.setText("Data Inclusão:");

        txtDataInclProduto.setEditable(false);
        txtDataInclProduto.setFocusable(false);
        txtDataInclProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataInclProdutoActionPerformed(evt);
            }
        });

        jLabel1.setText("Descrição:");

        jLabel4.setText("Valor de Custo:");

        jLabel10.setText("Valor de Venda:");

        txtVlrVendaProduto.setInputVerifier(verificador);
        txtVlrVendaProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CampoDinheiroFocusGained(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tamanhos"));

        lstTamDispProduto.setModel(lmTamDispProdt);
        lstTamDispProduto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstTamDispProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lstTamDispProdutoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lstTamDispProdutoFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(lstTamDispProduto);

        jLabel8.setText("Disponíveis:");

        btnIncluirTamProduto.setText(">");
        btnIncluirTamProduto.setEnabled(false);
        btnIncluirTamProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirTamProdutoActionPerformed(evt);
            }
        });

        jLabel9.setText("Selecionados:");

        btnExcluirTamProduto.setText("<");
        btnExcluirTamProduto.setEnabled(false);
        btnExcluirTamProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirTamProdutoActionPerformed(evt);
            }
        });

        lstTamSelecProduto.setModel(lmTamSelecProdt);
        lstTamSelecProduto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstTamSelecProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lstTamSelecProdutoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lstTamSelecProdutoFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(lstTamSelecProduto);

        btnNovoTamanho.setText("+");
        btnNovoTamanho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoTamanhoActionPerformed(evt);
            }
        });

        jLabel5.setText("Novo Tamanho:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnIncluirTamProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnExcluirTamProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNovoTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNovoTamanho))
                    .addComponent(jLabel5))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(btnIncluirTamProduto)
                                .addGap(68, 68, 68)
                                .addComponent(btnExcluirTamProduto)
                                .addGap(102, 102, 102))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNovoTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovoTamanho))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cores"));

        jLabel11.setText("Disponíveis:");

        lstCoresDispProduto.setModel(lmCoresDispProdt);
        lstCoresDispProduto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstCoresDispProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lstCoresDispProdutoFocusGained(evt);
            }
        });
        jScrollPane3.setViewportView(lstCoresDispProduto);

        btnIncluirCorProduto.setText(">");
        btnIncluirCorProduto.setEnabled(false);
        btnIncluirCorProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirCorProdutoActionPerformed(evt);
            }
        });

        btnExcluirCorProduto.setText("<");
        btnExcluirCorProduto.setEnabled(false);
        btnExcluirCorProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirCorProdutoActionPerformed(evt);
            }
        });

        jLabel12.setText("Selecionados:");

        lstCoresSelecProduto.setModel(lmCoresSelecProdt);
        lstCoresSelecProduto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstCoresSelecProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lstCoresSelecProdutoFocusGained(evt);
            }
        });
        jScrollPane4.setViewportView(lstCoresSelecProduto);

        jLabel6.setText("Nova Cor:");

        btnNovaCor.setText("+");
        btnNovaCor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaCorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(btnExcluirCorProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnIncluirCorProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtNovaCor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNovaCor))
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane4)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnIncluirCorProduto)
                        .addGap(67, 67, 67)
                        .addComponent(btnExcluirCorProduto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNovaCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovaCor))
                .addContainerGap())
        );

        jSeparator1.setAutoscrolls(true);

        btnSalvar.setText("Salvar");
        btnSalvar.setFocusable(false);
        btnSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnFechar.setText("Fechar");
        btnFechar.setFocusable(false);
        btnFechar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFechar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtVlrCustoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtVlrVendaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDataInclProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txtDescProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnFechar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtDescProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(txtVlrVendaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtVlrCustoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtDataInclProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFechar)
                    .addComponent(btnSalvar))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        telaCarregada.dispose();


    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
       
        if (validarProduto()) {
            salvarProd();
        }

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirTamProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirTamProdutoActionPerformed
        listTamDisp.add(listTamSelec.get(lstTamSelecProduto.getSelectedIndex()));
        listTamSelec.remove(lstTamSelecProduto.getSelectedIndex());
        lmTamDispProdt.addElement(lmTamSelecProdt.get(lstTamSelecProduto.getSelectedIndex()));
        lmTamSelecProdt.remove(lstTamSelecProduto.getSelectedIndex());
        btnIncluirTamProduto.setEnabled(false);
        btnExcluirTamProduto.setEnabled(false);


    }//GEN-LAST:event_btnExcluirTamProdutoActionPerformed

    private void btnExcluirCorProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirCorProdutoActionPerformed
        listCorDisp.add(listCorSelec.get(lstCoresSelecProduto.getSelectedIndex()));
        listCorSelec.remove(lstCoresSelecProduto.getSelectedIndex());
        lmCoresDispProdt.addElement(lmCoresSelecProdt.get(lstCoresSelecProduto.getSelectedIndex()));
        lmCoresSelecProdt.remove(lstCoresSelecProduto.getSelectedIndex());
        btnIncluirCorProduto.setEnabled(false);
        btnExcluirCorProduto.setEnabled(false);

    }//GEN-LAST:event_btnExcluirCorProdutoActionPerformed

    private void btnIncluirTamProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirTamProdutoActionPerformed
        listTamSelec.add(listTamDisp.get(lstTamDispProduto.getSelectedIndex()));
        listTamDisp.remove(lstTamDispProduto.getSelectedIndex());
        lmTamSelecProdt.addElement(lmTamDispProdt.get(lstTamDispProduto.getSelectedIndex()));
        lmTamDispProdt.remove(lstTamDispProduto.getSelectedIndex());
        btnIncluirTamProduto.setEnabled(false);
        btnExcluirTamProduto.setEnabled(false);


    }//GEN-LAST:event_btnIncluirTamProdutoActionPerformed

    private void btnIncluirCorProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirCorProdutoActionPerformed
        listCorSelec.add(listCorDisp.get(lstCoresDispProduto.getSelectedIndex()));
        listCorDisp.remove(lstCoresDispProduto.getSelectedIndex());
        lmCoresSelecProdt.addElement(lmCoresDispProdt.get(lstCoresDispProduto.getSelectedIndex()));
        lmCoresDispProdt.remove(lstCoresDispProduto.getSelectedIndex());
        btnIncluirCorProduto.setEnabled(false);
        btnExcluirCorProduto.setEnabled(false);


    }//GEN-LAST:event_btnIncluirCorProdutoActionPerformed

    private void lstTamDispProdutoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lstTamDispProdutoFocusGained
        btnIncluirTamProduto.setEnabled(true);
        btnExcluirTamProduto.setEnabled(false);
        lstTamSelecProduto.removeSelectionInterval(lstTamSelecProduto.getMinSelectionIndex(), lstTamSelecProduto.getMaxSelectionIndex());
    }//GEN-LAST:event_lstTamDispProdutoFocusGained

    private void lstTamDispProdutoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lstTamDispProdutoFocusLost
    }//GEN-LAST:event_lstTamDispProdutoFocusLost

    private void lstTamSelecProdutoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lstTamSelecProdutoFocusLost
    }//GEN-LAST:event_lstTamSelecProdutoFocusLost

    private void lstTamSelecProdutoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lstTamSelecProdutoFocusGained
        btnIncluirTamProduto.setEnabled(false);
        btnExcluirTamProduto.setEnabled(true);
        lstTamDispProduto.removeSelectionInterval(lstTamDispProduto.getMinSelectionIndex(), lstTamDispProduto.getMaxSelectionIndex());

    }//GEN-LAST:event_lstTamSelecProdutoFocusGained

    private void lstCoresDispProdutoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lstCoresDispProdutoFocusGained
        btnIncluirCorProduto.setEnabled(true);
        btnExcluirCorProduto.setEnabled(false);
        lstCoresSelecProduto.removeSelectionInterval(lstCoresSelecProduto.getMinSelectionIndex(), lstCoresSelecProduto.getMaxSelectionIndex());
    }//GEN-LAST:event_lstCoresDispProdutoFocusGained

    private void lstCoresSelecProdutoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lstCoresSelecProdutoFocusGained
        btnIncluirCorProduto.setEnabled(false);
        btnExcluirCorProduto.setEnabled(true);
        lstCoresDispProduto.removeSelectionInterval(lstCoresDispProduto.getMinSelectionIndex(), lstCoresDispProduto.getMaxSelectionIndex());
    }//GEN-LAST:event_lstCoresSelecProdutoFocusGained

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
    }//GEN-LAST:event_formComponentShown

    private void btnNovoTamanhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoTamanhoActionPerformed
        Caracteristica NovoTam = new Caracteristica();
        NovoTam.setNome(txtNovoTamanho.getText());
        NovoTam.setPai(Tamanho);
        new OADCaracteristica().salvar(NovoTam);
        listTamDisp.add(NovoTam);
        carregaCaracteristicas();
        txtNovoTamanho.setText(null);
    }//GEN-LAST:event_btnNovoTamanhoActionPerformed

    private void btnNovaCorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaCorActionPerformed
        Caracteristica NovaCor = new Caracteristica();
        NovaCor.setNome(txtNovaCor.getText());
        NovaCor.setPai(Cor);
        new OADCaracteristica().salvar(NovaCor);
        listCorDisp.add(NovaCor);
        carregaCaracteristicas();
        txtNovaCor.setText(null);
    }//GEN-LAST:event_btnNovaCorActionPerformed

    private void txtDataInclProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataInclProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataInclProdutoActionPerformed

    private void CampoDinheiroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CampoDinheiroFocusGained
        JTextField t = (JTextField) evt.getSource();
        t.setText(t.getText().replace("R$ ", ""));
        t.selectAll();
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoDinheiroFocusGained

    private void txtCodProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProdutoKeyReleased
        JTextField t = (JTextField) evt.getSource();
        t.setText(t.getText().replaceAll("[^0-9]", ""));
    }//GEN-LAST:event_txtCodProdutoKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btnExcluirCorProduto;
    javax.swing.JButton btnExcluirTamProduto;
    javax.swing.JButton btnFechar;
    javax.swing.JButton btnIncluirCorProduto;
    javax.swing.JButton btnIncluirTamProduto;
    javax.swing.JButton btnNovaCor;
    javax.swing.JButton btnNovoTamanho;
    javax.swing.JButton btnSalvar;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel10;
    javax.swing.JLabel jLabel11;
    javax.swing.JLabel jLabel12;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel5;
    javax.swing.JLabel jLabel6;
    javax.swing.JLabel jLabel8;
    javax.swing.JLabel jLabel9;
    javax.swing.JPanel jPanel1;
    javax.swing.JPanel jPanel2;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JScrollPane jScrollPane2;
    javax.swing.JScrollPane jScrollPane3;
    javax.swing.JScrollPane jScrollPane4;
    javax.swing.JSeparator jSeparator1;
    javax.swing.JList lstCoresDispProduto;
    javax.swing.JList lstCoresSelecProduto;
    javax.swing.JList lstTamDispProduto;
    javax.swing.JList lstTamSelecProduto;
    javax.swing.JTextField txtCodProduto;
    javax.swing.JTextField txtDataInclProduto;
    componente.text.RequiredTextField txtDescProduto;
    javax.swing.JTextField txtNovaCor;
    javax.swing.JTextField txtNovoTamanho;
    componente.text.RequiredTextField txtVlrCustoProduto;
    javax.swing.JTextField txtVlrVendaProduto;
    // End of variables declaration//GEN-END:variables

    private void limparCampos() {
        txtDescProduto.setText(null);
        txtDataInclProduto.setText(null);
        txtCodProduto.setText(null);
        txtVlrCustoProduto.setText(null);
        txtVlrVendaProduto.setText(null);
        btnIncluirTamProduto.setEnabled(false);
        btnExcluirTamProduto.setEnabled(false);
        btnIncluirCorProduto.setEnabled(false);
        btnExcluirCorProduto.setEnabled(false);
    }

    private void habilitaFormularioCadastro(Boolean valor) {
        this.txtCodProduto.setEnabled(valor);
        this.txtDataInclProduto.setEnabled(valor);
        this.txtDescProduto.setEnabled(valor);
        this.txtNovaCor.setEnabled(valor);
        this.txtNovoTamanho.setEnabled(valor);
        this.txtVlrCustoProduto.setEnabled(valor);
        this.txtVlrVendaProduto.setEnabled(valor);
        this.lstCoresDispProduto.setEnabled(valor);
        this.lstCoresSelecProduto.setEnabled(valor);
        this.lstTamDispProduto.setEnabled(valor);
        this.lstTamSelecProduto.setEnabled(valor);
        this.btnSalvar.setEnabled(valor);
    }

    private Boolean validaFormulario() {
        Boolean retorno = true;
        if (!((RequiredTextField) this.txtDescProduto).runValidation()) {
            retorno = false;
        }

        if (!((RequiredTextField) this.txtVlrCustoProduto).runValidation()) {
            retorno = false;
        }
        if (!((RequiredListBox)this.lstTamSelecProduto).runValidation()) {
            retorno = false;
        }
        if (!((RequiredListBox) this.lstCoresSelecProduto).runValidation()) {
            retorno = false;
        }
        return retorno;
    }

    private boolean validarProduto() {
        Boolean valido = true;
        
        if(txtCodProduto.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Código Referência está vazio!", "", JOptionPane.INFORMATION_MESSAGE);
        }else{
            Integer qtdeRefIguais = new OADProduto().ContaReferencia(Integer.valueOf(txtCodProduto.getText()));
        if (produtoCarregado != null) {
            if (!(produtoCarregado.getCodigoReferencia().equals(Integer.valueOf(txtCodProduto.getText())))
                    && (qtdeRefIguais > 0)) {
                JOptionPane.showMessageDialog(this, "Código Referência já existente!", "", JOptionPane.INFORMATION_MESSAGE);
                txtCodProduto.requestFocus();
                valido = false;
            }
        } else {
            if (txtCodProduto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código Referência é obrigatório!", "", JOptionPane.INFORMATION_MESSAGE);
                txtCodProduto.requestFocus();
                valido = false;
            } else {
                if (qtdeRefIguais > 0) {
                    JOptionPane.showMessageDialog(this, "Código Referência já existente!", "", JOptionPane.INFORMATION_MESSAGE);
                    txtCodProduto.requestFocus();
                    valido = false;
                }
            }
        }
        }
        return valido;
    }

    private void salvarProd() {
        List<Caracteristica> CaracteristicasSelecionadas = new ArrayList<Caracteristica>();
        for (int i = 0; i < listCorSelec.size(); i++) {
            CaracteristicasSelecionadas.add(listCorSelec.get(i));
        }
        for (int i = 0; i < listTamSelec.size(); i++) {
            CaracteristicasSelecionadas.add(listTamSelec.get(i));
        }
        Produto novoProd = new Produto();

        if (produtoCarregado != null) {
            novoProd.setCodigo(produtoCarregado.getCodigo());
        }
        novoProd.setCodigoReferencia(Integer.valueOf(txtCodProduto.getText()));
        novoProd.setCaracteristicas(CaracteristicasSelecionadas);
        novoProd.setDescricao(txtDescProduto.getText());
        novoProd.setDataInclusao(new Date());
        novoProd.setValorCusto(NumberFieldVerifier.GetValorFormatado(txtVlrCustoProduto.getText()));
        novoProd.setValorVenda(NumberFieldVerifier.GetValorFormatado(txtVlrVendaProduto.getText()));
        if (validaFormulario()) {
            if (new OADProduto().salvar(novoProd)) {
                JOptionPane.showMessageDialog(this, "Registro Salvo com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
                if (telaChamadora == null) {
                    limparCampos();
                    inicializaCaracteristicas();
                } else {
                    if (telaChamadora.getClass().equals(FormConsultaProduto.class)) {
                        FormConsultaProduto telaConsultaProd = (FormConsultaProduto) telaChamadora;
                        telaConsultaProd.consultarProduto(null);
                    } else {
                        FormCadPedido telaCadPedido = (FormCadPedido) telaChamadora;
                        telaCadPedido.carregaCaracteristicasDoProduto(novoProd);
                    }
                    telaCarregada.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Operação NÃO efetuada! Contatar Analista.", "", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Há campos necessários não preenchidos, por favor, verifique.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
}
