package priv.demo.mq.customer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import priv.demo.mq.common.pojo.Constant;

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

  // direct 方式
  @Bean
  public Queue directQueue() {
    return new Queue(Constant.Q_DIRECT);
  }

  //topic 方式
  @Bean
  public Queue queueMessage() {
    return new Queue(Constant.Q_TOPIC_MESSAGE);
  }

  // topic 方式
  @Bean
  public Queue queueMessageAll() {
    return new Queue(Constant.Q_TOPIC_MESSAGE_ALL);
  }

  /**
   * 声明Binding需要提供QueueName,ExchangeName,BindingKey routing_key 由发送方指定，不关心哪个Queue，只指定名称，由exchange路由 binding_key
   * 由接收方指定，将该Queue与exchange绑定，并指定binding_key正则表达式，以匹配符合的routing_key binding_key=topic.message
   * 完全匹配routing_key为topic.message发过来的消息才加放队列
   */
  @Bean
  public Binding bindingExchangeMessage(final Queue queueMessage, final TopicExchange topicExchange) {
    return BindingBuilder.bind(queueMessage).to(topicExchange).with(Constant.R_TOPIC_MESSAGE);
  }

  /**
   * binding_key=topic.# 模糊匹配routing_key为topic.xxx发过来的消息加放队列
   */
  @Bean
  public Binding bindingExchangeMessageAll(final Queue queueMessageAll, final TopicExchange topicExchange) {
    return BindingBuilder.bind(queueMessageAll).to(topicExchange).with(Constant.R_TOPIC_MESSAGE_ALL);
  }

  // fanout 方式
  @Bean
  public Queue messageA() {
    return new Queue(Constant.Q_FANOUT_A);
  }

  // fanout 方式
  @Bean
  public Queue messageB() {
    return new Queue(Constant.Q_FANOUT_B);
  }

  // fanout 方式
  @Bean
  public Queue messageC() {
    return new Queue(Constant.Q_FANOUT_C);
  }

  @Bean
  public Binding bindingExchangeA(final Queue messageA, final FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(messageA).to(fanoutExchange);
  }

  @Bean
  public Binding bindingExchangeB(final Queue messageB, final FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(messageB).to(fanoutExchange);
  }

  @Bean
  public Binding bindingExchangeC(final Queue messageC, final FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(messageC).to(fanoutExchange);
  }

}
