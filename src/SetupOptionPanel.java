import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Edward Fominykh
//Program Description
//Feb 11, 2017
public class SetupOptionPanel extends JPanel
{

    private JLabel label;
    private JLabel secondJLabel;
    private JTextField textField;
    private String text;
    private JButton jb;
    private JCheckBox jcb;
    
    
    public SetupOptionPanel(Color cx, Color cy, String text){
    this.text= text;

    label = new JLabel("     "+text);
    label.setFont(JMessaging.getOpenSans3());
    textField = new JTextField();
    textField.setFont(JMessaging.getOpenSans3());
    textField.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, cx));
    textField.setBackground(cy);
//    textField.setEchoChar('*');
    this.setLayout(null);
    this.add(label);
    this.add(textField);
    this.setBackground(cy);
    textField.setBounds(20, this.getHeight()-60, 200, 30);
//    return subPanel;
    }


    public JPanel getPanel()
    {
        return this;
    }

    public void setTextFieldBounds(){
        textField.setBounds(20, this.getHeight()-50, 200, 30);
    }


    public JLabel getLabel()
    {
        return label;
    }


    public void setLabel(JLabel label)
    {
        this.label = label;
    }


    public JTextField getTextField()
    {
        return textField;
    }


    public void setTextField(JTextField textField)
    {
        this.textField = textField;
    }

    public void removeTextField()
    {
        this.remove(textField);
    }
    
    public void addButton(JButton jb){
        this.jb = jb;
        this.jb.setBackground(Color.white);
        this.add(this.jb);
    }

    public JButton getButton(){
        return jb;
    }
    
    public void addCheckbox(JCheckBox jcb, String message)
    {
        this.jcb = jcb;
        this.jcb.setOpaque(false);
        this.secondJLabel = new JLabel(message);
        this.secondJLabel.setFont(JMessaging.getOpenSans5());
        this.add(this.secondJLabel);
        this.add(this.jcb);
//        jcb.setBounds(10, this.getHeight()-60, 200, 30);
        jcb.setFont(JMessaging.getOpenSans3());
    }
    
    public void setCheckboxBounds(){
        this.jcb.setBounds(10, this.getHeight()-60, getWidth(), 30);
        this.secondJLabel.setBounds(35, this.getHeight()-60, getWidth(), 30);
        
    }
    
    public JCheckBox getCheckbox()
    {
        return jcb;
    }
    
    public void setButtonBounds(){
        jb.setBounds(20, this.getHeight()-50, 200, 30);
    }
    
    public String getInput()
    {
        return textField.getText();
    }
    
    public String getText()
    {
        return text;
    }

    public void setLabelBounds()
    {
        label.setBounds(0, this.getHeight()-87, getWidth(), 30);
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
}
