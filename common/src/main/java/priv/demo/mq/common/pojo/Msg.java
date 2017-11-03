package priv.demo.mq.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.joda.time.DateTime;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Msg implements Serializable {

  private DateTime sendTime;

  private String data;

  @Override
  public String toString() {
    return "Message{" + "[" + sendTime.toString() + "], data='" + data + "\'}";
  }
}
