package org.example;



public enum EnumAction {
	
	Enabled{
	    @Override
	    public String toString() {
	      return "assertionEnabled";
	    }
	  },
	TextDisplayed{
		  @Override
		    public String toString() {
		      return "assertionTextDisplayed";
		    }
	  },
	ElementDisplayed{
		  @Override
		    public String toString() {
		      return "assertionElementDisplayed";
		    }
	  },
	Selected{
		  
			  @Override
			    public String toString() {
			      return "assertionSelected";
			    }
		  
	  },
	button{
		  
		  @Override
		    public String toString() {
		      return "button";
		    }
	  
	  },
	radio{
		  
		  @Override
		    public String toString() {
		      return "radio";
		    }
	  
	  },
	text{
		  
		  @Override
		    public String toString() {
		      return "text";
		    }
	  
	  },
	calender{
		  
		  @Override
		    public String toString() {
		      return "calender";
		    }
	  
	  }
	
}

