package view.guiElements;

import java.awt.Font;

import javax.swing.JTable;

public class Tabela extends JTable{
  private static final long serialVersionUID = 1L;

  public Tabela(String data[][], String colunas[], Font fonte){
    super(data, colunas);
    this.setFont(fonte);
  }

  public boolean isCellEditable(int row, int column) {                
    return false;               
  }
}
