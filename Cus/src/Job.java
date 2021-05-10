 import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Job implements Comparable {



    private final String JobId;
    private boolean JobCompleted;
    private final long residency;
    private final long duration;


    public Job(String JobId, long residency, long duration) {

    this.JobId = JobId;
    this.duration = duration;
    this.residency=residency;

    }

   

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}


	public boolean isJobCompleted() {
		return JobCompleted;
	}


	public void setJobCompleted(boolean jobCompleted) {
		JobCompleted = jobCompleted;
	}


	public String getJobId() {
		return JobId;
	}


	public long getResidency() {
		return residency;
	}


	public long getDuration() {
		return duration;
	}

		



}