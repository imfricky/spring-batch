package com.fricky.springbatch.config;

import com.fricky.springbatch.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    private static final String CSV_FILE_PATH = "src/main/resources/users.csv";

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<User> itemReader,
                   ItemProcessor<User, User> itemProcessor,
                   ItemWriter<User> itemWriter) {

        // Create a Step which is given reader, processor and writer
        Step step = stepBuilderFactory.get("User-Step")
                .<User, User>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        // Create a Job which is given one or multiple steps
        Job job = jobBuilderFactory.get("User-Job")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
        return job;
    }

    @Bean
    public FlatFileItemReader<User> fileItemReader() {
        FlatFileItemReader<User> fileItemReader = new FlatFileItemReader<>();
        fileItemReader.setResource(new FileSystemResource(CSV_FILE_PATH)); // Giving reader the resource to read from
        fileItemReader.setName("CSV-Reader"); // Setting name of the reader
        fileItemReader.setLinesToSkip(1); // Setting lines to skip, because we are storing headers in csv
        fileItemReader.setLineMapper(lineMapper());
        return fileItemReader;
    }

    @Bean
    public LineMapper<User> lineMapper() {
        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(","); // Setting the delimiter type for our file
        lineTokenizer.setStrict(false); // Setting strictness as false
        lineTokenizer.setNames("id", "name", "dept", "salary"); // setting the name of fields in csv

        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class); // Setting the target class type for data read from csv

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

}
