package Rediffmail;

public class sample {

	public static void main(String[] args) {
		
		
		String s = "abcaa";
		String[] arr = s.trim().split(s);
		int dupli=0;
		for(int i=arr.length-1;i<=0;i--) {
			arr[i]=arr[i+1];
			dupli+=1;
		}
		System.out.println(dupli);
		
	}

}
