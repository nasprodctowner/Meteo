package meteo;

import meteo.Entity.Meteo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@MessageDriven(mappedName = "uneBALMeteo")
public class OTBean implements MessageListener {

    public OTBean() {
    }

    @PersistenceContext(unitName="meteoPU")
    private EntityManager em;

    @Resource(mappedName="UneConnectionFactoryMeteoOT")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName="uneBALOT")
    private Queue queue = null;

    private Session session = null;
    private MessageProducer messageProducer = null;
    private Connection connection = null;

    @PostConstruct
    public void onPostConstruct() {
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

    @PreDestroy
    public void onPreDestroy(){
        try {
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void onMessage(Message inMessage) {

        TextMessage msg;

        try {
            if (inMessage instanceof TextMessage) {
                msg = (TextMessage) inMessage;
                System.out.println("Meteo d'aujourd'hui : " + msg.getText());

                messageProducer.send(msg);
                Meteo meteo = new Meteo();
                meteo.setMeteoAjd(msg.getText());
                em.persist(meteo);



            } else {
                System.out.println(
                        "Message of wrong type: "
                        + inMessage.getClass().getName());
            }
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }
}
