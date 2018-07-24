package com.java.krishnamg.springbatch.readers;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class ReaderImpl implements ItemReader<String> {

    private String[] msgArray= {"Hi","Welcome","To","Spring","batch","!!"};
    private int count=0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(count < msgArray.length){
            return msgArray[count++];
        }else{
            count=0;
        }
        return null;
    }
}
