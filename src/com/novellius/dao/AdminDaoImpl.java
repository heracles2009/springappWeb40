package com.novellius.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.novellius.pojo.Admin;
import com.novellius.pojo.AdminRowMapper;

@Transactional
@Repository
public class AdminDaoImpl implements AdminDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	//@Override
	public void save(Admin admin) {
		getSession().save(admin);
		
	}

	//@Override
	@SuppressWarnings("unchecked")
	public List<Admin> findAll() {
		
		Query query= getSession().createQuery("from Admin"); // es el equivalente a select * from Admin;
		return query.list();
	}

	//@Override
	public Admin findById(int id) {
		Criteria crit = getSession().createCriteria(Admin.class);
		crit.add(Restrictions.eq("id",id));
		return (Admin) crit.uniqueResult();
	}

	//@Override
	public List<Admin> findByNombre(String nombre) {
		Criteria crit= getSession().createCriteria(Admin.class);
		crit.add(Restrictions.like("nombre","%"+nombre+"%"));
		return crit.list();
	}

	//@Override
	public void update(Admin admin) {
		// TODO Auto-generated method stub
		getSession().update(admin);
	}

	//@Override
	public void delete(Admin admin) {
		// TODO Auto-generated method stub
		getSession().delete(admin);
	}
	

}


/*
 * 
 * @Component("adminDao")
public class AdminDaoImpl implements AdminDao{
 
private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	//@Override
	public boolean save(Admin admin) {
//		MapSqlParameterSource paramMap = new MapSqlParameterSource();
//		paramMap.addValue("nombre", admin.getNombre());
//		paramMap.addValue("cargo", admin.getCargo());
//		paramMap.addValue("fechaCreacion", admin.getFechaCreacion());
		
		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(admin);
		
		return jdbcTemplate.
				update("insert into Admin (nombre, cargo, fechaCreacion) values (:nombre, :cargo, :fechaCreacion)", paramMap) == 1;
	}

	//@Override
	public List<Admin> findAll() {
		return jdbcTemplate.query("select * from Admin", new RowMapper<Admin>() {

			//@Override
			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				Admin admin = new Admin();
				
				admin.setIdAd(rs.getInt("idAd"));
				admin.setCargo(rs.getString("cargo"));
				admin.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
				admin.setNombre(rs.getString("nombre"));
				
				return admin;
			}
		});
	}

	//@Override
	public Admin findById(int id) {
//		return (Admin) jdbcTemplate.query("select * from Admin where idAd=:idAd", 
//				new MapSqlParameterSource("idAd", id) ,new AdminRowMapper());
		
		return jdbcTemplate.queryForObject("select * from Admin where idAd=:idAd", 
				new MapSqlParameterSource("idAd", id), new AdminRowMapper());
	}

	//@Override
	public List<Admin> findByNombre(String nombre) {
		return jdbcTemplate.query("select * from Admin where nombre like :nombre", 
				new MapSqlParameterSource("nombre", "%" + nombre + "%"), new AdminRowMapper());
	}

	//@Override
	public boolean update(Admin admin) {
		return jdbcTemplate.update("update Admin set nombre=:nombre, cargo=:cargo, fechaCreacion=:fechaCreacion where idAd=:idAd", 
				new BeanPropertySqlParameterSource(admin)) == 1;
	}

	//@Override
	public boolean delete(int idAd) {
		return jdbcTemplate.update("delete from Admin where idAd=:idAd", new MapSqlParameterSource("idAd", idAd)) == 1;
	}

	@Transactional
//	@Override
	public int[] saveAll(List<Admin> admins) {
		SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(admins.toArray());
		
		return jdbcTemplate.batchUpdate("insert into Admin (idAd, nombre, cargo, fechaCreacion) values (:idAd, :nombre, :cargo, :fechaCreacion)", 
				batchArgs);
	}
}
*/