public class Eligibility {

    public boolean White(String white) {
    
	boolean eligible = false;
	
	if (white.equals("")) {
	    eligible = true;
	}
	
	for (int i = 0; i < white.length(); i++) {
	  
	    if (white.charAt(i) < '0'
		|| white.charAt(i) > '9') {
		eligible = true;
		break;
	    }
	       
	    else {
		eligible = false;
	    }
	}
	
	return eligible;
    }
    
    public boolean Red(String red) {
    
	boolean eligible = false;
	
	if (red.equals("")) {
	    eligible = true;
	}
	
	for (int i = 0; i < red.length(); i++) {
	  
	    if (red.charAt(i) < '0'
		|| red.charAt(i) > '9') {
		eligible = true;
		break;
	    }
	       
	    else {
		eligible = false;
	    }
	}
	
	return eligible;
    }
    
    public boolean Green(String green) {
    
	boolean eligible = false;
	
	if (green.equals("")) {
	    eligible = true;
	}
	
	for (int i = 0; i < green.length(); i++) {
	  
	    if (green.charAt(i) < '0'
		|| green.charAt(i) > '9') {
		eligible = true;
		break;
	    }
	       
	    else {
		eligible = false;
	    }
	}
	
	return eligible;
    }
    
    public boolean Black(String black) {
    
	boolean eligible = false;
	
	if (black.equals("")) {
	    eligible = true;
	}
	
	for (int i = 0; i < black.length(); i++) {
	  
	    if (black.charAt(i) < '0'
		|| black.charAt(i) > '9') {
		eligible = true;
		break;
	    }
	       
	    else {
		eligible = false;
	    }
	}
	
	return eligible;
    }
}

