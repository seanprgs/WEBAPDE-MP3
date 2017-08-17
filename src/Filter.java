import java.util.*;

public class Filter{
	public static void main (String[] args) {
		ArrayList <String> words = new ArrayList<String>();
		ArrayList <String> fwords = new ArrayList<String>();
		
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i <3; i ++)
		{
			words.add(sc.nextLine());
		}
		
		for(int i = 0 ; i < words.size(); i++)
		{
			String w = words.get(i);
			if(!fwords.contains(w))
				fwords.add(w);
		}

		for(int i = 0; i < fwords.size(); i++)
			System.out.println(fwords.get(i));
	}
}