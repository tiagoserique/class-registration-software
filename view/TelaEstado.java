package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

  // quantidade de campos a serem exibidos para cada materia
  private final int numeroCampos = 5;

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
    this.setTitle("Estado das matérias");
    this.setMinimumSize(new Dimension(200,200));
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
    if(materiasCursadas == null){
      return;
    }
    this.remove(materiasCursadasSp);

    String colunas[] = {"Código", "Nome", "Media Final", "Carga Horária", "Período"};
    String data[][]  = fromMateriaMatrizToStringMatriz(materiasCursadas);
    materiasCursadasTabela = new JTable(data, colunas);
    materiasCursadasSp = new JScrollPane(materiasCursadasTabela);
    materiasCursadasSp.setFont(fonte);
    materiasCursadasSp.setBounds(2, 2, 20, 20);

    // this.add(materiasCursadasTabela, BorderLayout.CENTER);
    this.add(materiasCursadasSp, BorderLayout.CENTER);
  }

  // Transforma materia em vetor de Strings
  private String[] fromMateriaToString(MateriaHistorico mat, int periodo){
    String[] res = new String[numeroCampos];
    res[0] = mat.getCodCurso();
    res[1] = mat.getNomeAtivCurri();
    res[2] = mat.getMediaFinal();
    res[3] = mat.getChTotal();
    res[4] = Integer.toString(periodo);
    return res;
  }

  // Transforma
  private String[][] fromMateriaMatrizToStringMatriz(Vector<Vector<MateriaHistorico>> mat){
    // contar quantas materias tem na matriz
    int amountMat = 0;

    for(int i = 0; i < mat.size(); i++)
      amountMat += mat.get(i).size();

    String[][] res = new String[amountMat][numeroCampos];

    // Marca qual posição deve ser alterada
    int toChange = 0;

    for(int i = 0; i < mat.size(); i++){
      for(int j = 0; j < mat.get(i).size(); j++){
        res[toChange] = fromMateriaToString(mat.get(i).get(j), i+1);
        toChange++;
      }
    }

    return res;
  }

  // Função a ser executada quando aperta botão
  @Override
  public void actionPerformed(ActionEvent e){
    if (e.getSource() == bMenu){
      inicio = TelaInicial.getInstance();
      inicio.setLocationRelativeTo(this);
      inicio.setVisible(true);
      inicio.setBounds(this.getBounds());
      this.setVisible(false);
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
