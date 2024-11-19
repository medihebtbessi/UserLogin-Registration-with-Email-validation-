package find_your_house.config;

import find_your_house.entity.Reservation;
import find_your_house.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchReservationConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final ReservationRepository repository;

    @Bean
    public FlatFileItemReader<Reservation> itemReaderRev(){
        FlatFileItemReader<Reservation> itemReader=new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/reservation.csv"));
        itemReader.setName("csvReaderReservation");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    @Bean
    public ReservationProcessor processorRev(){
        return new ReservationProcessor();
    }

    @Bean
    public RepositoryItemWriter<Reservation> writerRev(){
        RepositoryItemWriter<Reservation> writer=new RepositoryItemWriter<>();
        writer.setRepository(repository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step importStepRev(){
        return new StepBuilder("csvImportReservation",jobRepository)
                .<Reservation,Reservation>chunk(10,platformTransactionManager)
                .reader(itemReaderRev())
                .processor(processorRev())
                .writer(writerRev())
                .taskExecutor(taskExecutorRev())
                .build();
    }

    @Bean
    public Job runJobRev(){
        return new JobBuilder("importReservation",jobRepository)
                .start(importStepRev())
                .build();
    }
    @Bean
    public TaskExecutor taskExecutorRev(){
        SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }

    private LineMapper<Reservation> lineMapper(){
        DefaultLineMapper<Reservation> lineMapper=new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("idReservationn","dateDebut","dateFin","modePaiement");
        BeanWrapperFieldSetMapper<Reservation> fieldSetMapper=new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Reservation.class);
        fieldSetMapper.setConversionService(new CustomConversionService());
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
}
