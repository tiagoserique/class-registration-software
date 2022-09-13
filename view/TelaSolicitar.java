package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
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

  private JPanel centralPanel;
  private Botao bAdd;
  private Botao bRmv;

  private Botao bVerificar;
  private Botao bConfirmar;


  public static synchronized TelaSolicitar getInstance(){
    if (instancia == null)
      instancia = new TelaSolicitar();
    return instancia;
  }

  private TelaSolicitar(){
    this.setLayout(new BorderLayout(10,10));
    fonte = new Font("Hack", Font.BOLD, 16);

    JLabel titulo = new JLabel("Selecionar matérias para solicitar, clique na ordem de prioridade (é necessário verificar antes de salvar)");
    titulo.setHorizontalAlignment(SwingConstants.CENTER);
    titulo.setFont(fonte);
    this.add(titulo, BorderLayout.PAGE_START);

    listNaoCursadas = geraLista(listNaoCursadas, materiasNaoCursadasOfertadas, BorderLayout.WEST);
    listSolicitadas = geraLista(listSolicitadas, materiasNaoCursadasSolicitadas, BorderLayout.EAST);

    fazBotoes();
    this.add(botoes, BorderLayout.PAGE_END);

    fazPainelCentral();
    this.add(centralPanel, BorderLayout.CENTER);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Solicitar Matérias");
    this.setMinimumSize(new Dimension(500,300));
  }

  private JList<String> geraLista(JList<String> lista, Vector<Materia> materias, String pos){
    if(materias == null) return lista;
    if(lista != null){
      this.remove(lista);
    }

    DefaultListModel<String> demo = new DefaultListModel<String>();
    for(int i = 0; i < materias.size(); i++)
      demo.addElement( materiaToString(materias.get(i)) );

    JList<String> result = new JList<String>(demo);
    result.setFont(fonte);
    this.add(result, pos);
    return result;
  }

  private void fazPainelCentral(){
    bAdd = new Botao(">>>", fonte, this);
    bRmv = new Botao("<<<", fonte, this);

    bVerificar = new Botao("Verificar estado atual", fonte, this);
    bConfirmar = new Botao("Salvar pedido em um arquivo", fonte, this);
    bConfirmar.setEnabled(false);

    centralPanel = new JPanel(new GridLayout(4, 1, 2, 2));
    centralPanel.add(bVerificar);
    centralPanel.add(bAdd);
    centralPanel.add(bRmv);
    centralPanel.add(bConfirmar);
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
      this.setVisible(false);
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
      listNaoCursadas = geraLista(listNaoCursadas, materiasNaoCursadasOfertadas, BorderLayout.WEST);
      listSolicitadas = geraLista(listSolicitadas, materiasNaoCursadasSolicitadas, BorderLayout.EAST);
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
      listNaoCursadas = geraLista(listNaoCursadas, materiasNaoCursadasOfertadas, BorderLayout.WEST);
      listSolicitadas = geraLista(listSolicitadas, materiasNaoCursadasSolicitadas, BorderLayout.EAST);
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
    listNaoCursadas = geraLista(listNaoCursadas, materiasNaoCursadasOfertadas, BorderLayout.WEST);
    updateScreen();
  }
  // referente a grade de materias ofertadas por periodo que serão solicitadas
  public Vector<Materia> getMateriasNaoCursadasSolicitadas() {
    return materiasNaoCursadasSolicitadas;
  }
  public void setMateriasNaoCursadasSolicitadas(Vector<Materia> materiasNaoCursadasSolicitadas) {
    this.materiasNaoCursadasSolicitadas = materiasNaoCursadasSolicitadas;
    listSolicitadas = geraLista(listSolicitadas, materiasNaoCursadasSolicitadas, BorderLayout.EAST);
    updateScreen();
  }
  // arquivo salvar
  public String getExportPath(){ return this.exportPath; }
}
