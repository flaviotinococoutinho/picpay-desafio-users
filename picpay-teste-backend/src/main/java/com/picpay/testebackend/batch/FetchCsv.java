package com.picpay.testebackend.batch;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.picpay.testebackend.util.FileUtil;


class FetchCsv implements Tasklet, StepExecutionListener {

    String filePath;
    String downloadUrl;

    public FetchCsv(String filePath, String downloadUrl){
        this.filePath=filePath;
        this.downloadUrl=downloadUrl;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
       
        File file;

        file = new File(filePath);

        if (!file.exists()) {
            file.createNewFile();
        }

        URL url = new URL(downloadUrl);
        ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        fileOutputStream.close();

        try{
        	FileUtil.unZip(file,true);
        }catch(Exception e){
            throw e;
        }
        

        return RepeatStatus.FINISHED;
    }

}