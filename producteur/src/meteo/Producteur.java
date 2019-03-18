package meteo;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;


public class Producteur {

    public static void main(String[] args) {
    ConnectionFactory connectionFactory=null;
    Queue queue=null;
    
        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;
        TextMessage message = null;        
        final int NUM_MSGS = 3;

        try {
				InitialContext ctx = new InitialContext();		
				connectionFactory = (ConnectionFactory) ctx.lookup("UneConnectionFactoryMete");
				queue = (Queue) ctx.lookup("uneBALMeteo");
			}
		catch (Exception ex) {
				System.err.println("erreur dans le lookup");
				ex.printStackTrace();
			}

		Scanner scanner = new Scanner(System.in);
        try {
				connection = connectionFactory.createConnection();
				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				messageProducer = session.createProducer(queue);
				message = session.createTextMessage();

			int reponse = 'O';

				while(reponse != 0) {
					System.out.println("Quel message ? 1 2 3 4 et 0 pour quitter");

					reponse = scanner.nextInt();
					scanner.nextLine();

					switch (reponse){
						case 1 :
							message.setText("erreur au lancement du serveur JavaEE");
							System.out.println("Sending log: " + message.getText());
							messageProducer.send(message);
							break;
						case 2 :
							message.setText("erreur lors du déploiement d'un EJB");
							System.out.println("Sending log: " + message.getText());
							messageProducer.send(message);
							break;
						case 3 :
							message.setText("erreur lors de la recherche d'un nom dans JNDI");
							System.out.println("Sending log: " + message.getText());
							messageProducer.send(message);
							break;
						case 4 :
							message.setText("erreur à l'arrêt du serveur JavaEE");
							System.out.println("Sending log: " + message.getText());
							messageProducer.send(message);
							break;
					}
				}
			}

			catch (JMSException e) {
					System.out.println("Exception occurred: " + e.toString());
				}
    } // main
} // class
