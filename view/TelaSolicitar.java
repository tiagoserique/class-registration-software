package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import materia.Materia;
import view.guiElements.Botao;


public class TelaSolicitar extends Tela{

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

  private JPanel addRmvPanel;
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

    JLabel titulo = new JLabel("Selecionar matérias para solicitar, clique na ordem de prioridade");
    titulo.setHorizontalAlignment(SwingConstants.CENTER);
    titulo.setFont(fonte);
    this.add(titulo, BorderLayout.PAGE_START);

    listNaoCursadas = geraLista(listNaoCursadas, materiasNaoCursadasOfertadas, BorderLayout.WEST);
    listSolicitadas = geraLista(listSolicitadas, materiasNaoCursadasSolicitadas, BorderLayout.EAST);

    fazBotoes();
    this.add(botoes, BorderLayout.PAGE_END);

    addRmvMake();
    this.add(addRmvPanel, BorderLayout.CENTER);

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

  private void addRmvMake(){
    bAdd = new Botao(">>>", fonte, this);
    bRmv = new Botao("<<<", fonte, this);

    addRmvPanel = new JPanel(new GridLayout(4, 1, 2, 2));
    addRmvPanel.add(new JLabel());
    addRmvPanel.add(bAdd);
    addRmvPanel.add(bRmv);
    addRmvPanel.add(new JLabel());
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

  // Função a ser executada quando aperta botão
  @Override
  public void actionPerformed(ActionEvent e){
    Object source = e.getSource();
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
      this.setVisible(false);
      this.setVisible(true);
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
      this.setVisible(false);
      this.setVisible(true);
      return;
    }
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
  }
  // referente a grade de materias ofertadas por periodo que serão solicitadas
  public Vector<Materia> getMateriasNaoCursadasSolicitadas() {
    return materiasNaoCursadasSolicitadas;
  }
  public void setMateriasNaoCursadasSolicitadas(Vector<Materia> materiasNaoCursadasSolicitadas) {
    this.materiasNaoCursadasSolicitadas = materiasNaoCursadasSolicitadas;
    listSolicitadas = geraLista(listSolicitadas, materiasNaoCursadasSolicitadas, BorderLayout.EAST);
  }
}
