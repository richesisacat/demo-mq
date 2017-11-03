package priv.demo.mq.producer.controller;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import priv.demo.mq.producer.service.Sender;

@RestController
@RequestMapping("/msg")
public class MessageController {

  @Autowired
  private Sender sender;

  @PostMapping("/direct")
  @ApiOperation("direct发送，队列名称：direct")
  public void directSend(@RequestParam final String msg) {
    sender.directSend(msg);
  }

  @PostMapping("/topicMsg")
  @ApiOperation("topic发送，binding_key：topic.message")
  public void topicSendMsg(@RequestParam final String msg) {
    sender.topicSendMessage(msg);
  }

  @PostMapping("/topicAll")
  @ApiOperation("topic发送，binding_key：topic.#")
  public void topicSendAll(@RequestParam final String msg) {
    sender.topicSendAbc(msg);
  }

  @PostMapping("/fanout")
  @ApiOperation("fanout发送")
  public void fantouSend(@RequestParam final String msg) {
    sender.fanoutSend(msg);
  }
}
