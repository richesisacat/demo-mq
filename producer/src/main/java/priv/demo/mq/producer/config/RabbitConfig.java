package priv.demo.mq.producer.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import priv.demo.mq.common.pojo.Constant;

/**
 * 对于queue的说明
 * 1 无论是生产者还是消费者，都要声明Queue，这就产生一个问题：是生产者还是消费者声明呢？
 * 首先明确
 * a] 消费者无法订阅或获取不存在的Queue
 * b] 当生产者 ack false情况下没有匹配的Queue会被丢弃
 * 所以，
 * a] 如果能接受：消费者定义Queue前，生产者发出的消息被丢弃，或者有消息重发机制允许消息丢失，则 消费者声明Queue
 * b] 如果不能接受：则生产消费者[都]需要尝试建立消息队列。（如果一端尝试建立已经存在的Queue，MQ不会做任何事，只会返回成功）
 * 如果一个消费者在一个Channel下在监听某一个队列的消息，MQ是不允许该消费者在同一个Channel去声明其它队列的。
 * 个人见解，由于本着生产端与消费端隔离的思想，认为应该将Queue定义在消费方，特别当消费方为不同项目时。
 */

@Configuration
public class RabbitConfig {

  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange(Constant.TOPIC_EX);
  }

  @Bean
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange(Constant.FANOUT_EX);
  }

}
