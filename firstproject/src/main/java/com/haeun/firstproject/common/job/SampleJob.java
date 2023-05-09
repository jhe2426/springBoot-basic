package com.haeun.firstproject.common.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SampleJob implements Job {

    //실무에서는 많이 사용된다고 함

    //* 실제 작업이 실행될 메서드(execute메서드가 반복적으로 실행될 메서드인 것)
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Scheduler!!");
    }
    
    //* Job Detail 설정 (Job의 메타 데이터 설정)
    @Bean
    public JobDetail jobDetail() { //JobDetail jobDetail에 대한 설명 작성 (이름, 설명 작성)
        return JobBuilder.newJob()
                        .ofType(SampleJob.class)
                        .storeDurably()
                        .withIdentity("Sample Job") //Job의 이름
                        .withDescription("Sample Job 테스트입니다.") //Job 설명
                        . build();
    }

    //* Trigger 설정 (반복할 스케줄 지정)
    //Trigger(언제 어떻게 실행을 할 것인지를 작성해주는 것)
    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        CronScheduleBuilder schedule = //CronScheduleBuilder 언제 어떠한 횟수만큼 반복할지 지정하는 것
            CronScheduleBuilder.cronSchedule("* * * * * ?"); //초 분 시 일 월 요일 순서임 1초 마다 실행이 되도록 설정한 것임
            // cron 표현식
            // cronScheduler(”초(0-59, *) 분(0-59, *) 시(0-23, *) 일(1-31, *) 월(1-12, *) 요일(0-7, ?)”); 


        return TriggerBuilder.newTrigger()
                        .forJob(jobDetail)
                        .withIdentity("Sample Trigger")
                        .withDescription("Sample Trigger 입니다.")
                        .withSchedule(schedule) // 여기 메서드에서 만든 schedule 넣어 주는 거
                        .build();
    }
}
