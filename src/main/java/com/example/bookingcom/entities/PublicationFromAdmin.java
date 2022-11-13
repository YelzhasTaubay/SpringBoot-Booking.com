package com.example.bookingcom.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "publication")
public class PublicationFromAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    private Users user;
    @Column(columnDefinition = "Text")
    private String description;
    private LocalDateTime postDate;
    @OneToMany(fetch = FetchType.LAZY)
    private List<PhotosOfPublication> photos;


}
