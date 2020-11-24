import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
//import javax.swing.Timer;

public class Client implements Runnable{
	
	private Socket client_socket;  
	//private Timer timer;
	private String client_name;
	private String symbol;
	private double bid_price ;
	private double current_price ;
	private String line; 	
	
	public Client( Socket socket) {
		client_socket = socket;
        //timer = new Timer(500, this);   //Create Timer and assign current ActionListener
        //timer.start();                  //Start timer
    }
	
	public void run(){
		try {
			//input stream
            BufferedReader in = new BufferedReader(new InputStreamReader(this.client_socket.getInputStream()));   
            //output stream
            PrintWriter out = new PrintWriter(new OutputStreamWriter(this.client_socket.getOutputStream()));      
		    
		    out.print("Welcome to the bid platform! *************************************** \n");
            out.print("Enter 'quit' at anytime to top this program\n\n");
            out.print("Enter Client Name : ");
            out.flush();
            
            //get client name
            line = in.readLine();
            client_name = line;
            out.print("Hello "+client_name+"!\n"+"Enter the Symbol of Bidding item : ");
            out.flush();
            
            //get bidding item symbol
            line = in.readLine();
            symbol  = line;
            while( !ServerModel.map.containsKey(symbol)) {
            	out.print("-1\n");
                out.print(symbol+" is not available in the stock. please enter a available symbol.(for example try :GOOGL)\n");
                out.print("Enter the Symbol of Bidding item : ");           
                out.flush();
            	line = in.readLine();
            	symbol  = line;
            }
            
            //show current price
            while(line != null) {
            	current_price = ServerModel.map.get(symbol).getPrice();
                out.print("Current price of " +symbol + " is "  +current_price +"\n");
                out.print("Enter your Bid: ");
                out.flush();
                break;
            }
            
            //get bidding value
            line = in.readLine();
            while(!line.equalsIgnoreCase("quit")) {
                try {                           
                	bid_price = Double.parseDouble(line);   
                }catch (NumberFormatException e) {     
                    out.println("Invalid Format. Bidding value should be a double value\n");
                    out.flush();
                }
                                
                if( bid_price > ServerModel.map.get(symbol).getPrice()){
                	ServerModel.newLog(symbol, bid_price,client_name);
                    out.print("You placed a bid of " + bid_price + " on "+ symbol + "\n");
                    out.print("Now updated price of " + symbol + "is "+ ServerModel.map.get(symbol).getPrice()+"\n");
                }
                else{ 
                    out.print("you bid should be greater than the current price of " +  ServerModel.map.get(symbol).getPrice() + " on "+ symbol + "." );
                }
                out.print("\nEnter your new bid on "+ symbol +": ");
                out.flush();
                line = in.readLine();
            }
            out.print("Thank you for your contribution!\n " );
            out.print("please press enter twice to quit\n " );
            out.flush();           
            out.close();
            in.close();
            this.client_socket.close();
        }catch (IOException e) { 
            System.out.println(e); 
        }catch( NumberFormatException e){
            System.out.println(e);
                     
		}finally { 	    
            try{
                this.client_socket.close(); 
            }catch(Exception e){}	   
        }
   }          
}
