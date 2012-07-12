import au.com.carwashclub.User
import au.com.carwashclub.SalesPollerJob
import org.quartz.JobDetail
import org.quartz.Trigger
import org.quartz.JobBuilder
import org.quartz.TriggerBuilder
import org.quartz.SimpleScheduleBuilder


class BootStrap {


    def quartzScheduler

    def init = { servletContext ->
        log.info("HELLO BOOOOOT");

        if(User.count() == 0){
          def adminUser = new User(username: 'admin', enabled: true, password: 'password')
          adminUser.setAuthorities("ROLE_ADMIN");
          adminUser.save(flush: true)


          assert User.count() == 1

        }

     JobDetail job = JobBuilder.newJob(SalesPollerJob.class)
    .withIdentity("SalesPollerJob", "carWashClubGroup")
    .build();

    // Trigger the job to run now, and then repeat every 40 seconds
    Trigger trigger = TriggerBuilder.newTrigger()
        .withIdentity("checkPurchasesTrigger", "carWashClubGroup")
        .startNow()
        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(60)
                .repeatForever())
        .build();

    // Tell quartz to schedule the job using our trigger
    quartzScheduler.scheduleJob(job, trigger);

    }
    def destroy = {
    }
}
