package processscheduller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AboutPage extends JFrame implements ActionListener
{
    JPanel panel;
    JTextArea Area1, Area2,Area3;
    Color C= new Color(216, 229, 242);
    Color c= new Color(121, 166, 210);
    JButton Gstarted;
   
    Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Acer\\Documents\\NetBeansProjects\\ProcessScheduller\\src\\processscheduller\\icons\\final2.jpg");
    Image img2 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Acer\\Documents\\NetBeansProjects\\ProcessScheduller\\src\\processscheduller\\icons\\final2 (1).jpg");
    public AboutPage() throws IOException 
   {
       super("ABOUT");
      this.setContentPane(panel= new JPanel()
      {
         @Override
         public void paintComponent(Graphics g) 
         {
            super.paintComponent(g);
            g.drawImage(img, 20, 120, 400, 300, rootPane);
            g.drawImage(img2, 570, 450, 450, 450, rootPane);
         }
      });
      
      
      Area3= new JTextArea("E-PROCESS SCHEDULER");
      Area3.setBounds(290, 10, 500, 45);
      Area3.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 40));
      Area3.setBackground(C);
      
      Area1= new JTextArea("Scheduling is the activity of determining which \nservice request should be handled next by \nthe server.A scheduling algorithm " +
                            "specifies criteria \nfor use during scheduling. Scheduling is \nimportant because it influences user service and \nthe efficiency of CPU" +
                            "utilization. This application \ndemonstrates the implementation of these policies \n to perform job and process scheduling in an \n" +
                            "operating system.");
      Area1.setBounds(460, 125, 570, 300);
      Area1.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 27));
      Area1.setBackground(C);
      
      Area2=new JTextArea("CPU Scheduling Algorithms\n \n" +"E-Process scheduler will help in calculating the \nwaiting time and turnaround time along with the \n pictorial view of gantt chart.\n \n The five algorithms used are: \n"+
"• First come, first served (FCFS)\n" +
"• Shortest Job First (SJF) \n"+
"• Shortest Remaining Time First (SRTF) \n" +
"• Round Robin.\n" +
"• Priority Scheduling \n");
     Area2.setBounds(30, 450, 570, 600);
      Area2.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 27));
      Area2.setBackground(C);
      
      Gstarted= new JButton("Get Started");
      Gstarted.setBounds(40, 870, 250, 30);
      Gstarted.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));
      Gstarted.setBackground(c);
      pack();
      panel.setLayout(null);
      setVisible(true);
      panel.setBackground(C);
      panel.add(Area3);
      panel.add(Gstarted);
      panel.add(Area1);
      panel.add(Area2);
      Gstarted.addActionListener(this);
   }
   
   public static void main(String[] args) throws Exception 
   {
      AboutPage a= new AboutPage();
      a.setSize(1050, 1000);
      a.setVisible(true);
      a.setResizable(false);
      a.setLocation(500,20);
   }
   
   public void actionPerformed(ActionEvent e) 
    {
        Object obj=e.getSource();
        if(obj==Gstarted)
        {
            dispose();   
            ResultPage r= new ResultPage();
            r.setSize(550, 650);
            r.setVisible(true);
            r.setLocation(600,250);
        }
    }
   
}