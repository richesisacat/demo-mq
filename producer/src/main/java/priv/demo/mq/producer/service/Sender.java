package priv.demo.mq.producer.service;

import org.joda.time.DateTime;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.demo.mq.common.pojo.Constant;
import priv.demo.mq.common.pojo.Msg;

@Service
public class Sender {

  @Autowired
  private AmqpTemplate rabbitTemplate;

  public void directSend(final String data) {
    final Msg msg = Msg.builder().data(data).sendTime(new DateTime()).build();
    System.out.println("Sender:" + msg.toString());
    rabbitTemplate.convertAndSend(Constant.Q_DIRECT, msg);
  }

  public void topicSendMessage(final String data) {
    final String context = " -- from RoutingKey : topic.message";
    final Msg msg = Msg.builder().data(data + context).sendTime(new DateTime()).build();
    System.out.println("Sender : " + msg.toString());
    // exchangeName routingKey msg
    rabbitTemplate.convertAndSend(Constant.TOPIC_EX, "topic.message", msg);
  }

  public void topicSendAbc(final String data) {
    final String context = " -- from RoutingKey : topic.abc";
    final Msg msg = Msg.builder().data(data + context).sendTime(new DateTime()).build();
    System.out.println("Sender : " + msg.toString());
    rabbitTemplate.convertAndSend(Constant.TOPIC_EX, "topic.abc", msg);
  }

  public void fanoutSend(final String data) {
    final String context = " -- from fanOut";
    final Msg msg = Msg.builder().data(data + context).sendTime(new DateTime()).build();
    System.out.println("Sender : " + msg.toString());
    rabbitTemplate.convertAndSend(Constant.FANOUT_EX,"不需要routingKey", msg);
  }
}
