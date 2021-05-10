import java.util.*;

import java.security.SecureRandom;

public class Client 
{
	private String ClientId;
	//private Jobduration;
	private String JobDeadline;
	private final List<Job> jobs;
	
	
	public Client() 
	{
		ClientId=IDgenerator();
		jobs = new ArrayList<Job>();
	}
	

	public Client(String ClientId, String JobDeadline, List<Job> jobs) {
		this.ClientId= ClientId;
		this.JobDeadline= JobDeadline;
		this.jobs = jobs;
		
	}
	
	
	

	public static String IDgenerator()												//12 digit alphanumeric ID generator
		{
			
			String start="";
			int count=0;
			String hold="";
			SecureRandom rand=new SecureRandom();
			char alphabet[]= 
				{'A','B','C','D', 'E', 'F', 'G', 
             'H', 'I', 'J', 'K', 'L', 'M', 'N',  
             'O', 'P', 'Q', 'R', 'S', 'T', 'U', 
              'V', 'W', 'X', 'Y', 'Z'
				};
			
			do {
				for(int i=0;i<3;i++) 
				{
					hold=String.valueOf(rand.nextInt(999                                                                                                                         ));
					start=start + alphabet[rand.nextInt(26)];
					if(hold.length()==2) {
						hold="0"+hold;
					}
					else if(hold.length()==1) {
						hold="00"+hold;
					}
				}
					start=start+hold;
				count++;
			}while(count<2);
		return start;	
			
		}


	public String getClientId() {
		return ClientId;
	}


	public String getJobdeadline() {
		return JobDeadline;
	}


	public void addJob(Job var) {
		jobs.add(var);
	}



}