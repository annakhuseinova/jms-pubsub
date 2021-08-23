package com.annakhuseinova;

import com.annakhuseinova.model.Employee;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.naming.InitialContext;

public class HRApp {

    public static void main(String[] args) throws Exception{
        InitialContext context = new InitialContext();
        Topic topic = (Topic) context.lookup("topic/empTopic");
        try(ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            JMSContext jmsContext = connectionFactory.createContext()){
            Employee employee = new Employee();
            employee.setId(123);
            employee.setFirstName("John");
            employee.setLastName("Dow");
            employee.setDesignation("Software engineer");
            employee.setEmail("whatever.com");
            employee.setPhone("123456");
            JMSProducer producer = jmsContext.createProducer();
            producer.send(topic, employee);
            System.out.println("Message Sent");
        }
    }
}
