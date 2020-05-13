package com.example.batchprocessing;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	public DataSource dataSource;

    @Bean
    public MyBatisCursorItemReader<Person> reader() {
        return new MyBatisCursorItemReaderBuilder<Person>()
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("com.example.batchprocessing.PersonMapper.select")
                .build();
    }
	
	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}

	@Bean
	public MyBatisBatchItemWriter<Person> writer(DataSource dataSource) {
		return new MyBatisBatchItemWriterBuilder<Person>()
			.sqlSessionFactory(sqlSessionFactory)
			.statementId("com.example.batchprocessing.PersonMapper.save")
			.build();
	}

	// job/step
	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importUserJob")
			.incrementer(new RunIdIncrementer())
			.listener(listener)
			.flow(step1)
			.end()
			.build();
	}

	@Bean
	public Step step1(MyBatisBatchItemWriter<Person> writer) {
		return stepBuilderFactory.get("step1")
			.<Person, Person> chunk(10)
			.reader(reader())
			.processor(processor())
			.writer(writer)
			.build();
	}
}
