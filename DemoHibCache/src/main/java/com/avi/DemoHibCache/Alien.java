package com.avi.DemoHibCache;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

//to change name of the table is @Entity(name="alien_table")
//also entity name and table is different so if we only
//want to change table name we can use
//@Entity


@Entity
//@Table(name="ALien_table")
@Cacheable
//if u have to make it cacheable for second level
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
//how the cached data need to processed - as read only for now
public class Alien 
{
	@Id
	private int aid;
	//@Transient //this will drop the aname field from the table in database
	private AlienFullName aname;
	//@Column(name="Alien_color") //to change column name
	private String color;
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public AlienFullName getAname() {
		return aname;
	}
	public void setAname(AlienFullName aname) {
		this.aname = aname;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Alien [aid=" + aid + ", aname=" + aname + ", color=" + color + "]";
	}

	
}
