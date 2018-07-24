package com.java.krishnamg.springbatch.processors;

import org.springframework.batch.item.ItemProcessor;

public class ProcessorImpl implements ItemProcessor<String, String> {

    @Override
    public String process(String message) throws Exception {
        //some transformation to the original message recieved.
        return message.toUpperCase();
    }

}
