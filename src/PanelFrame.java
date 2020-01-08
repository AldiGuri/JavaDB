import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PanelFrame extends JFrame
{
    private JTextArea textRead; // demo string
    private JButton showList; // inicializon kopjimin e string
    private JButton addToList;
    private JPanel buttonJPanel;


    public PanelFrame()
    {
        super( "President USA" );

        String filePath="PresidentUsa.txt";
        buttonJPanel = new JPanel();
        buttonJPanel.setLayout( new GridLayout( 1, 5 ) );

        File_IO IO = new File_IO();
        ///

        String DB_URL = "jdbc:mysql://localhost:3306/?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    	String USER = "root";
    	String PASS = "aldi1234";

    	
    	DB db = new DB(DB_URL, USER, PASS);
        
        ///
        textRead = new JTextArea(30, 30 );


        showList = new JButton( "Lexo Presidentet" );

        showList.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed( ActionEvent event )
                    {
                        textRead.setText( db.getPresident() );
                    }
                }
        );
        addToList = new JButton( "Shto President" );



        addToList.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed( ActionEvent event )
                    {
                        String text = textRead.getText();
                        IO.AppendToFile( text,filePath);
                    }
                }
        );
        buttonJPanel.add( showList );
        add( new JScrollPane( textRead ) );
        buttonJPanel.add( addToList );

        add( buttonJPanel, BorderLayout.SOUTH );
    }
}