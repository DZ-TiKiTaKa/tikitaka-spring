package com.tikitaka.redis;

import com.tikitaka.model.MessageModel;

public interface MessagePublisher {
    void publish(String message);

	void publish(MessageModel model);
}