package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import materia.Materia;
import materia.MateriaHistorico;
import view.guiElements.Botao;

public class TelaEstado extends JFrame implements ActionListener{

  // panel para organização de botões
  private JPanel botoes;

  // botao para voltar para tela inicial
  private Botao bMenu;
  // tela inicial
  private TelaInicial inicio;

  // tabela a ser mostrada
  private JTable materiasCursadasTabela;
  private JScrollPane materiasCursadasSp;

  // porcentagem de aprovacao do ultimo periodo
  private Double porcentAprovacao;
  // quantidade de reprovacao do ultimo periodo
  private int quantidadeReprovacaoFalta;
  // lista de materias que faltam para passar a barreira
  private Vector<Materia> materiasBarreira;
  // ofertadas e ainda nao cursadas, ou nao aprovadas, por periodo
  private Vector<Vector<MateriaHistorico>> materiasCursadas;


  private static TelaEstado instancia = null;

  private Font fonte;

  // Para ser singleton
  public static synchronized TelaEstado getInstance(){
    if (instancia == null)
      instancia = new TelaEstado();
    return instancia;
  }

  // Cria layout inicial da janela
  private TelaEstado(){
    this.setLayout(new BorderLayout(10,10));
    fonte = new Font("Hack", Font.BOLD, 16);

    JLabel titulo = new JLabel("Estado das matérias atuais");
    titulo.setHorizontalAlignment(SwingConstants.CENTER);
    titulo.setFont(fonte);

    materiasCursadasSp = new JScrollPane(materiasCursadasTabela);
    this.add(materiasCursadasSp, BorderLayout.CENTER);
    criaTabela();

    fazBotoes();

    this.add(botoes, BorderLayout.PAGE_END);
    this.add(titulo, BorderLayout.PAGE_START);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setTitle("Estado das matérias");
  }

  // Cria o botão de voltar e coloca na tela
  private void fazBotoes(){
    bMenu = new Botao("Voltar", fonte, this);

    botoes = new JPanel(new GridLayout(1, 2, 10, 10));
    botoes.add(bMenu);
  }

  // Gera a tabela pra ser mostrada
  private void criaTabela(){
    // this.remove(materiasCursadasTabela);
    this.remove(materiasCursadasSp);

    String colunas[] = {"Código", "Nome", "Media Final"};
    String data[][]={ {"CI1389", "Materia doida", "0"} };
    materiasCursadasTabela = new JTable(data, colunas);
    materiasCursadasSp = new JScrollPane(materiasCursadasTabela);

    // this.add(materiasCursadasTabela, BorderLayout.CENTER);
    this.add(materiasCursadasSp, BorderLayout.CENTER);
  }

  // Transforma materia em vetor de Strings
  private String[] fromMateriaToString(MateriaHistorico mat){
    String[] res = new String[3];
    res[0] = mat.getSigla();
    res[1] = mat.getSigla();
    res[2] = mat.getMediaFinal();
    return res;
  }

  // Função a ser executada quando aperta botão
  @Override
  public void actionPerformed(ActionEvent e){
    if (e.getSource() == bMenu){
      inicio = TelaInicial.getInstance();
      inicio.setLocationRelativeTo(this);
      this.setVisible(false);
      inicio.setVisible(true);
    }
  }


  public Double getPorcentAprovacao(){ 
    return porcentAprovacao; 
  }
  public int getQuantidadeReprovacaoFalta(){ 
    return quantidadeReprovacaoFalta; 
  }
  public Vector<Materia> getMateriasBarreira(){ 
    return materiasBarreira; 
  }
  public Vector<Vector<MateriaHistorico>> getMateriasCursadas() {
    return materiasCursadas;
  }

  public void setPorcentAprovacao(Double porcentAprovacao){
    this.porcentAprovacao = porcentAprovacao; 
  }
  public void setQuantidadeReprovacaoFalta(int quantidadeReprovacaoFalta){ 
    this.quantidadeReprovacaoFalta = quantidadeReprovacaoFalta; 
  }
  public void setMateriasBarreira(Vector<Materia> materiasBarreira){ 
    this.materiasBarreira = materiasBarreira; 
  }
  public void setMateriasCursadas(Vector<Vector<MateriaHistorico>> materiasCursadas) {
    this.materiasCursadas = materiasCursadas;
    criaTabela();
  }
}
