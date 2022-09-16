package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import materia.Materia;
import view.guiElements.Botao;

// Pedido será "verify" para verficar as materias selecionadas
// Quando verificar, atualizar tanto as matérias solicitadas quanto as matérias a serem pedidas
// Pedido será "save" quando se deseja salvar
// Quando pedido for "save" TelaSolicitar.getExportPath(); retorna o caminha do arquivo a ser salvo
public class TelaSolicitar extends Tela{
  
  // Caminho do arquivo pra salvar
  private String exportPath = "";

  // panel para organização de botões
  private JPanel botoes;

  // botao para voltar para tela inicial
  private Botao bMenu;
  private Botao bEstadoMaterias;

  private static TelaSolicitar instancia = null;

  private Font fonte;

  // referente a grade de materias ofertadas por periodo que ainda nao foram cursadas
  private Vector<Materia> materiasNaoCursadasOfertadas = new Vector<Materia>();
  private Vector<Materia> materiasNaoCursadasSolicitadas = new Vector<Materia>();
  private JList<String> listNaoCursadas;
  private JList<String> listSolicitadas;
  private JScrollPane barraNaoCursadas;
  private JScrollPane barraSolicitadas;

  private JPanel centralPanel;
  private Botao bAdd;
  private Botao bRmv;

  private Botao bVerificar;
  private Botao bConfirmar;

  // quantidade de matérias que é possível pegar
  private int quantSugerido;
  private JTextArea quantSugeridoLabel;

  public static synchronized TelaSolicitar getInstance(){
    if (instancia == null)
      instancia = new TelaSolicitar();
    return instancia;
  }

  private TelaSolicitar(){
    // quantSugerido = 5;
    this.setLayout(new BorderLayout(10,10));
    fonte = new Font("Hack", Font.BOLD, 16);

    JLabel titulo = new JLabel("Selecionar matérias para solicitar, clique na ordem de prioridade (é necessário verificar antes de salvar)");
    titulo.setHorizontalAlignment(SwingConstants.CENTER);
    titulo.setFont(fonte);
    this.add(titulo, BorderLayout.PAGE_START);

    listNaoCursadas = geraLista(listNaoCursadas, materiasNaoCursadasOfertadas, BorderLayout.WEST, barraNaoCursadas);
    listSolicitadas = geraLista(listSolicitadas, materiasNaoCursadasSolicitadas, BorderLayout.EAST, barraSolicitadas);

    fazBotoes();
    this.add(botoes, BorderLayout.PAGE_END);

    fazPainelCentral();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Solicitar Matérias");
    this.setMinimumSize(new Dimension(500,300));
  }

  private JList<String> geraLista(JList<String> lista, Vector<Materia> materias, String pos, JScrollPane scr){
    if(materias == null) return lista;
    if(lista != null && scr != null){
      scr.remove(lista);
      this.remove(scr);
    }

    DefaultListModel<String> demo = new DefaultListModel<String>();
    for(int i = 0; i < materias.size(); i++)
      demo.addElement( materiaToString(materias.get(i)) );

    JList<String> result = new JList<String>(demo);
    result.setFont(fonte);
    if(scr == barraNaoCursadas){
      barraNaoCursadas = new JScrollPane(result);
      this.add(barraNaoCursadas, pos);
    }
    else if(scr == barraSolicitadas){
      barraSolicitadas = new JScrollPane(result);
      this.add(barraSolicitadas, pos);
    }
    else{
      scr = new JScrollPane(result);
      this.add(scr, pos);
    }
    return result;
  }

  private void fazPainelCentral(){
    if(centralPanel != null) centralPanel.removeAll();
    bAdd = new Botao(">>>", fonte, this);
    bRmv = new Botao("<<<", fonte, this);

    bVerificar = new Botao("Verificar estado atual", fonte, this);
    bConfirmar = new Botao("Salvar pedido em um arquivo", fonte, this);
    bConfirmar.setEnabled(false);


    quantSugeridoLabel = new JTextArea("São sugeridas " + quantSugerido + " matérias");
    quantSugeridoLabel.setFont(fonte);
    quantSugeridoLabel.setEditable(false);
    quantSugeridoLabel.setLineWrap(true);
    quantSugeridoLabel.setBackground(new Color(0xEEEEEE));

    bAdd.      setMaximumSize(new Dimension(350, 100));
    bRmv.      setMaximumSize(new Dimension(350, 100));
    bVerificar.setMaximumSize(new Dimension(350, 100));
    bConfirmar.setMaximumSize(new Dimension(350, 100));
    quantSugeridoLabel.setMaximumSize(new Dimension(350, 200));

    bAdd.      setAlignmentX(CENTER_ALIGNMENT);
    bRmv.      setAlignmentX(CENTER_ALIGNMENT);
    bVerificar.setAlignmentX(CENTER_ALIGNMENT);
    bConfirmar.setAlignmentX(CENTER_ALIGNMENT);
    quantSugeridoLabel.setAlignmentX(CENTER_ALIGNMENT);

    bAdd.      setAlignmentY(CENTER_ALIGNMENT);
    bRmv.      setAlignmentY(CENTER_ALIGNMENT);
    bVerificar.setAlignmentY(CENTER_ALIGNMENT);
    bConfirmar.setAlignmentY(CENTER_ALIGNMENT);
    quantSugeridoLabel.setAlignmentY(CENTER_ALIGNMENT);

    centralPanel = new JPanel();
    centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
    centralPanel.add(quantSugeridoLabel);
    centralPanel.add(bVerificar);
    centralPanel.add(bAdd);
    centralPanel.add(bRmv);
    centralPanel.add(bConfirmar);
    this.add(centralPanel, BorderLayout.CENTER);
  }

  private String materiaToString(Materia mat){
    // Código, Nome, Carga horária, Período
    String result;
    result  = mat.getCodDisci();
    result += "  " + mat.getNomeDisci();
    result += "  " + "CH" + Integer.toString(mat.getChTotal());
    result += "  " + (mat.getPeriodo() <= 8 ? Integer.toString(mat.getPeriodo()) + "° Período" : "Optativa");
    return result;
  }

  private void fazBotoes(){
    bMenu = new Botao("Menu Inicial", fonte, this);
    bEstadoMaterias = new Botao("Estado das matérias", fonte, this);

    botoes = new JPanel(new GridLayout(1, 2, 10, 10));
    botoes.add(bEstadoMaterias);
    botoes.add(bMenu);
  }

  private void updateScreen(){
    if(this.isVisible()){
      this.setVisible(true);
    }
  }

  // Função a ser executada quando aperta botão
  @Override
  public void actionPerformed(ActionEvent e){
    Object source = e.getSource();
    // Adicionar/remover materias
    if(source == bAdd){
      int index = listNaoCursadas.getSelectedIndex();
      if(index == -1) return;
      Materia selecionada = materiasNaoCursadasOfertadas.get(index);
      System.out.println("Adicionar materia: " + selecionada.getNomeDisci());
      // Passar materia de uma lista para outra
      materiasNaoCursadasOfertadas.remove(selecionada);
      materiasNaoCursadasSolicitadas.add(selecionada);
      // Atualizar interface
      listNaoCursadas = geraLista(listNaoCursadas, materiasNaoCursadasOfertadas, BorderLayout.WEST, barraNaoCursadas);
      listSolicitadas = geraLista(listSolicitadas, materiasNaoCursadasSolicitadas, BorderLayout.EAST, barraSolicitadas);
      bConfirmar.setEnabled(false);
      updateScreen();
      return;
    } else if (source == bRmv){
      int index = listSolicitadas.getSelectedIndex();
      if(index == -1) return;
      Materia selecionada = materiasNaoCursadasSolicitadas.get(index);
      System.out.println("Remover materia: " + selecionada.getNomeDisci());
      // Passar materia de uma lista para outra
      materiasNaoCursadasOfertadas.add(selecionada);
      materiasNaoCursadasSolicitadas.remove(selecionada);
      // Atualizar interface
      listNaoCursadas = geraLista(listNaoCursadas, materiasNaoCursadasOfertadas, BorderLayout.WEST, barraNaoCursadas);
      listSolicitadas = geraLista(listSolicitadas, materiasNaoCursadasSolicitadas, BorderLayout.EAST, barraSolicitadas);
      bConfirmar.setEnabled(false);
      updateScreen();
      return;
    }
    // Verificar/Salvar
    if(source == bVerificar){
      super.setPedido("verify");
      this.updateSub();
      super.setPedido("");
      bConfirmar.setEnabled(true);
      return;
    } else if (source == bConfirmar){
      JFileChooser arqs = new JFileChooser();
      arqs.setCurrentDirectory(new File("."));
      arqs.setFont(fonte);
      int response = arqs.showSaveDialog(null);
      if(response == JFileChooser.APPROVE_OPTION){
        String path = arqs.getSelectedFile().getAbsolutePath();
        super.setPedido("save");
        this.exportPath = path;
        this.updateSub();
        super.setPedido("");
      }
      return;
    }
    //Trocar de menu
    JFrame proxTela;
    if(source == bMenu){
      proxTela = TelaInicial.getInstance();
    } else if(source == bEstadoMaterias){
      proxTela = TelaEstado.getInstance();
    } else {
      return;
    }
    proxTela.setLocationRelativeTo(this);
    proxTela.setBounds(this.getBounds());
    this.setVisible(false);
    proxTela.setVisible(true);
  }

  // referente a grade de materias ofertadas por periodo que ainda nao foram cursadas
  public Vector<Materia> getMateriasNaoCursadasOfertadas() {
    return materiasNaoCursadasOfertadas;
  }
  public void setMateriasNaoCursadasOfertadas(Vector<Materia> materiasNaoCursadasOfertadas) {
    this.materiasNaoCursadasOfertadas = materiasNaoCursadasOfertadas;
    listNaoCursadas = geraLista(listNaoCursadas, materiasNaoCursadasOfertadas, BorderLayout.WEST, barraNaoCursadas);
    updateScreen();
  }
  // referente a grade de materias ofertadas por periodo que serão solicitadas
  public Vector<Materia> getMateriasNaoCursadasSolicitadas() {
    return materiasNaoCursadasSolicitadas;
  }
  public void setMateriasNaoCursadasSolicitadas(Vector<Materia> materiasNaoCursadasSolicitadas) {
    this.materiasNaoCursadasSolicitadas = materiasNaoCursadasSolicitadas;
    listSolicitadas = geraLista(listSolicitadas, materiasNaoCursadasSolicitadas, BorderLayout.EAST, barraSolicitadas);
    updateScreen();
  }
  // arquivo salvar
  public String getExportPath(){ return this.exportPath; }

  public void setQuantSugerido(int quantSugerido){
    this.quantSugerido = quantSugerido;
    fazPainelCentral();
    updateScreen();
  }

  public int getQuantSugerido() { return this.quantSugerido; }
}
