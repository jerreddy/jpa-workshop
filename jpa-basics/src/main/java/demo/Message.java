package demo;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Setter
@Getter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="text", nullable = false, length = 100)
    private String text;

    @ManyToOne
    @JoinColumn(name = "created_user_id", nullable = false)
    private User createdBy;
}
