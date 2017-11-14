package a1.POJO;

import javax.persistence.*;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@NamedQueries({
	@NamedQuery(name="Movie.bySearch", query="from Movie where"
			+ " name like :search"
			+ " or rating like :search"
			+ " or director like :search"),
	@NamedQuery(name="Movie.byName", query="from Movie where name = :name"),
	@NamedQuery(name="Movie.byRating", query="from Movie where rating = :rating"),
	@NamedQuery(name="Movie.byDirector", query="from Movie where director = :director")
})
public class Movie {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int entry;
	
	private String name, director, review;
	private int rating;
}
