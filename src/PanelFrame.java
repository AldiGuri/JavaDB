import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class PanelFrame extends JFrame
{
    private JTextArea input; // demo string
    private JButton showList; // inicializon kopjimin e string
    private JButton addToList;
    private JPanel buttonJPanel;
    
    //DB CONNECTION INFO
    public String DB_URL = "jdbc:mysql://localhost:3306/?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	public String USER = "root";
	public String PASS = "aldi1234";
	DB db = new DB(DB_URL, USER, PASS); 

    public PanelFrame()
    {
        super( "President USA" );
        String filePath="PresidentUsa.txt";
        buttonJPanel = new JPanel();
        buttonJPanel.setLayout( new GridLayout( 1, 5 ) );
        File_IO IO = new File_IO();
        
        ///
        input = new JTextArea(30, 30 );

        showList = new JButton( "Lexo Presidentet" );

        showList.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed( ActionEvent event )
                    {
                        input.setText( db.getPresident() );
                    }
                }
        );
        addToList = new JButton( "Shto President" );

        addToList.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed( ActionEvent event )
                    {
                    	createFrame();
                    }
                }
        );
        buttonJPanel.add( showList );
        add( new JScrollPane( input ) );
        buttonJPanel.add( addToList );
        add( buttonJPanel, BorderLayout.SOUTH );
        
        // Ben qe me mbylljen e applikacionit te shkeputet lidhja me db
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent e) {
                e.getWindow().dispose();
                db.CloseCon();
            }
        });
        
    }
    public void createFrame()
    {
                JFrame frame = new JFrame("DB input");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try 
                {
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                   e.printStackTrace();
                }
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
               
                JPanel inputpanel = new JPanel();

                JTextField input = new JTextField("President Name",15);
                JTextField input2 = new JTextField("Party",15);
                JTextField input3 = new JTextField("State",15);
                JTextField input4 = new JTextField("Birth Year",15);
                JTextField input5 = new JTextField("Years Served",15);
                JTextField input6 = new JTextField("Death Age",15);
                
                JButton button = new JButton("Enter");
                
                button.addActionListener(
                        new ActionListener()
                        {
                            public void actionPerformed( ActionEvent event )
                            {
                            	String Pres_Name = input.getText();
                            	String Party = input2.getText();
                            	String State = input3.getText();
                            	String Birth_Y = input4.getText();
                            	String Years_S = input5.getText();
                            	String Death = input6.getText();
                            	
                            	
								db.WriteToDB(Pres_Name,Party,State,Birth_Y,Years_S,Death);
                            }
                        }
                );
               

                inputpanel.add(input);
                inputpanel.add(input2);
                inputpanel.add(input3);
                inputpanel.add(input4);
                inputpanel.add(input5);
                inputpanel.add(input6);
                
                inputpanel.add(button);
                panel.add(inputpanel);
                
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                
                frame.pack();
                
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                
                frame.setResizable(true);
                

            
        
    }
    
}