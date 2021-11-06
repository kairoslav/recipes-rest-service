package recipes.model;

import jdk.jfr.Category;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column
    private String name;
    @Column
    private String category;
    @Column
    private String description;
    @Column
    private LocalDateTime date;

    @ElementCollection
    private List<String> ingredients = new ArrayList<>();

    @ElementCollection
    private List<String> directions = new ArrayList<>();

    public Recipe(String name, String category, String description, List<String> ingredients, List<String> directions) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
        this.date = LocalDateTime.now();
    }
}
