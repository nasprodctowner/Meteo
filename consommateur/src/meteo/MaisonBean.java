package meteo;

import javax.ejb.*;
import javax.jms.*;

@MessageDriven(mappedName = "uneBALOT")
public class MaisonBean implements MessageListener {

    public MaisonBean() {
    }

    public void onMessage(Message inMessage) {
        TextMessage msg = null;

        try {
            if (inMessage instanceof TextMessage) {
                msg = (TextMessage) inMessage;
                System.out.println("Message recu Maison : " + msg.getText());
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
