package priv.demo.mq.customer.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import priv.demo.mq.common.pojo.Constant;
import priv.demo.mq.common.pojo.Msg;

@Component
public class Receiver {

  /**
   * 同名队列下，多消费者情况下，同一条消息只有消费节点中的一个收到。
   */
  @RabbitListener(queues = Constant.Q_DIRECT)
  @RabbitHandler
  public void directReceiver(final Msg msg) {
    System.out.println("Direct Receiver:" + msg.toString());
    try {
      Thread.sleep(3000);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @RabbitListener(queues = Constant.Q_TOPIC_MESSAGE)
  @RabbitHandler
  public void topicReceiver1(final Msg msg) {
    System.out.println("Topic Message Receiver:" + msg.toString());
  }

  @RabbitListener(queues = Constant.Q_TOPIC_MESSAGE_ALL)
  @RabbitHandler
  public void topicReceiver2(final Msg msg) {
    System.out.println("Topic Message All Receiver:" + msg.toString());
  }

  @RabbitListener(queues = Constant.Q_FANOUT_A)
  @RabbitHandler
  public void fanoutReceiverA(final Msg msg) {
    System.out.println("FanoutReceiverA  : " + msg.toString());
  }

  @RabbitListener(queues = Constant.Q_FANOUT_B)
  @RabbitHandler
  public void fanoutReceiverB(final Msg msg) {
    System.out.println("FanoutReceiverB  : " + msg.toString());
  }

  @RabbitListener(queues = Constant.Q_FANOUT_C)
  @RabbitHandler
  public void fanoutReceiverC(final Msg msg) {
    System.out.println("FanoutReceiverC  : " + msg.toString());
  }
}