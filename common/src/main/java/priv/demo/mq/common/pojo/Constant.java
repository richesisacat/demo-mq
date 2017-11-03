package priv.demo.mq.common.pojo;

public class Constant {

  public static final String TOPIC_EX = "my_topic_ex";

  public static final String FANOUT_EX = "my_fanout_ex";

  // queue name
  public static final String Q_DIRECT = "direct";

  public static final String Q_TOPIC_MESSAGE = "topic.message";

  public static final String Q_TOPIC_MESSAGE_ALL = "topic.message_all";

  public static final String Q_FANOUT_A = "fanout.A";

  public static final String Q_FANOUT_B = "fanout.B";

  public static final String Q_FANOUT_C = "fanout.C";

  // routing key name

  public static final String R_TOPIC_MESSAGE = "topic.message";

  public static final String R_TOPIC_MESSAGE_ALL = "topic.#";

}
