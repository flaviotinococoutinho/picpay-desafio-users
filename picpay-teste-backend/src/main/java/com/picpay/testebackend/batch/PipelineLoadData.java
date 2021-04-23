package com.picpay.testebackend.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.picpay.testebackend.dto.UserDTO;


@Configuration
public class PipelineLoadData {
    private static final String QUERY_INSERT_STUDENT = "INSERT " +
            "INTO user(id, nome, username) VALUES (UUID_TO_BIN(:id), :nome, :username) ON DUPLICATE KEY UPDATE nome=:nome,username=:username";
    
	@Value("${usersCsv.url}")
	private String DOWNLOAD_URL;
	@Value("${usersCsv.filePath}")
	private String FILE_PATH;
	@Value("${usersCsv.filePathDecompressed}")
	private String FILE_PATH_DECOMPRESSED;


    private FieldSetMapper<UserDTO> createStudentInformationMapper() {
        BeanWrapperFieldSetMapper<UserDTO> studentInformationMapper = new BeanWrapperFieldSetMapper<>();
        studentInformationMapper.setTargetType(UserDTO.class);
        return studentInformationMapper;
    }

    private LineMapper<UserDTO> createStudentLineMapper() {
        DefaultLineMapper<UserDTO> studentLineMapper = new DefaultLineMapper<>();

        LineTokenizer studentLineTokenizer = createStudentLineTokenizer();
        studentLineMapper.setLineTokenizer(studentLineTokenizer);

        FieldSetMapper<UserDTO> studentInformationMapper = createStudentInformationMapper();
        studentLineMapper.setFieldSetMapper(studentInformationMapper);

        return studentLineMapper;
    }

    private LineTokenizer createStudentLineTokenizer() {
        DelimitedLineTokenizer studentLineTokenizer = new DelimitedLineTokenizer();
        studentLineTokenizer.setDelimiter(",");
        studentLineTokenizer.setNames(new String[]{"id", "nome", "username"});
        return studentLineTokenizer;
    }

    @Bean
    ItemWriter<UserDTO> csvFileDatabaseItemWriter(DataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) {
        JdbcBatchItemWriter<UserDTO> databaseItemWriter = new JdbcBatchItemWriter<>();
        databaseItemWriter.setDataSource(dataSource);
        databaseItemWriter.setJdbcTemplate(jdbcTemplate);

        databaseItemWriter.setSql(QUERY_INSERT_STUDENT);

        ItemSqlParameterSourceProvider<UserDTO> sqlParameterSourceProvider = studentSqlParameterSourceProvider();
        databaseItemWriter.setItemSqlParameterSourceProvider(sqlParameterSourceProvider);

        return databaseItemWriter;
    }

	@Bean
	ItemProcessor<UserDTO, UserDTO> csvFileItemProcessor() {
		return new LogProcess();
	}

    @Bean
    ItemReader<UserDTO> csvFileItemReader() {
        FlatFileItemReader<UserDTO> csvFileReader = new FlatFileItemReader<>();
        csvFileReader.setResource(new FileSystemResource(FILE_PATH_DECOMPRESSED));
        csvFileReader.setLinesToSkip(1);

        LineMapper<UserDTO> studentLineMapper = createStudentLineMapper();
        csvFileReader.setLineMapper(studentLineMapper);

        return csvFileReader;
    }

    @Bean
    Job csvFileToDatabaseJob(JobBuilderFactory jobBuilderFactory,
                             @Qualifier("csvFileToDatabaseStep") Step csvStudentStep,@Qualifier("download") Step download) {
        return jobBuilderFactory.get("csvFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .start(download).next(csvStudentStep) 
                .build();
    }
    
	@Bean
	public Step download(StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("download").tasklet(new FetchCsv(FILE_PATH, DOWNLOAD_URL)).build();
	}

    @Bean
    Step csvFileToDatabaseStep(ItemReader<UserDTO> csvFileItemReader,
                               ItemProcessor<UserDTO, UserDTO> csvFileItemProcessor,
                               ItemWriter<UserDTO> csvFileDatabaseItemWriter,
                               StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("csvFileToDatabaseStep")
                .<UserDTO, UserDTO>chunk(1)
                .reader(csvFileItemReader)
                .processor(csvFileItemProcessor)
                .writer(csvFileDatabaseItemWriter)
                .build();
    }

    private ItemSqlParameterSourceProvider<UserDTO> studentSqlParameterSourceProvider() {
        return new BeanPropertyItemSqlParameterSourceProvider<>();
    }
}
