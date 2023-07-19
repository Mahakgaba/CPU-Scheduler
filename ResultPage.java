package processscheduller;

import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.table.JTableHeader;


public class ResultPage extends JFrame implements ActionListener, WindowListener{
    
    
    JPanel slider_panel;
    JLabel lbl_input,lbl_AWT,lbl_ATAT;
    JTextField txt_quantum,txt_speed,txt_AWT,txt_ATAT;
    JButton btn_finish,btn_export,btn_add,btn_remove,btn_compute;
    JTable table;
    JTextArea ganttchart;
    DefaultTableModel model;
    JScrollPane tablePane,chartPane;
    CustomPanel chartPanel;   
    JLabel lbl_algo,wtLabel,wtResultLabel,tatLabel,tatResultLabel,lbl_file;
    JButton btn_import;
    public JComboBox<String> c_algo,c_file;
    
    int algorithm=2;
    public ResultPage()
    {
        super("E-process Scheduller");
       //Labels
        
        lbl_input= new JLabel("Input");    
        lbl_input.setBounds(10,25,150,20);
        lbl_input.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));

        lbl_AWT= new JLabel("Average Waiting Time");        
        lbl_AWT.setBounds(10,370,300,25);
        lbl_AWT.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
        
        lbl_ATAT= new JLabel("Average TurnAround Time");        
        lbl_ATAT.setBounds(10,420,300,22);
        lbl_ATAT.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
     
        
        
        //TextFields
       
        txt_AWT=new JTextField(20);
        txt_AWT.setBounds(310,370,80,20);
        txt_AWT.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
        
        txt_ATAT=new JTextField(20);
        txt_ATAT.setBounds(310,420,80,20);
        txt_ATAT.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
      
       //gantt chart
       
        chartPanel = new CustomPanel();     
        chartPanel.setBackground(Color.WHITE);
        chartPane = new JScrollPane(chartPanel);
        chartPane.setBounds(10,450,500,100);
        chartPanel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
        chartPane.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
        
       
       //table
        model = new DefaultTableModel(new String[]{"Process", "AT", "BT", "Priority", "WT", "TAT"}, 0);
        table = new JTable(model);
        table.setBounds(10,50,470,200); 
        table.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 15));
        table.setOpaque(true);
        table.setFillsViewportHeight(true);
        table.setBackground(Color.WHITE);
        tablePane = new JScrollPane(table);      
        tablePane.setBounds(10,50,500,200);
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
        tableHeader.setBackground(Color.WHITE);
        
        //buttons
        btn_export= new JButton("Export");
        btn_export.setBounds(165,565,100,20);
        btn_export.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
        
        btn_finish= new JButton("Finish");
        btn_finish.setBounds(275,565,100,20);
        btn_finish.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
        
        btn_add = new JButton("Add");            
        btn_add.setBounds(10,320,100,20);
        btn_add.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
        btn_add.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addRow(new String[]{"", "", "", "", "", ""});
            } 
        });      
                   
        btn_import= new JButton("Import");
        btn_import.setBounds(340,320,100,20);
        btn_import.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
        
        btn_remove = new JButton("Remove");
        btn_remove.setBounds(120,320,100,20); 
        btn_remove.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
        btn_remove.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                
                if (row > -1) {
                    model.removeRow(row);
                }
            }
        });
        btn_compute = new JButton("Compute");            
        btn_compute.setBounds(230,320,100,20);
        btn_compute.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
          
       
        //drop down
        lbl_algo= new JLabel("Algorithm");
        lbl_algo.setBounds(10,270,100,22);
        lbl_algo.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
 
        
        String[] optionsToChoose = {"FCFS", "SJF", "SRTF", "Priority", "RR"};      
        c_algo = new JComboBox<>(optionsToChoose);
        c_algo.setBounds(110,270,100,20);
        c_algo.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
                
     
       
        slider_panel=new JPanel();
        add(slider_panel);
        
        //adding labels to panel 
        
        slider_panel.add(lbl_input);        
        slider_panel.add(lbl_AWT);        
        slider_panel.add(lbl_ATAT);        
            
        //adding textfields to panel 
        
        slider_panel.add(txt_AWT);        
        slider_panel.add(txt_ATAT);
        slider_panel.add(lbl_AWT);         
        slider_panel.add(lbl_algo);
        slider_panel.add(c_algo);
        
        
        //adding buttons to panel
        slider_panel.add(btn_export);
        slider_panel.add(btn_finish);
        slider_panel.add(btn_add);
        slider_panel.add(btn_remove);
        slider_panel.add(btn_compute);
        slider_panel.add(btn_import);
        
        //adding textarea to panel
        slider_panel.add(chartPane);
        //adding table to panel
       
        slider_panel.add(tablePane);
        slider_panel.setLayout(null);
        slider_panel.setSize(350, 250);
        slider_panel.setVisible(true);
        Color C= new Color(216, 229, 242);
        Color c= new Color(121, 166, 210);
        slider_panel.setBackground(C);
        btn_compute.setBackground(c);
        btn_import.setBackground(c);
        btn_export.setBackground(c);
        btn_finish.setBackground(c);
        btn_add.setBackground(c);
        btn_remove.setBackground(c);
        
        btn_compute.addActionListener(this);      
        btn_import.addActionListener(this);  
        btn_export.addActionListener(this);      
        btn_finish.addActionListener(this);  
        
        addWindowListener(this);
    }

    public static void main(String[] args)
    {
       ResultPage l=new ResultPage();
        l.setSize(550,650);
        l.setVisible(true);
        l.setLocation(600,250);
       l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       l.setResizable(false);

    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    {
        Object obj=e.getSource();
  if(btn_import==obj)
         {    
              JFileChooser file_upload= new JFileChooser();
                    
                    int res= file_upload.showOpenDialog(null);
                    
                    if(res== JFileChooser.APPROVE_OPTION)
                    {
                        File file_path = new File(file_upload.getSelectedFile().getAbsolutePath());
                        
                        File file = new File(file_path.toString());
                        
                        try {
                            FileReader fr = new FileReader(file);
                            BufferedReader br = new BufferedReader(fr);
                            
                                                       
                            Object[] lines = br.lines().toArray();
                            
                            for(int i=0; i< lines.length; i++)
                            {
                                String[] row= lines[i].toString().split(" ");
                                model.addRow(row);
                            }
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(ResultPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
         }
         if(btn_compute==obj)
         {    
           
        
        String selected = (String) c_algo.getSelectedItem();
                CPUScheduler scheduler;
                
                switch (selected) {
                    case "FCFS":
                        scheduler = new FirstComeFirstServe();
                        break;
                    case "SJF":
                        scheduler = new ShortestJobFirst();
                        break;
                    case "SRTF":
                        scheduler = new ShortestRemainingTime();
                        break;
                    case "Priority":
                        scheduler = new PriorityPreemptive();
                        break;
                    case "RR":
                        String tq = JOptionPane.showInputDialog("Time Quantum");
                        
                        if (tq == null) {
                            return;
                        }
                        scheduler = new RoundRobin();
                        scheduler.setTimeQuantum(Integer.parseInt(tq)); 
                        break;
                    default:
                        return;
                }
                
                for (int i = 0; i < model.getRowCount(); i++)
                {
                    String process = (String) model.getValueAt(i, 0);
                    int at = Integer.parseInt((String) model.getValueAt(i, 1));
                    int bt = Integer.parseInt((String) model.getValueAt(i, 2));
                    int pl;                  
                    pl = 1;         
                    scheduler.add(new Row(process, at, bt, pl));
                }
                
                scheduler.process();
                
                for (int i = 0; i < model.getRowCount(); i++)
                {
                    String process = (String) model.getValueAt(i, 0);
                    Row row = scheduler.getRow(process);
                    model.setValueAt(row.getWaitingTime(), i, 4);
                    model.setValueAt(row.getTurnaroundTime(), i, 5);
                }
                double b,c;
                b=scheduler.getAverageWaitingTime();
                b=Math.round(b*100.0)/100.0;
                c=scheduler.getAverageTurnAroundTime();
                c=Math.round(c*100.0)/100.0;
                
                txt_AWT.setText(Double.toString(b));
                txt_ATAT.setText(Double.toString(c));
              
                
                chartPanel.setTimeline(scheduler.getTimeline());
           }
    
     if(btn_export==obj)
         {    
           
           JFileChooser file_upload= new JFileChooser();
                    
                    int res= file_upload.showOpenDialog(null);
                    
                    if(res== JFileChooser.APPROVE_OPTION)
                    {
                        File file_path = new File(file_upload.getSelectedFile().getAbsolutePath());
                        
                        File file = new File(file_path.toString());
        
        try {
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            
           
            bw.write("Process"+"   "+"AT"+"        "+"BT"+"        "+"Priority"+"    "+"WT"+"        "+ "TAT");
            bw.newLine();   
            
            for(int i = 0; i < table.getRowCount(); i++){//rows
                for(int j = 0; j < table.getColumnCount(); j++){//columns
                    bw.write(table.getValueAt(i, j).toString()+"         ");
                }
                bw.newLine();               
               
            }
            bw.newLine();  bw.newLine();  
                bw.write(lbl_AWT.getText()+"=");
                bw.write(txt_AWT.getText());
                bw.newLine();
                bw.write(lbl_ATAT.getText()+"=");
                bw.write(txt_ATAT.getText());bw.newLine();
            
            
            
            bw.close();
            fw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ResultPage.class.getName()).log(Level.SEVERE, null, ex);
        }
           }

           
}       
     if(btn_finish==obj)
         {    
             dispose();    
             FeedbackForm f=new FeedbackForm();
             f.setSize(500,500);
             f.setVisible(true);
             f.setLocation(600,250);
             f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             f.setResizable(false);
         }

}
    }
    
        
class CustomPanel extends JPanel
    {   
        private java.util.List<Event> timeline;
        
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            if (timeline != null)
            {

                
                for (int i = 0; i < timeline.size(); i++)
                {
                    Event event = timeline.get(i);
                    int x = 30 * (i + 1);
                    int y = 20;
                    
                    g.drawRect(x, y, 30, 30);
                    g.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    g.drawString(event.getProcessName(), x + 10, y + 20);
                    g.setFont(new Font("Segoe UI", Font.PLAIN, 11));
                    g.drawString(Integer.toString(event.getStartTime()), x - 5, y + 45);
                    
                    if (i == timeline.size() - 1)
                    {
                        g.drawString(Integer.toString(event.getFinishTime()), x + 27, y + 45);
                    }
                    
                }
            }
        }
        
        public void setTimeline(java.util.List<Event> timeline)
        {
            this.timeline = timeline;
            repaint();
        }
    
    }
     
    @Override
    public void windowOpened(WindowEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
       System.exit(0); //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
