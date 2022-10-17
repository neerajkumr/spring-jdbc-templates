package com.neeraj;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class NamedParamEmployeeDao implements EmployeeDao {

	NamedParameterJdbcTemplate t;

	public NamedParamEmployeeDao(NamedParameterJdbcTemplate t) {
		this.t = t;
	}

	public NamedParamEmployeeDao() {
		super();
	}

	public NamedParameterJdbcTemplate getT() {
		return t;
	}

	public void setT(NamedParameterJdbcTemplate t) {
		this.t = t;
	}

	public void save(Employee e) {
		String query = "insert into employees values (:name,:id,:DOB)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", e.geteName());
		map.put("id", e.geteId());
		map.put("DOB", e.getDOB());
		t.execute(query, map, new PreparedStatementCallback<Object>() {

			public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				return ps.executeUpdate();
			}
		});
		getAll();
	}

	public void getAll() {
		String sql = "select * from employees";
		Map<String, Object> map = new HashMap<String, Object>();
		Employee e = new Employee();
		map.put("id", e.geteId());
		map.put("name", e.geteName());
		map.put("DOB", e.getDOB());

		List<Employee> list = t.query(sql, map, new ResultSetExtractor<List<Employee>>() {

			public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Employee> list = new LinkedList<Employee>();
				while (rs.next()) {
					Employee e11 = new Employee();
					e11.seteName(rs.getString(1));
					e11.seteId(rs.getInt(2));
					e11.setDOB(rs.getString(3));
					list.add(e11);
				}
				return list;
			}
		});
		System.out.println(String.format("%-15s %-15s %-15s", "EMP_NAME","EMP_ID","EMP_DOB"));
		System.out.println("=======================================");
		list.forEach((l) -> System.out.println(String.format("%-15s %-15s %-15s", l.geteName(),l.geteId(),l.getDOB())));
	}

	public void getByID(int id) {
		String sql = "select * from employees where E_id=:id";
		Employee e = new Employee();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id",id);
		List<Employee> emp=t.query(sql, map, new RowMapper<Employee>() {

			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				e.seteName(rs.getString(1));
				e.seteId(rs.getInt(2));
				e.setDOB(rs.getString(3));
				return e;
			}
		});
		System.out.println(String.format("%-15s %-15s %-15s", "EMP_NAME","EMP_ID","EMP_DOB"));
		System.out.println("=======================================");
		emp.forEach((l) -> System.out.println(String.format("%-15s %-15s %-15s", l.geteName(),l.geteId(),l.getDOB())));
	}

}
