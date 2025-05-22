package umc.study.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.store.FoodCategory;
import umc.study.domain.user.User;
import umc.study.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PreferFood extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private FoodCategory category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setMember(User user){
        if(this.user != null)
            user.getPreferFoods().remove(this);
        this.user = user;
        user.getPreferFoods().add(this);
    }

    public void setFoodCategory(FoodCategory foodCategory){
        this.category = foodCategory;
    }
}
