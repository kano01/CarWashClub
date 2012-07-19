import au.com.carwashclub.domain.User
import org.quartz.JobDetail
import org.quartz.Trigger
import org.quartz.JobBuilder
import org.quartz.TriggerBuilder
import org.quartz.SimpleScheduleBuilder
import au.com.carwashclub.jobs.SalesPollerJob
import au.com.carwashclub.domain.MembershipType


class BootStrap {


    def quartzScheduler

    def init = { servletContext ->
        log.info("Bootstrap Init starting quartz");

        insertDefaults()



        JobDetail job = JobBuilder.newJob(SalesPollerJob.class)
        .withIdentity("SalesPoller", "carWashClubGroup")
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

    def insertDefaults = {
        if(User.count() == 0){
            log.info("No users found inserting users");
            def adminUser = new User(username: 'admin', enabled: true, password: 'password')
            adminUser.setAuthorities("ROLE_ADMIN");
            adminUser.save(flush: true)

            assert User.count() == 1

        }

        if(MembershipType.count() == 0){
            log.info("No membershiptypes found inserting membershiptypes");
            def membershipType = new MembershipType(name:"Bronze Membership", optionsMatch:"x12 Washes", voucherQuantity: 12);
            membershipType.save(flush: true)
            membershipType = new MembershipType(name:"Bronze Membership", optionsMatch:"x6 Washes", voucherQuantity: 6);
            membershipType.save(flush: true)
            membershipType = new MembershipType(name:"Bronze Membership", optionsMatch:"x3 Washes", voucherQuantity: 3);
            membershipType.save(flush: true)

        }

    }
}
