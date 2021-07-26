import java.util.ArrayList;

public class LRE {
	static ArrayList<ArrayList<String>> grammerRules = new ArrayList<ArrayList<String>> ();
	static ArrayList<ArrayList<String>> finalRules = new ArrayList<ArrayList<String>> ();
	static String Final = "";
	
	public LRE(String rules) {
		String [] subRules = rules.split("\\;");
		for (int i = 0 ; i < subRules.length ; i++) {
			ArrayList<String> initial = new ArrayList<String> ();
			 String [] subInitial = subRules[i].split("\\,") ;
			 for ( int j = 0 ; j < subInitial.length ; j++) {
				 initial.add(subInitial[j]);
			 }
			 grammerRules.add(initial);
		}
		for (int k = 0 ; k < grammerRules.size() ; k++) {
			for (int l = 0 ; l < k ;l++) {
				LRD ( grammerRules.get(k),grammerRules.get(l),k);
				}
			leftRecursion(grammerRules.get(k),k);
		}
		Final = "" ;
		for (int n = 0 ; n < finalRules.size();n++) {
			for(int c = 0 ; c < finalRules.get(n).size() ; c++) {
				if (c!= finalRules.get(n).size()-1)
					Final = Final + finalRules.get(n).get(c) + "," ;
				else 
					Final = Final + finalRules.get(n).get(c); 
			}
			if (n!= finalRules.size()-1)
				Final = Final +";" ;
		
		}
	}
	public static void leftRecursion(ArrayList<String> x, int k) {
		ArrayList<String> beta = new ArrayList<String> ();
		ArrayList<String> alpha = new ArrayList<String> ();
		for (int i = 1; i< x.size() ; i++) {
			String y = "" + x.get(i).charAt(0);
			if(y.equals(x.get(0))){
				alpha.add(x.get(i).substring(1,x.get(i).length()));
			}
			else {
				beta.add(x.get(i));
			}
		}
		if (!(alpha.isEmpty())) {
			ArrayList<String> initial = new ArrayList<String> ();
			initial.add(x.get(0));
			for ( int j = 0; j < beta.size() ; j++ ) {
				initial.add(beta.get(j)+x.get(0)+"'");
			}
			finalRules.add(initial);
			initial = new ArrayList<String> ();
			initial.add(x.get(0)+"'");
			for ( int l = 0 ; l < alpha.size() ; l++) {
			   initial.add(alpha.get(l)+x.get(0)+"'");
			}
			initial.add("e");
			finalRules.add(initial);
		}
		else 
		{
			finalRules.add(x);
		}
	
	}
	
	public static void LRD(ArrayList<String> x,ArrayList<String> y,int k) {
		String start = y.get(0);
		for (int i = 1 ; i < x.size() ; i++) {
			String end = ""+x.get(i).charAt(0);
			int z  = 0;
			if (start.equals(end)) {
				y = search (y.get(0));
				String temp = x.get(i).substring(1, x.get(i).length());
				x.remove(i);
				for ( int j = 1 ; j < y.size() ; j++) {
					x.add(i+z,y.get(j)+""+temp);
					z++;
				}
				i = (i + z)-1;
			}
			grammerRules.remove(k);
			grammerRules.add(k,x);
		}
	}
	 
	public static ArrayList<String> search (String x) {
		for (int i = 0 ; i < finalRules.size() ; i++) {
			if (finalRules.get(i).get(0).equals(x)) {
				return finalRules.get(i);
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
//		
//		LRE lre = new LRE("S,qSt,qS,c");
//		System.out.println(Final);
//		Final = "";
//		LRE lre2 = new LRE("S,SvS,SuS,q");
//		System.out.println(Final);
//		Final = "";
//		LRE lre3 = new LRE("S,SvT,T;T,TuK,K;K,fa");
//		System.out.println(Final);
//		Final = "";
//		LRE lre4 = new LRE("S,EK,Kd;E,SK,ES,c;K,SE,c");
//		System.out.println(Final);
//		Final = "";
		LRE lre5 = new LRE("S,ScT,Sq,T,b;T,qSb,iqHb,i;H,SdH,S");
		System.out.println(Final);
		Final = "";
	}

	
}
