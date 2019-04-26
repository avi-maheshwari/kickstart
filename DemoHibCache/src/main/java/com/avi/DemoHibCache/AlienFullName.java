package com.avi.DemoHibCache;

import javax.persistence.Embeddable;

//in order to embed this detail in already created table odrwise thr wil be a problem
@Embeddable
public class AlienFullName
{
	private String fname;
	private String mname;
	private String lname;
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	@Override
	public String toString() {
		return "AlienFullName [fname=" + fname + ", mname=" + mname + ", lname=" + lname + "]";
	}
	
}
