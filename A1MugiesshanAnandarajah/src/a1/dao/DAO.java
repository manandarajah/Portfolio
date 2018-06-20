package a1.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import a1.POJO.Movie;

public class DAO {
	SessionFactory sessionFactory= new Configuration().configure("a1/config/hibernate.cfg.xml").buildSessionFactory();
	
	public void transaction(Movie movie) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(movie);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void queryDelete(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("delete from Movie where name = :name");
		query.setParameter("name", name);
		query.executeUpdate();
		session.close();
	}
	
	public List<Movie> queryBySearch(String search) {
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery("Movie.bySearch");
		query.setParameter("search","%"+search+"%");
		List<Movie> result = query.getResultList();
		session.close();
		return result;
	}
	
	public List<Movie> queryByName(String name) {
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery("Movie.byName");
		query.setParameter("name", name);
		List<Movie> result = query.getResultList();
		session.close();
		return result;
	}
	
	public List<Movie> queryByRating(int rating) {
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery("Movie.byRating");
		query.setParameter("rating", rating);
		List<Movie> result = query.getResultList();
		session.close();
		return result;
	}
	
	public List<Movie> queryByDirector(String director) {
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery("Movie.byDirector");
		query.setParameter("director", director);
		List<Movie> result = query.getResultList();
		session.close();
		return result;
	}
}
