package recipes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IngredientID")
    public long id;
    @Column
    public String ingredient;

    public Ingredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
