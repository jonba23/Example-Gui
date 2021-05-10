import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class VCC {

	
	
	HashMap<String,Job>jobs;
	
	
    private Queue<Job> jobQueue;
   
    private HashMap<String, Vehicle> vehicles = new HashMap<>();
    
    
    
    
    
    public HashMap<String, Job> getJobs() {
		return jobs;
	}

	public void setJobs(HashMap<String, Job> jobs) {
		this.jobs = jobs;
	}

	public Queue<Job> getJobQueue() {
		return jobQueue;
	}

	public void setJobQueue(Queue<Job> jobQueue) {
		this.jobQueue = jobQueue;
	}

	VCC() {
		jobs = new HashMap<String,Job>();
		jobQueue  = new PriorityQueue<>();
     }
	
	public static void write(String JobData) {
		 
         try {
      	   FileWriter clientStream = new FileWriter("JobData.txt",true);
      		  BufferedWriter writer = new BufferedWriter(clientStream);         		                         
      	   	writer.write(JobData);
      	   	writer.close();
         } catch (IOException e) {
        e.printStackTrace();
         }
		
	}

    public void registerJob(Job job)   {
    	
    			jobs.put(job.getJobId(), job);
				jobQueue.add(job);
			
    }

    public void RemoveJob(String jobID) {
        jobQueue.poll();
        jobs.remove(jobID);
    }

    public void assignVehicle(Job job) {
    	
    	File file = new File("VehicleData.txt");
    			
    	
    	if(file.length()==0) {
    		
    	String fileName = "WaitingJobs.txt";
    	
		BufferedWriter writer;
		
		try {
			writer = new BufferedWriter(new FileWriter(fileName, true));
			writer.write(toString(job));
			
			writer.newLine();
		
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
			
    	}
    	else {
    		Scanner kb= new Scanner("VehicleData.txt");
    		
    	}
    }
    
    public void PopulateVehicles() throws IOException {
    	
    	Scanner kb= new Scanner("VehicleData.txt");
    	String kbNext="";
    	ArrayList<String> VehicleListArray = new ArrayList<String>();
    	 
    	BufferedReader csvReader = new BufferedReader(new FileReader("VehicleData.txt")); // csv file path
    	
    	while (( kbNext = csvReader.readLine()) != null) {
    	
    		String[] data = kbNext.split(",");  // in data you have each column value for each row
    		
    			
		 String VehicleMake = data[0];
		 String VehicleModel = data[1];
		 String VehicleYear = data[2];
		 
		long Resideny = Long.parseLong(data[3]);;
					
			Vehicle vehicle = new Vehicle(VehicleMake, VehicleModel, VehicleYear, Resideny);
	
		 
			vehicles.put( vehicle.getVin(), vehicle);
    	}
    }
    
    
    public HashMap<String, Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(HashMap<String, Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public String toString(Job job) {
    	return job.getJobId();
    	
    	
    	
    }
	
	public int getCompletionTime(String id) {
        Job job= jobs.get(id);
            long timer = job.getDuration();
            for(Job time: jobQueue) {
            	if(time.equals(job)) {
            		return (int) timer;
            	}
          
            	timer += (int) job.getDuration();
            }
         
            return -1;
        
      
    }
 
}
