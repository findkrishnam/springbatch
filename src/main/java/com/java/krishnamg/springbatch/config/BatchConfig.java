package com.java.krishnamg.springbatch.config;

import com.java.krishnamg.springbatch.processors.ProcessorImpl;
import com.java.krishnamg.springbatch.readers.ReaderImpl;
import com.java.krishnamg.springbatch.writers.WriterImpl;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SimpleJobLauncher simpleJobLauncher;

    @Scheduled(cron = "* * * * * ?")
    public void someRandomTaskTodo() throws Exception {
        System.out.println("Job Started @ :" + LocalDateTime.now().toString());
        JobParameters parameters = new JobParametersBuilder().addString("JobId", String.valueOf(System.currentTimeMillis())).toJobParameters();
        JobExecution execution = simpleJobLauncher.run(job(), parameters);
        System.out.println("Job finished with status :" + execution.getStatus());
    }

    public Job job() {
        return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
    }

    public Step step1() {
        return stepBuilderFactory.get("step1").<String, String>chunk(1).reader(new ReaderImpl()).processor(new ProcessorImpl()).writer(new WriterImpl()).build();
    }

}
