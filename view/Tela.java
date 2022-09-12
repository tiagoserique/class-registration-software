package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class Tela extends JFrame implements ActionListener{
  protected TelaSub subscriber;
  private String pedido;

  public void subscribe(TelaSub ts){
    this.subscriber = ts;
  }

  public void actionPerformed(ActionEvent e){
  }

  protected void updateSub(){
    if(this.subscriber != null)
      this.subscriber.update(this);
  }

  protected void setPedido(String p){ this.pedido = p; }
  public String getPedido() { return this.pedido; }
}
