package meteo;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;


public class Producteur {

    public static void main(String[] args) {
		ConnectionFactory connectionFactory=null;
		Topic topic=null;
        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;
        TextMessage message = null;

        try {
				InitialContext ctx = new InitialContext();		
				connectionFactory = (ConnectionFactory) ctx.lookup("UneConnectionFactoryMeteo");
				topic = (Topic) ctx.lookup("uneBALMeteo");
			}
		catch (Exception ex) {
				System.err.println("erreur dans le lookup");
				ex.printStackTrace();
			}

		Scanner scanner = new Scanner(System.in);
        try {
				connection = connectionFactory.createConnection();
				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				messageProducer = session.createProducer(topic);
				message = session.createTextMessage();

			int reponse = 'O';

				while(reponse != 0) {
					System.out.println("Quelle est la météo d'aujourd'hui ? 1 2 3 4 et 0 pour quitter");

					reponse = scanner.nextInt();
					scanner.nextLine();

					switch (reponse){
						case 1 :
							message.setText("Jeudi 21/2 – 7h – météo brumeuse, des nuages");
							System.out.println("Envoi Meteo: " + message.getText());
							messageProducer.send(message);
							break;
						case 2 :
							message.setText("Jeudi 21/2 – 10h15 – confirmation d’une météo morose");
							System.out.println("Sending log: " + message.getText());
							messageProducer.send(message);
							break;
						case 3 :
							message.setText("Jeudi 21/2 – 12h40 – un début d’éclaircie, le voile matinal se lève");
							System.out.println("Sending log: " + message.getText());
							messageProducer.send(message);
							break;
						case 4 :
							message.setText("Jeudi 21/2 – 13h05 – le soleil apparaît enfin !");
							System.out.println("Sending log: " + message.getText());
							messageProducer.send(message);
							break;
					}
				}
			connection.close();
			System.exit(0);
			}

			catch (JMSException e) {
					System.out.println("Exception occurred: " + e.toString());
				}
    } // main
} // class
