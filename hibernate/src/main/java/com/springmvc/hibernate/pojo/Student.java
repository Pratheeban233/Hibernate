package com.springmvc.hibernate.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
//@Table(name = "A_student")
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Student {
	@Id
	private int rollNo;
	private String Name;
	private int Mark;
//	@ManyToMany(mappedBy = "student", fetch = FetchType.EAGER)
//	private List<Laptop> laptop = new ArrayList<>();

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getMark() {
		return Mark;
	}

	public void setMark(int mark) {
		Mark = mark;
	}

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", Name=" + Name + ", Mark=" + Mark + "]";
	}

}
