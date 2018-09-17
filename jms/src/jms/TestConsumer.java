package jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/*
 * This class will connect to ActiveMQ and consume a destination named fila.financeiro
 * 
 */
public class TestConsumer {

	public static void main(String[] args) throws NamingException, JMSException {
		
		// create a context. it will read from jndi.properties
		InitialContext context = new InitialContext();
		
		// create a connection factory and a connection to the mom
		ConnectionFactory cf = (ConnectionFactory)context.lookup("ConnectionFactory");
		Connection con = cf.createConnection();
		
		// start the connection to the mom
		con.start();
		
		// create a session. it handles ack and transaction
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		// destination is where the messages are. the jndi name is defined in the jndi.properties
		Destination fila = (Destination) context.lookup("financeiro");
		MessageConsumer consumer = session.createConsumer(fila);
		
		// set a queue listener
		consumer.setMessageListener(new MessageListener() {

			// on message received, this method will execute
			@Override
			public void onMessage(Message message) {
				// cast from message to text and print
				TextMessage txtmsg = (TextMessage) message;
				try {
					System.out.println("received msg: " + txtmsg.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		// use this scanner to stop the runtime here
		System.out.println("Press enter to stop...");
		new Scanner(System.in).nextLine();
		System.out.println("Stopping the Message consumer...");
		
		// close the open objects
		session.close();
		con.close();    
		context.close();
	}

}