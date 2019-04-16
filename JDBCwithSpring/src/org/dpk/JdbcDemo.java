package org.dpk;

import org.dpk.dao.JdbcDaoImpl;
import org.dpk.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) {
		Circle circle=new JdbcDaoImpl().getCircle(1);
		System.out.println(circle.getName());
	}

}
