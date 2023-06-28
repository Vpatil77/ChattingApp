
package chatting.application;


import static chatting.application.Server.dout;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Client implements ActionListener{
        JTextField  text;
       static JPanel a1;
       static Box vertical = Box.createVerticalBox();
       static DataOutputStream dout;       
       static JFrame f = new JFrame();
       
    Client(){
        
        f.setLayout(null);
        
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
        f.add(p1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5,20,25,25);      
        p1.add(back);
        
          back.addMouseListener(new MouseAdapter(){
              
              public void mouseClicked(MouseEvent me){
                  System.exit(0);
              }
              
          });
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40,10,50,50);
        p1.add(profile);
        
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300,20,30,30);
        p1.add(video);
        
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel telephone = new JLabel(i12);
        telephone.setBounds(360,20,35,30);
        p1.add(telephone);
        
         ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel morevert = new JLabel(i15);
        morevert.setBounds(410,20,10,25);
        p1.add(morevert);
        
        JLabel name = new JLabel("Pappa");
        name.setForeground(Color.WHITE);
        name.setBounds(110,15,100,30);
        name.setFont(new Font("SAN_SARIF",Font.BOLD,20));
        p1.add(name);
        
        JLabel status = new JLabel("Online");
        status.setForeground(Color.WHITE);
        status.setBounds(110,45,100,18);
        status.setFont(new Font("SAN_SARIF",Font.PLAIN,15));
        p1.add(status);
    
       a1 =new JPanel();
       a1.setBounds(0,70,450,585);
       f.add(a1);
        
       text = new JTextField();
       text.setBounds(5,655,310,40);
       text.setFont(new Font("SAN_SARIF",Font.PLAIN,16));
       f.add(text);
       
       JButton send = new JButton("Send");
       send.setBackground(new Color(7,94,84));
       send.setForeground(Color.WHITE);
       send.setFont(new Font("SAN_SARIF",Font.PLAIN,16));
       send.setBounds(320,655,123,40);
       send.addActionListener(this);
       f.add(send);
       
        f.setSize(450,700);
        f.setLocation(800,50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae){
        
        try{
       String out = text.getText();

       
       JPanel p2 = formatLabel(out);
       
       
       a1.setLayout(new BorderLayout());
       
       JPanel right = new JPanel(new BorderLayout());
       right.add(p2,BorderLayout.LINE_END);
       vertical.add(right);
       vertical.add(Box.createVerticalStrut(15));
       
       a1.add(vertical,BorderLayout.PAGE_START);
       
       dout.writeUTF(out);
       
       text.setText("");
       
       f.repaint();
       f.invalidate();
       f.validate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
   
    public static JPanel formatLabel(String out){
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel("<html><p style =\"width: 150px\">" + out +"</p></html>");
        output.setFont(new Font("SAN_SARIF",Font.PLAIN,20));
        output.setBackground(new Color(0,255,51));      
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        
        panel.add(output);
        
        
        Calendar cal = Calendar.getInstance();
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        
        return panel;
    }
 
    public static void main(String args[]) {
        // TODO code application logic here
        new Client();
        
        try{
            Socket s = new Socket("127.0.0.3",6003);
            DataInputStream din = new DataInputStream(s.getInputStream());
               dout = new  DataOutputStream(s.getOutputStream());
               
               while(true){
                   a1.setLayout(new BorderLayout());
                   String msg = din.readUTF();
                   JPanel panel = formatLabel(msg);
                   
                   JPanel left = new JPanel(new BorderLayout());
                   left.add(panel,BorderLayout.LINE_START);
                   vertical.add(left);
                   
                   vertical.add(Box.createVerticalStrut(15));
                   a1.add(vertical,BorderLayout.PAGE_START);
                   
                   f.validate();
               }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
