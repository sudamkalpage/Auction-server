import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ServerView extends JPanel implements ActionListener {

	HashMap<String, Item> server_system;
	static JFrame frame;
	static DefaultTableModel model1;
	static DefaultTableModel model2;
	private static String[] showing_symbols = { "FB", "VRTU", "MSFT", "GOOGL", "YHOO", "XLNX", "TSLA", "TXN" };
	Timer timer;  

	ServerView(HashMap<String, Item> server_system) throws Exception {
		this.server_system = server_system;
		frame = new JFrame("Server GUI");
		frame.setSize(1100, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		JPanel panel1 = new JPanel();

		// text label "Popular Items"
		JLabel l1 = new JLabel("Popular Items");
		panel1.add(l1);
		
		// popular items table
		JTable table1;
		model1 = new DefaultTableModel();
		table1 = new JTable(model1);
		model1.addColumn("Symbol");
		model1.addColumn("Price");
		model1.addColumn("Security Name");
		try {
			for (int i = 0; i < showing_symbols.length; i++)
				model1.addRow(new Object[] { showing_symbols[i],Double.toString(server_system.get(showing_symbols[i]).getPrice()),server_system.get(showing_symbols[i]).getSecurity_name() });
		} catch (NullPointerException e) {};
		table1.setBackground(Color.YELLOW);
		table1.getTableHeader().setFont(new Font(Font.SERIF,Font.BOLD,20));
		table1.setFont(new Font("Serif", Font.BOLD, 12));
	    table1.setRowHeight(50);
	    table1.getColumnModel().getColumn(0).setPreferredWidth(30);
	    table1.getColumnModel().getColumn(1).setPreferredWidth(20);
	    table1.getColumnModel().getColumn(2).setPreferredWidth(170);
		JScrollPane scroll1 = new JScrollPane();
		scroll1.setViewportView(table1);
		panel1.add(scroll1);

		// text label "bid log"
		JLabel l2 = new JLabel("Bid log");
		panel1.add(l2);

		// log table
		JTable table2;
		model2 = new DefaultTableModel();
		table2 = new JTable(model2);
		model2.addColumn("Symbol");
		model2.addColumn("Biddng Price");
		model2.addColumn("Client Name");
		model2.addColumn("Date and Time");
		table2.setBackground(Color.pink);
		table2.getTableHeader().setFont(new Font(Font.SERIF,Font.BOLD,12));	
	    table2.getColumnModel().getColumn(0).setPreferredWidth(10);
	    table2.getColumnModel().getColumn(1).setPreferredWidth(10);
	    table2.getColumnModel().getColumn(2).setPreferredWidth(20);
	    table2.getColumnModel().getColumn(3).setPreferredWidth(120);
		
		JScrollPane scroll2 = new JScrollPane();
		scroll2.setViewportView(table2);
		panel1.add(scroll2);

		// full frame
		frame.add(panel1);
		frame.setVisible(true);
		
		timer = new Timer(500,this); 
        timer.start(); 
		
	}

	// change GUI values after a bid
	public static void UpdateLog(String symbol, double new_price, String client_name) {
		String date_time = new SimpleDateFormat("EEE, MMM d, yyyy 'at' h:mm a").format(Calendar.getInstance().getTime()); 
		ServerView.model2.addRow(new Object[] { symbol, new_price, client_name,date_time });
		int index = -1;
		for (int i = 0; i < showing_symbols.length; i++) {
			if (symbol.equals(showing_symbols[i])) {
				index = i;
				ServerView.model1.removeRow(index);
				ServerView.model1.addRow((new Object[] { symbol, ServerModel.map.get(symbol).getPrice(),
						ServerModel.map.get(symbol).getSecurity_name() }));
				
				//change symbols array according to new update
				int len = showing_symbols.length-1;
				for (int j = index; j < len; j++) {		
					showing_symbols[j]=showing_symbols[j+1];
				}
				showing_symbols[showing_symbols.length-1]=symbol;
				break;
			}
		}
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {}
}
