package com.java.krishnamg.springbatch.writers;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class WriterImpl implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> msgList) throws Exception {
        for(String msg : msgList){
            System.out.println("In writer: " + msg);
        }
    }

}
