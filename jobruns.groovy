import hudson.model.*
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar

df=new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
starttime=new GregorianCalendar();
endtime=new GregorianCalendar();
buildEnd=new GregorianCalendar();

starttime.setTime(df.parse("2015-7-10 10:10:10"));
endtime.setTime(df.parse("2015-12-30 11:10:10"));


for (hudson.model.AbstractProject p : hudson.model.Hudson.instance.projects) {
    for (hudson.model.AbstractBuild b : p.getBuilds() ) {
        if (b==null) break; //I think that can't be null anyway
        if ( b.isBuilding() || b.timestamp.compareTo(endtime) >= 0 ) continue;
        buildEnd.setTimeInMillis(b.timestamp.getTimeInMillis()+b.duration);
        if ( buildEnd.compareTo(starttime) < 0 ) break;

        println b 
        println buildEnd.time
    }
}