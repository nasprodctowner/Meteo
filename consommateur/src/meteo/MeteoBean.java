package meteo;

import javax.ejb.*;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@MessageDriven(mappedName = "uneBALMeteo")
public class MeteoBean implements MessageListener {

    public MeteoBean() {
    }

    @PersistenceContext(unitName="meteoPU")
    private EntityManager em;

    public void onMessage(Message inMessage) {
        TextMessage msg = null;

        try {
            if (inMessage instanceof TextMessage) {
                msg = (TextMessage) inMessage;
                System.out.println("Message recu: " + msg.getText());

            } else {
                System.out.println(
                        "Message of wrong type: "
                        + inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }
}
