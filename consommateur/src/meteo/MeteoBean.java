package meteo;

import meteo.Entity.Meteo;

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

    private Meteo meteo = new Meteo();

    public void onMessage(Message inMessage) {
        TextMessage msg = null;

        try {
            if (inMessage instanceof TextMessage) {
                msg = (TextMessage) inMessage;
                System.out.println("Meteo d'aujourd'hui : " + msg.getText());

                meteo.setMeteoAjd(msg.getText());
                em.persist(meteo);

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
